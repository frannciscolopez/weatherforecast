package com.weather.api.weatherforecast.utils;
import java.time.LocalDate;
public class Utils {
    public static boolean dateIsToday(String date) {
        return LocalDate.now().toString()
            .equals(date);
    }
}