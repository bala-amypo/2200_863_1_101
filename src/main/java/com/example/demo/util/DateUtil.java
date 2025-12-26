package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    public static LocalDate today() {
        return LocalDate.now();
    }
    
    public static boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }
}