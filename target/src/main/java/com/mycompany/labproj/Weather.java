/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author miguel
 */
@XmlRootElement
public class Weather {
    
    private String id;
    private String location;
    private List<WeatherHour> weatherbyhour;
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setWeatherHour(List<WeatherHour> tmp) {
        weatherbyhour = tmp;
    }
    
    public List<WeatherHour> getWeatherByHour() {
        return weatherbyhour;
    }
}
