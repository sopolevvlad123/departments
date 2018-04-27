package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class RequestDataParser {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public static Integer parseInteger(String num) {
        if (num == null || !(Pattern.matches("[0-9]+", num) && num.length() < 10)) {
            return null;
        }
        return Integer.parseInt(num);
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return new Date(0,0,0);
        }
        sdf.setLenient(false);
        Date validDate;
        try {
             validDate = sdf.parse(date);
        } catch (ParseException e) {
            return new Date(0,0,0);
        }
        return validDate;
    }
}
