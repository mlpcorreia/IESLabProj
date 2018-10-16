/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author miguel
 */
@Path("resources")
public class Resources {

    //@Inject
    WeatherResources wResources = new WeatherResources();

    @POST
    @Path("5days")
    @Produces(MediaType.APPLICATION_JSON)
    public Response forecast5Days(@FormParam("location") String location) {
        System.out.println(location);
        List<WeatherHour> report;
        report = wResources.sendRequestForWeatherInfo(location);
        System.out.println(report);
        //GenericEntity<List<WeatherHour>> entity = new GenericEntity<List<WeatherHour>>(report) {};
        return Response.ok(report.toString()).build();
    }
}
