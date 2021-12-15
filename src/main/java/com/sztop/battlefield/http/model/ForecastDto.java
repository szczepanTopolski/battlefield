package com.sztop.battlefield.http.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ForecastDto {

    private List<List<String>> forecast;
}
