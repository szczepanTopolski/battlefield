package com.sztop.battlefield.mail;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("MapperUtils")
@Component
public class MapperUtils {

    private static final String EMAIL_SEPARATOR = ";";

    @Named("splitString")
    public String[] splitString(String s){
        if(s == null) return new String[0];

        return s.split(EMAIL_SEPARATOR);
    }
}
