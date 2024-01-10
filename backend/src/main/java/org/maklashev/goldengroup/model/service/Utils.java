package org.maklashev.goldengroup.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDateTime convertStringToDate(String dateValue){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateValue, formatter).atTime(8, 0);
    }

    public static LocalDateTime convertStringToStartOfTheDay(String dateValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateValue, formatter).atTime(0, 0);
    }

    public static LocalDateTime convertToMonth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return LocalDate.parse(date, formatter).atTime(0, 0);
    }
    public static LocalDateTime convertToYear(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return LocalDate.parse(date, formatter).atTime(0, 0);
    }
}
