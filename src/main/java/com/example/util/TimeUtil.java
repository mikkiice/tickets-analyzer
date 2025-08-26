package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    public static LocalDateTime parse(String date, String time) {
        return LocalDateTime.parse(date + " " + time, FORMATTER);
    }
}
