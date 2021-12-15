package com.sztop.battlefield.http.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OpenWeatherForecastDto {

    private List<OpenWeatherWeatherDescriptionDto> daily;

}
