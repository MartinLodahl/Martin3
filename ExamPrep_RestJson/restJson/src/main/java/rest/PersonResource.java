/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Generator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author MartinLodahl
 */
@Path("data")
public class PersonResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of entity.PersonResource
     * @param amount
     * @param id
     * @return an instance of java.lang.String
     */
     @Path("{amount}/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("amount") int amount, @PathParam("id") int id) {
        //TODO return proper representation object
        String testData = Generator.generatePerson(amount, id);
        
        return testData;
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        //TODO return proper representation object
        String testData = Generator.generatePerson(2, 4);
        
        return testData;
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
