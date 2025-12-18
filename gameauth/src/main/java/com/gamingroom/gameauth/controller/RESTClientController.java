package com.gamingroom.gameauth.controller;
 
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders; // Import for setting headers..
import java.util.Base64; // Import for Base64 encoding...

import com.gamingroom.gameauth.representations.GameUserInfo;
 
@Produces(MediaType.TEXT_PLAIN)
@Path("/client/")
public class RESTClientController 
{
    private Client client;
 
    public RESTClientController(Client client) {
        this.client = client;
    }
     
    @GET
    @Path("/gameusers")
    public String getGameUsers()
    {
        // 1. This defines username and password for the client request
        String username = "guest";
        String password = "password";
        String token = username + ":" + password;
        
        // 2. Encodes the token using Base64...
        String encodedToken = Base64.getEncoder().encodeToString(token.getBytes());
        
        WebTarget webTarget = client.target("http://localhost:8080/gameusers");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        
        // 3. Then adds the authorization header to authenticate the client request
        invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Basic " + encodedToken);
        
        Response response = invocationBuilder.get();
        
        // Handles no -200 responses if necessary but proceed assuming success for now
        if (response.getStatus() != 200) {
            return "Error: API returned status " + response.getStatus();
        }
        
        @SuppressWarnings("rawtypes")
        ArrayList gameusers = response.readEntity(ArrayList.class);
        return gameusers.toString();
    }
     
    @GET
    @Path("/gameusers/{id}")
    public String getGameUserById(@PathParam("id") int id)
    {
        String username = "guest";
        String password = "password";
        String token = username + ":" + password;
        String encodedToken = Base64.getEncoder().encodeToString(token.getBytes());

        //Do not hard code in your application
        WebTarget webTarget = client.target("http://localhost:8080/gameusers/"+id);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        
        // Adds the Authorization header...
        invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Basic " + encodedToken);
        
        Response response = invocationBuilder.get();
        
        if (response.getStatus() != 200) {
            return "Error: API returned status " + response.getStatus();
        }
        
        GameUserInfo gameUserInfo = response.readEntity(GameUserInfo.class);
        return gameUserInfo.toString();
    }
}