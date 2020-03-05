package com.example.thethirdapplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewsUtility {

    public static String apiKey = "4d02332f1b864beda94d60580952d46a";
    private static Calendar c;
    private static String currentDate;

    public static String getSpecificDate() {
        c = new GregorianCalendar();
        c.add(Calendar.MONTH, -1);
        Date date = c.getTime();
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        currentDate = df.format(date);
        return currentDate;
    }
}
