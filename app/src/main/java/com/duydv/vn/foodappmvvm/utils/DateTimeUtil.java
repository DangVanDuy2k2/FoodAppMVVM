package com.duydv.vn.foodappmvvm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    private static final String DEFAULT_FORMAT_DATE = "dd-MM-yyyy,hh:mm a";

    public static String convertTimeStampToDate(long time) {
        String strDate = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMAT_DATE, Locale.ENGLISH);
            Date date = (new Date(time));
            strDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }
}
