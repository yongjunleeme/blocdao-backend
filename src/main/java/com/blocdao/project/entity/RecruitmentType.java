package com.blocdao.project.entity;

public enum RecruitmentType {
    STUDY("STUDY"),
    PROJECT("PROJECT");

    private final String value;

    RecruitmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
