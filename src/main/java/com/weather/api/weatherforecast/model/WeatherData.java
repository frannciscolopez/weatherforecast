package com.weather.api.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("date")
    private String date;
    @JsonProperty("day")
    private DayWeatherData dayWeatherData;
}
