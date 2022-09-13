package com.blocdao.project.controller.converter;

import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;


public class StringToRecruitmentTypeEnumConverter implements Converter<String, RecruitmentType> {

    @Override
    public RecruitmentType convert(String value) {
        try {
            return RecruitmentType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
