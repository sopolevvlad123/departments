package com.utils;

import java.sql.Date;
import java.util.regex.Pattern;

public class DataParser {

    private static Integer parseInteger(String num) {
        if (num == null || !(Pattern.matches("[0-9]+", num) && num.length() < 10)) {
            return null;
        }
        return Integer.parseInt(num);
    }

    static Date parseDate(String date) {
        try {
            return Date.valueOf(date);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isIDValid(String id) {
        return parseInteger(id) != null;
    }
}
