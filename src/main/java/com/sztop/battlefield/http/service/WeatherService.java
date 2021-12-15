package com.sztop.battlefield.http.service;

import com.sztop.battlefield.http.model.WeatherDto;
import com.sztop.battlefield.http.webclient.WeatherClient;
import com.sztop.battlefield.http.model.ForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    @Cacheable(cacheNames = "CityWeather", key = "#city")
    public WeatherDto getWhether(String city){
        return weatherClient.getWeatherForCity(city);
    }

    public ForecastDto getForecast(Float lan, Float lon) {
        return weatherClient.getForecast(lan, lon);
    }
}
