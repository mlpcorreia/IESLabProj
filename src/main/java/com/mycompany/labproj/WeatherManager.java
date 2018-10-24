/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.naming.NamingException;
import javax.persistence.EntityManager;

/**
 * Save data to the database
 * @author Henrique Manso Nº 65308
 * @author Miguel Correia Nº69892
 */
public class WeatherManager extends WeatherManagerBase {

    private EntityManager entityManager;

    @Override
    public void saveWeatherInfo(WeatherHour wHour) throws NamingException {
        entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            WeatherHour tmp = entityManager.find(WeatherHour.class, wHour.getUniqueLocalDate());
            if (tmp != null) {
                entityManager.close();
                fullUpdate(tmp, wHour);
            } else {
                entityManager.persist(wHour);
                System.out.println("saving");
                entityManager.getTransaction().commit();
                entityManager.close();
            }

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.getStackTrace();
        }
    }

    @Override
    public void fullUpdate(WeatherHour tmp, WeatherHour whour) throws NamingException {
        entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            tmp.setDateW(whour.getDateW());
            tmp.setDescription(whour.getDescription());
            tmp.setTemp(whour.getTemp());
            tmp.setTempMax(whour.getTempMax());
            tmp.setTempMin(whour.getTempMin());
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.getStackTrace();
        }
    }
}
