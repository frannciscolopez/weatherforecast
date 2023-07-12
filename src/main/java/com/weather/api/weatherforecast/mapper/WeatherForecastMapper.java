package com.weather.api.weatherforecast.mapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.weatherforecast.model.WeatherData;
import com.weather.api.weatherforecast.model.WeatherForecast;

public class WeatherForecastMapper {

    public WeatherForecast converToWeatherForecast(ResponseEntity < String > response, String cityName) throws JsonMappingException, JsonProcessingException {
        JsonNode node = new ObjectMapper().readTree(response.getBody());

        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast.setCityName(cityName);
        JsonNode forecastDay = node.get("forecast").get("forecastday");
        List < WeatherData > weatherData = mapWeatherData(forecastDay);


        weatherForecast.setWeatherData(weatherData);
        return weatherForecast;
    }

    public List < WeatherData > mapWeatherData(JsonNode forecastDay) throws JsonMappingException, JsonProcessingException { //
        Iterator < JsonNode > fieldsIterator = forecastDay.elements();
        List < WeatherData > weatherData = new ArrayList();
        while (fieldsIterator.hasNext()) {
            JsonNode dayData = fieldsIterator.next();
            WeatherData weatherDataDay = new ObjectMapper().readValue(dayData.toString(), WeatherData.class);
            weatherData.add(weatherDataDay);
        }
        return weatherData;
    }

}