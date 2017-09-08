/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import exception.QuoteNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author MartinLodahl
 */
@Path("quote")
public class APIResource {
//Det er vigtigt at skrive response error numre, da det bliver vist som en fejl, 
//og kan læses af devs der prøver at tilgå den.
    private static Map<Integer, String> quotes = new HashMap() {
        {
           /* put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");*/
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of APIResource
     */
    public APIResource() {
    }

    /**
     * Retrieves representation of an instance of rest.APIResource
     *
     * @param id
     * @return an instance of java.lang.String
     * @throws exception.QuoteNotFoundException
     */
    //api/quote/{id}
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml(@PathParam("id") int id) throws QuoteNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        if (quotes.get(id) == null) {
            throw new QuoteNotFoundException("{\"code\": 404, \"message\": \"Quote with requested id not found\"}");
        }
        return "{\"quote\":\"" + quotes.get(id) + "\"}";
    }

    @Path("random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() throws QuoteNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        if (quotes.size() == 0) {
            throw new QuoteNotFoundException(" {\"code\": 404, \"message\": \"No Quotes Created yet\"}");
        }
        int r = (int) (Math.random() * quotes.size()) + 1;
        return "{\"quote\":\"" + quotes.get(r) + "\"}";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJSON(String content) {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        int newId = quotes.size() + 1;
        Quote q1 = new Gson().fromJson(content, Quote.class);
        quotes.put(newId, q1.getQuote());
        JsonObject j1 = new JsonObject();
        j1.addProperty("id", newId);
        j1.addProperty("quote", q1.getQuote());
        return j1.toString();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJSON(String content, @PathParam("id") int id) throws QuoteNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        if (quotes.get(id) == null) {
            throw new QuoteNotFoundException("{\"code\": 404, \"message\": \"Quote with requested id not found\"}");
        }
        Quote q1 = new Gson().fromJson(content, Quote.class);
        quotes.put(id, q1.getQuote());
        JsonObject j1 = new JsonObject();
        j1.addProperty("id", id);
        j1.addProperty("quote", q1.getQuote());
        return j1.toString();
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteJSON(String content, @PathParam("id") int id) throws QuoteNotFoundException {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        if (quotes.get(id) == null) {
            throw new QuoteNotFoundException("{\"code\": 404, \"message\": \"Quote with requested id not found\"}");
        }
        JsonObject j1 = new JsonObject();
        j1.addProperty("quote", quotes.get(id));
        quotes.remove(id);

        return j1.toString();
    }
}
