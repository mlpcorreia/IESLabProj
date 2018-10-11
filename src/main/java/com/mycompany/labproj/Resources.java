/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author miguel
 */
@Path("/resources")
public class Resources {
    
    @GET
    @Path("/5days")
    @Produces(MediaType.APPLICATION_JSON)
    public Response forecast5Days(@FormParam("location") String location) {
        return null;
    }
}
