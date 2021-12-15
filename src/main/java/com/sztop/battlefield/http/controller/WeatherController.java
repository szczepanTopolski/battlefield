package com.sztop.battlefield.http.controller;

import com.sztop.battlefield.http.model.ForecastDto;
import com.sztop.battlefield.http.model.WeatherDto;
import com.sztop.battlefield.http.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Profile("http")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDto getWhether(@RequestParam(required = false) String city){
        city = city != null ? city : "warszawa";
        return weatherService.getWhether(city);
    }

    @GetMapping("/forecast")
    public ForecastDto getForecast(@RequestParam(required = false) Float lan, @RequestParam(required = false) Float lon){
        lan = lan != null ? lan : 52.23F;  // Default for warsaw
        lon = lon != null ? lon : 21.01F;  // Default for warsaw
        return weatherService.getForecast(lan, lon);
    }
}
