package com.weather.api.weatherforecast.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class WeatherForecast {
    private String cityName;
    private List<WeatherData> weatherData;
}
