/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import com.mycompany.labproj.kafka.KProducer;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Exposes web resources using REST
 * @author Henrique Manso Nº 65308
 * @author Miguel Correia Nº69892
 */
@Path("resources")
public class Resources {

    WeatherResources wResources = new WeatherResources();
    KProducer kProducer = new KProducer();

    @POST
    @Path("5days")
    @Produces(MediaType.TEXT_HTML)
    public Response forecast5Days(@FormParam("location") String location) {
        List<WeatherHour> report;
        report = wResources.sendRequestForWeatherInfo(location);
        String response = wResources.forecastHTML(report);
        return Response.ok(response).build();
    }
    
    @POST
    @Path("current")
    @Produces(MediaType.TEXT_HTML)
    public Response forecastCurrent(@FormParam("location") String location) {
        WeatherHour report;
        report = wResources.sendRequestForWeatherCurrent(location);
        String response = wResources.currentHTML(report);
        return  Response.ok(response).build();
    }
}
