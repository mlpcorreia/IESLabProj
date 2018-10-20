/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author hjmmanso
 */
public class WeatherHourManager extends WeatherHourManagerBase{

    private EntityManager entityManager;
    //private EntityTransaction transaction;

    @Override
    public void saveWeatherInfo(WeatherHour wHour) throws NamingException {
        entityManager = getEntityManager();
        try {
            //transaction.begin();
            entityManager.getTransaction().begin();
            entityManager.persist(wHour);
            System.out.println("saving");
            entityManager.getTransaction().commit();
            //transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            //transaction.rollback();
            System.err.println(e.getStackTrace());
        }
        
    }
}
