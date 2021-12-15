package com.sztop.battlefield.http.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OpenWeatherWeatherDescriptionDto {

    private List<OpenWeatherForecastWeatherDto> weather;

}
