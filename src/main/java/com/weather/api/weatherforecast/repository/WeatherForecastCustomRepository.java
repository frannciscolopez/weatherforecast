package com.weather.api.weatherforecast.repository;

import org.springframework.stereotype.Repository;

import com.weather.api.weatherforecast.model.WeatherForecast;

@Repository
public interface WeatherForecastCustomRepository {
    void updateWeatherForecast(String cityName, WeatherForecast weatherForecast);

}