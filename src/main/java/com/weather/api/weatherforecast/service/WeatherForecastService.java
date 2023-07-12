package com.weather.api.weatherforecast.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.api.weatherforecast.client.WeatherForecastClient;
import com.weather.api.weatherforecast.mapper.WeatherForecastMapper;
import com.weather.api.weatherforecast.model.WeatherData;
import com.weather.api.weatherforecast.model.WeatherForecast;
import com.weather.api.weatherforecast.repository.WeatherForecastRepository;
import com.weather.api.weatherforecast.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {

    private final WeatherForecastClient weatherForecastClient;
    private final WeatherForecastRepository weatherForecastRepository;

    private WeatherForecastMapper weatherForecastMapper = new WeatherForecastMapper();

    public ResponseEntity saveCityWeatherForecast(String cityName) throws JsonProcessingException {
        WeatherForecast repositoryCityWeatherForecast = weatherForecastRepository.findByCityName(cityName);
        ResponseEntity < String > clientResponse;

        try {
            clientResponse = weatherForecastClient.retrieveWeatherForecast(cityName);
        } catch (Exception ex) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }
        WeatherForecast clientCityWeatherForecast = weatherForecastMapper.converToWeatherForecast(clientResponse, cityName);

        if (repositoryCityWeatherForecast == null) {
            weatherForecastRepository.save(clientCityWeatherForecast);
        } else {
            updateIfThereIsYesterdaysWeatherForecastSaved(clientCityWeatherForecast, repositoryCityWeatherForecast);
        }


        return new ResponseEntity < > (HttpStatus.ACCEPTED);
    }

    public WeatherForecast fetchingCityWeatherForecast(String cityName) {
        WeatherForecast weatherForecast = weatherForecastRepository.findByCityName(cityName);
        return weatherForecast;
    }

    public List < WeatherForecast > fetchingAllCitiesWeatherForecast() {
        List < WeatherForecast > allCitiesWeatherForecast = weatherForecastRepository.findAll();
        allCitiesWeatherForecast.sort(Comparator.comparing(WeatherForecast::getCityName));
        return allCitiesWeatherForecast;
    }

    private void updateIfThereIsYesterdaysWeatherForecastSaved(WeatherForecast clientCityWeatherForecast, WeatherForecast repositoryCityWeatherForecast) {

        boolean sameDataSaved = 
            repositoryCityWeatherForecast.getWeatherData()
            .stream()
            .map(WeatherData::getDate)
            .allMatch(clientCityWeatherForecast.getWeatherData().stream()
            .map(WeatherData::getDate)
            .collect(Collectors.toList())::contains);


        if (!sameDataSaved) {
            WeatherData todayWeatherData =
                clientCityWeatherForecast.getWeatherData()
                .stream()
                .filter(weatherData -> !Utils.dateIsToday(weatherData.getDate()))
                .findFirst().get();

                repositoryCityWeatherForecast.getWeatherData().stream()
                .filter(weatherData-> !Utils.dateIsToday(weatherData.getDate()))
                .forEach(weatherData -> {
                    weatherData.setDate(todayWeatherData.getDate());
                    weatherData.setDayWeatherData(todayWeatherData.getDayWeatherData());
                });

                weatherForecastRepository.updateWeatherForecast(repositoryCityWeatherForecast.getCityName(), repositoryCityWeatherForecast);
                
        }

    }

}