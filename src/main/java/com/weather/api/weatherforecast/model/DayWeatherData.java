package com.weather.api.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayWeatherData {
    @JsonProperty("maxtemp_c")
    private float maxtemp_c;
    @JsonProperty("mintemp_c")
    private float mintemp_c;
    @JsonProperty("avghumidity")
    private float avghumidity;
    @JsonProperty("totalprecip_mm")
    private float totalprecip_mm;
    @JsonProperty("condition")
    private Condition condition;
}
