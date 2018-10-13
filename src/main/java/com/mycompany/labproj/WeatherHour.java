/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@XmlRootElement
public class WeatherHour {
    
    private double temp;
    private double temp_min;
    private double temp_max;
    private String date;
    private String description;
    
    public WeatherHour(double temp, double temp_min, double temp_max, String date, String description) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.date = date;
        this.description = description;
    }
    
    public void setTemp(double temperature) {
        temp = temperature;
    }
    
    public double getTemp() {
        return temp;
    }
    
    public void setTempMax(double temperature) {
        temp_max = temperature;
    }
    
    public double getTempMax() {
        return temp_max;
    }
    
    public void setTempMin(double temperature) {
        temp_min = temperature;
    }
    
    public double getTempMin() {
        return temp_min;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDate() {
        return date;
    }
}
