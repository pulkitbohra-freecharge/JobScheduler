package com.JobSchedulingNotification.JobSchedulingProject.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ApiListConverter implements AttributeConverter<List<String>,String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute){

        if(attribute==null || attribute.isEmpty() ){
            return "";
        }
        return String.join(",",attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData){
        if(dbData == null || dbData.isEmpty()){
            return List.of();
        }

        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
