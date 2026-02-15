package com.example.StudentCourseApplication.Entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    Male,
    Female,
    Other;

    @JsonCreator
    public static Gender from(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
