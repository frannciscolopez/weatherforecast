package com.weather.api.weatherforecast.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.weather.api.weatherforecast.model.WeatherForecast;


@Repository
public interface WeatherForecastRepository extends MongoRepository < WeatherForecast, String >, WeatherForecastCustomRepository {

    WeatherForecast findByCityName(String cityName);

}