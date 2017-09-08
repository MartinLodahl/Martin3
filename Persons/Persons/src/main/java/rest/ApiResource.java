/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import Entity.Person;
import com.google.gson.Gson;
import exception.PersonNotFoundException;
import exception.ValidationErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import facader.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import json.JSonConverter;

/**
 * REST Web Service
 *
 * @author MartinLodahl
 */
@Path("person")
public class ApiResource {

    EntityManagerFactory emf;
    PersonFacade pf;
    JSonConverter jc;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
        this.jc = new JSonConverter();
        this.emf = Persistence.createEntityManagerFactory("JPAPU");
        this.pf = new PersonFacade(emf);
    }

    /**
     * Retrieves representation of an instance of rest.ApiResource
     *
     * @param content
     * @param id
     * @return an instance of java.lang.String
     * @throws exception.PersonNotFoundException
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(String content, @PathParam("id") int id) throws PersonNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        Person p = pf.getPerson(id);
        if (p == null) {
            throw new PersonNotFoundException("{\"code\": 404, \"message\": \"No person with provided id found\"}");
        }
        return jc.getJSONFromPerson(p);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons(String content) {
        //TODO return proper representation object
        //{"quote" : "Quote text"}

        List< Person> p = pf.getPersons();
        return new Gson().toJson(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createPerson(String content) throws ValidationErrorException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        Person p = jc.getPersonFromJson(content);
        if (p.getfName() == null || p.getfName().equals("") || p.getlName() == null || p.getlName().equals("")) {
            throw new ValidationErrorException("{\"code\": 400, \"message\": \"First Name or Last Name is missing\"}");
        }
        pf.addPerson(p);
        return "{}";
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param content representation for the resource
     * @param id
     */
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content, @PathParam("id") int id) throws ValidationErrorException, PersonNotFoundException {
        Person p = jc.getPersonFromJson(content);
        if (pf.getPerson(id) == null) {
            throw new PersonNotFoundException("{\"code\": 404, \"message\": \"Cannot edit. Person with provided id does not exist\"");
        } else if (p.getfName() == null || p.getfName().equals("") || p.getlName() == null || p.getlName().equals("")) {
            throw new ValidationErrorException("{\"code\": 400, \"message\": \"First Name or Last Name is missing\"}");
        }
        p.setId((long) id);
        p = pf.editPerson(p);
        return "{}";
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(String content, @PathParam("id") int id) throws PersonNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        if (pf.getPerson(id) == null) {
            throw new PersonNotFoundException("{\"code\": 404, \"message\":\"Could not delete. No person with provided id exists\"");
        }
        pf.deletePerson(id);
        return "{}";
    }
}
