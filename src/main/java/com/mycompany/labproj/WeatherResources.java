/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj;

import java.io.IOException;
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


    private static void sendLiveRequest(String city) {
        
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?q=" + city + "&appid=" + ACCESS_KEY + METRIC);
        
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            
            JSONObject weatherReport = new JSONObject(EntityUtils.toString(entity));
            
            System.out.println(weatherReport.toString());
            System.out.println(weatherReport.getJSONArray("list"));
            JSONArray report = weatherReport.getJSONArray("list");
            for(int i = 0; i < report.length();i++) {
                System.out.println(report.getJSONObject(i).getJSONObject("main"));
                System.out.println(report.getJSONObject(i).getJSONArray("weather"));
                JSONArray tmp = report.getJSONObject(i).getJSONArray("weather");
                System.out.println(tmp.getJSONObject(0).get("description"));
            }
            
            for(int i = 0; i < report.length();i++) {
                WeatherHour tmp;
                double temp = report.getJSONObject(i).getJSONObject("main").getDouble("temp");
                double temp_min = report.getJSONObject(i).getJSONObject("main").getDouble("temp_min");
                double temp_max = report.getJSONObject(i).getJSONObject("main").getDouble("temp_max");
                //String description = report
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
    }
    
    public static void main(String[] args) {
        sendLiveRequest("Aveiro,pt");
    }
}
