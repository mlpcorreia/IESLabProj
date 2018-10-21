/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@XmlRootElement
@Entity(name="P03G08")
public class WeatherHour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long Id;
    @Column(name="temp")
    private double temp;
    @Column(name="temp_min")
    private double temp_min;
    @Column(name="temp_max")
    private double temp_max;
    @Column(name="dateW")
    private String dateW;
    @Column(name = "description")
    private String description;
    @Column(name = "local")
    private String local;
    @Column(name = "uniqueLocalDate", unique = true)
    private String uniqueLocalDate;
    
    public WeatherHour(double temp, double temp_min, double temp_max, String dateW, String description, String local, String unique) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.dateW = dateW;
        this.description = description;
        this.local = local;
        this.uniqueLocalDate = unique;
    }

    public WeatherHour(double temp, double temp_min, double temp_max, String description, String local) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.dateW = dateW;
        this.description = description;
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public Long getId(){
        return Id;
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
    
    public void setDateW(String dateW) {
        this.dateW = dateW;
    }
    
    public String getDateW() {
        return dateW;
    }

    @Override
    public String toString() {
        return '{' + "temp :" + temp + ", temp_min :" + temp_min + ", temp_max :" + temp_max + ", date :" + dateW + ", description :" + description + '}'+"\n";
    }
    
}
