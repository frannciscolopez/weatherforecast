package com.weather.api.weatherforecast.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.weatherforecast.model.WeatherForecast;
import com.weather.api.weatherforecast.service.WeatherForecastService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/weather-forecast")
@RequiredArgsConstructor
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;

    @PostMapping(value = "/save-city-weather/{cityName}")
    public ResponseEntity persistCityWeather(@PathVariable String cityName) throws Exception {
        return weatherForecastService.saveCityWeatherForecast(cityName);
    }

    @GetMapping(value = "/fetching-city-weather/{cityName}")
    public ResponseEntity fetchingCityWeather(@PathVariable String cityName) throws Exception {
        WeatherForecast weatherForecast = weatherForecastService.fetchingCityWeatherForecast(cityName);

        return Optional.ofNullable(ResponseEntity.ok(weatherForecast))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/fetching-all-cities-weather")
    public ResponseEntity fetchingAllCitiesWeather() throws Exception {
        List < WeatherForecast > allCitiesWeatherForecast = weatherForecastService.fetchingAllCitiesWeatherForecast();

        return Optional.ofNullable(ResponseEntity.ok(allCitiesWeatherForecast))
            .orElse(ResponseEntity.notFound().build());
    }


}