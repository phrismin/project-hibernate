package com.example.hibernateproject.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate localDate) {

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(localDate, LocalDate.now());
    }
}
