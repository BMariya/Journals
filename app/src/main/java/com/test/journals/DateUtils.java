package com.test.journals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public static Calendar toCalendar(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String toDisplay(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        return dateFormat.format(date.getTime());
    }
}
