package com.weather.api.weatherforecast.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@PropertySource("classpath:application.properties")
public class WeatherForecastClient {
    @Value("${api.keyAuth}")
    private String keyAuth;
    @Value("${api.url}")
    private String url;
    
    public ResponseEntity < String > retrieveWeatherForecast(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity < String > response = restTemplate.getForEntity(
            "" + url + "?q=" + cityName + "&days=2&key=" + keyAuth + "",
            String.class);
        return response;
    }
}