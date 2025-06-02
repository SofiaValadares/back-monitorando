package br.com.cesarschool.domain.entity.enums;

import java.util.Arrays;

public enum WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static WeekDay fromString(String dayString) {
        return Arrays.stream(WeekDay.values())
                .filter(day -> day.name().equalsIgnoreCase(dayString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid day: " + dayString));
    }
}
