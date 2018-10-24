package com.mycompany.labproj;

import com.mycompany.labproj.kafka.KProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
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
 * Get and parse data from OpenWeather
 * @author Henrique Manso Nº 65308
 * @author Miguel Correia Nº69892
 */
public class WeatherResources {

    public static final String ACCESS_KEY = "2ab81415f3744d38bb015dd0f7f2c8c6";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String ENDPOINT = "forecast";
    public static final String CURRENT = "weather";
    public static final String METRIC = "&units=metric";
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    public static final String HTML_HEAD = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "<title>Start Page</title>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
            + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n"
            + "</head>\n"
            + "<body>\n"
            + "<div class=\"jumbotron\">\n"
            + "<h1 class=\"display-4\"> P308 - OpenWeather </h1>\n";
    public static final String HTML_END = "</div>\n"
            + "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n"
            + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n"
            + "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n"
            + "</body>\n"
            + "</html>";

    public List<WeatherHour> sendRequestForWeatherInfo(String city) {

        List<WeatherHour> reportWeather = new ArrayList<>();
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?q=" + city + "&appid=" + ACCESS_KEY + METRIC);
        WeatherManager wManager = new WeatherManager();

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            JSONObject weatherReport = new JSONObject(EntityUtils.toString(entity));
            JSONArray report = weatherReport.getJSONArray("list");
            KProducer producer = new KProducer();

            double temp = report.getJSONObject(0).getJSONObject("main").getDouble("temp");
            String date = "";
            for (int i = 0; i < report.length(); i++) {
                JSONObject tmp = report.getJSONObject(i);
                if(Math.abs(tmp.getJSONObject("main").getDouble("temp") - temp ) > 5) {
                        producer.sendMessage("Alarm! Temperature difference greater than 5º between "+date+" and "+tmp.getString("dt_txt"));
                }
                temp = tmp.getJSONObject("main").getDouble("temp");
                double temp_min = tmp.getJSONObject("main").getDouble("temp_min");
                double temp_max = tmp.getJSONObject("main").getDouble("temp_max");
                String description = tmp.getJSONArray("weather").getJSONObject(0).getString("description");
                date = tmp.getString("dt_txt");
                String unique = city.concat(date);
                WeatherHour wHour = new WeatherHour(temp, temp_min, temp_max, date, description, city, unique);
                wManager.saveWeatherInfo(wHour);
                reportWeather.add(wHour);
            }

            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return reportWeather;
    }

    public WeatherHour sendRequestForWeatherCurrent(String city) {

        HttpGet get = new HttpGet(BASE_URL + CURRENT + "?q=" + city + "&appid=" + ACCESS_KEY + METRIC);

        WeatherHour wHour = null;

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            JSONObject weatherReport = new JSONObject(EntityUtils.toString(entity));
            System.out.println(weatherReport);
            double temp = weatherReport.getJSONObject("main").getDouble("temp");
            double temp_min = weatherReport.getJSONObject("main").getDouble("temp_min");
            double temp_max = weatherReport.getJSONObject("main").getDouble("temp_max");
            String description = weatherReport.getJSONArray("weather").getJSONObject(0).getString("description");
            wHour = new WeatherHour(temp, temp_min, temp_max, description, city);
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wHour;
    }

    public String currentHTML(WeatherHour whour) {

        StringBuilder body = new StringBuilder();
        body.append(HTML_HEAD);
        body.append("<p> Location : ");
        body.append(whour.getLocal());
        body.append("</p>");
        body.append("<p> Current temp: ");
        body.append(whour.getTemp());
        body.append(" ºC</p>");
        body.append("<p> Min temp: ");
        body.append(whour.getTempMin());
        body.append(" ºC</p>");
        body.append("<p> Max temp: ");
        body.append(whour.getTempMax());
        body.append(" ºC</p>");
        body.append("<p> Weather condition: ");
        body.append(whour.getDescription());
        body.append("</p>");
        body.append(HTML_END);
        String finalHTML = body.toString();
        return finalHTML;
    }

    public String forecastHTML(List<WeatherHour> whour) {

        StringBuilder body = new StringBuilder();
        body.append(HTML_HEAD);
        body.append("<p class=\"lead\">").append("Location ").append(whour.get(1).getLocal()).append("</p>");
        body.append(
                 "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>Min Temp</th>"
                + "<th>Max Temp</th>"
                + "<th>Date</th>"
                + "<th>Description</th>"
                + "</tr>"
                + "</thead>"
        );
        for (int i = 0; i < whour.size(); i++) {
            body.append("<tr><td>")
                    .append(whour.get(i).getTempMin())
                    .append(" ºC</td><td>")
                    .append(whour.get(i).getTempMax())
                    .append(" ºC</td><td>")
                    .append(whour.get(i).getDateW())
                    .append("</td><td>")
                    .append(whour.get(i).getDescription())
                    .append("</td></tr>");
        }
        body.append(HTML_END);
        String finalHTML = body.toString();
        return finalHTML;
    }
}
