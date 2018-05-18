package com.utils;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.text.DateFormat;

public class CustomSqlEditor extends CustomDateEditor {

    public CustomSqlEditor(DateFormat dateFormat, boolean allowEmpty) {
        super(dateFormat, allowEmpty);
    }

    @Override
    public void setAsText(@Nullable String text) throws IllegalArgumentException {
        if (DataParser.parseDate(text) != null) {
            setValue(Date.valueOf(text));
        }
    }
}