/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.naming.NamingException;
import javax.persistence.EntityManager;

/**
 *
 * @author hjmmanso
 */
public class WeatherManager extends WeatherManagerBase {

    private EntityManager entityManager;

    @Override
    public void saveWeatherInfo(WeatherHour wHour) throws NamingException {
        entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(wHour);
            System.out.println("saving");
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println(e.getStackTrace());
        }
    }
}
