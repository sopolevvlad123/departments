package com.utils;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {


    public static Integer parseInteger(String num) {
        if (num == null || !(Pattern.matches("[0-9]+", num) && num.length() < 10)) {
            return null;
        }
        return Integer.parseInt(num);
    }


    public static Date parseDate(String date){
        System.out.println("date parser");
     try{
         return Date.valueOf(date);
     }catch (IllegalArgumentException e){
         return null;
     }

    }

    public static  boolean isIDValid(String id){
        return parseInteger(id) !=null;
    }
}
