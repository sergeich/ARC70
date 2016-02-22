package com.sergeich.autoradioclub.app.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryDate {
    private static final ThreadLocal<DateFormat> DF = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private final Date date;

    public QueryDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return DF.get().format(date);
    }
}