package com.luizalabs.tracking.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEnumConverter implements Converter<String, StatusSchedule> {

    @Override
    public StatusSchedule convert(String source) {
        try {
            return StatusSchedule.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
}