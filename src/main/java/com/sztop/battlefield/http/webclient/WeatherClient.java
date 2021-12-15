package com.sztop.battlefield.http.webclient;

import com.sztop.battlefield.http.model.ForecastDto;
import com.sztop.battlefield.http.model.WeatherDto;
import com.sztop.battlefield.http.webclient.dto.OpenWeatherForecastDto;
import com.sztop.battlefield.http.webclient.dto.OpenWeatherForecastWeatherDto;
import com.sztop.battlefield.http.webclient.dto.OpenWeatherWeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Component
public class WeatherClient {

    private final String WEATHER_URL;
    private final String API_KEY;
    private final RestTemplate restTemplate;

    public WeatherClient(
            @Value("${application.weather.url}") String WEATHER_URL,
            @Value("${application.weather.key}") String API_KEY,
            RestTemplate restTemplate
    ) {
        this.WEATHER_URL = WEATHER_URL;
        this.API_KEY = API_KEY;
        this.restTemplate = restTemplate;
    }

    public WeatherDto getWeatherForCity(String city) {
        OpenWeatherWeatherDto openWeatherDto = callGetForObject(
                "weather?q={city}&appid={apiKey}&units=metric&lang=pl",
                OpenWeatherWeatherDto.class,
                city,
                API_KEY);

        return WeatherDto.builder()
                .temperature(openWeatherDto.getMain().getTemp())
                .humidity(openWeatherDto.getMain().getHumidity())
                .pressure(openWeatherDto.getMain().getPressure())
                .windSpeed(openWeatherDto.getWind().getSpeed())
                .build();
    }

    public ForecastDto getForecast(float lat, float lon) {
        OpenWeatherForecastDto openWeatherDto = callGetForObject(
                "onecall?lat={lat}&lon={lon}&exclude=minutely,hourly&appid={API key}&units=metric&lang=pl",
                OpenWeatherForecastDto.class,
                lat,
                lon,
                API_KEY);


        return ForecastDto.builder()
                .forecast(openWeatherDto.getDaily()
                        .stream()
                        .map(o -> o.getWeather()
                                .stream()
                                .map(OpenWeatherForecastWeatherDto::getDescription)
                                .collect(Collectors.toList()))
                        .collect(Collectors.toList()))
                .build();
    }

    private <T> T callGetForObject(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url, responseType, objects);
    }
}
