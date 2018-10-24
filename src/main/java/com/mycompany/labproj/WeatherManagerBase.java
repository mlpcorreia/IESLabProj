/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * @author Henrique Manso Nº 65308
 * @author Miguel Correia Nº69892
 */
@Produces("application/json")
public abstract class WeatherManagerBase {

    protected EntityManager getEntityManager() throws NamingException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IES_Lab");
        return emf.createEntityManager();
    }
    @POST
    public void saveWeatherInfo(WeatherHour wHour) throws NamingException{   
    }
    
    @POST
    public void fullUpdate(WeatherHour tmp, WeatherHour whour) throws NamingException{
        
    }

}
