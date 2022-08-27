package com.blocdao.project.entity;

public enum RecruitmentType {
    STUDY("study"),
    PROJECT("project");

    private final String value;

    RecruitmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
