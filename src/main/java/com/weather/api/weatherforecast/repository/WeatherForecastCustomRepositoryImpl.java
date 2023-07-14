package com.weather.api.weatherforecast.repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.weather.api.weatherforecast.model.WeatherForecast;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WeatherForecastCustomRepositoryImpl implements WeatherForecastCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateWeatherForecast(String cityName, WeatherForecast weatherForecast) {
        Query query = new Query(Criteria.where("cityName").is(cityName));
        Update update = new Update();
        update.set("weatherData", weatherForecast.getWeatherData());
        mongoTemplate.upsert(query, update, WeatherForecast.class);
    }
}