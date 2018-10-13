/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author miguel
 */
public class WeatherResources {
    
    public static final String ACCESS_KEY = "2ab81415f3744d38bb015dd0f7f2c8c6";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String ENDPOINT = "forecast";
    public static final String METRIC = "&units=metric";
    static CloseableHttpClient httpClient = HttpClients.createDefault();


    public List<WeatherHour> sendRequestForWeatherInfo(String city) {
        
        List<WeatherHour> reportWeather = new ArrayList<>();
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?q=" + city + "&appid=" + ACCESS_KEY + METRIC);
        
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            
            JSONObject weatherReport = new JSONObject(EntityUtils.toString(entity));
            
            JSONArray report = weatherReport.getJSONArray("list");
           
            for(int i = 0; i < report.length();i++) {
                JSONObject tmp = report.getJSONObject(i);
                double temp = tmp.getJSONObject("main").getDouble("temp");
                double temp_min = tmp.getJSONObject("main").getDouble("temp_min");
                double temp_max = tmp.getJSONObject("main").getDouble("temp_max");
                String description = tmp.getJSONArray("weather").getJSONObject(0).getString("description");
                String date = tmp.getString("dt_txt");
                WeatherHour wHour = new WeatherHour(temp,temp_min,temp_max,date,description);
                reportWeather.add(wHour);
            }

            response.close();
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParseException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }
        
        return reportWeather;
    }  
}
