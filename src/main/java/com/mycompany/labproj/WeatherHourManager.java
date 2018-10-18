/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author hjmmanso
 */
public class WeatherHourManager {

    EntityManagerFactory emf = null;
    EntityManager entityManager = null;
    EntityTransaction transaction = null;

    public WeatherHourManager() {
        emf = Persistence.createEntityManagerFactory("weather-mySQL");
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void saveWeatherInfo(WeatherHour wHour) {
        try {
            transaction.begin();
            entityManager.persist(wHour);
            System.out.println("saving");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.err.println(e.getStackTrace());
        }
    }
}
