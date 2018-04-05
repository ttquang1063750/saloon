package com.product.nguyencongduc.bookticketapplication.utils;

/**
 * Created by nguyencongduc on 4/2/18.
 */


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GUMI-DUC on 9/5/17.
 * Using for format time.
 */

public class DateFormatUtils {

    private static DateFormatUtils instance;
    public static final String PARAMS_INPUT_1 = "YYYY-MM-dd HH:mm:ss";
    public static final String PARAMS_INPUT_2 = "YYYY-MM-dd'T'hh:mm:ss.SSSZ";
    public static final String PARAMS_INPUT_3 = "hh:mm";
    public static final String PARAMS_INPUT_4 = "YYYY-MM-dd";

    public static DateFormatUtils getInstance() {
        if (instance == null) {
            instance = new DateFormatUtils();
        }
        return instance;
    }

    /**
     * Using for convert 1 string date to another format string date
     * @param paramsOutput date format output
     * @param paramsInput date format input
     * @param dateInput string date
     * @return
     */
    public String stringToString(String paramsOutput, String paramsInput, String dateInput) {
        SimpleDateFormat dateFormatInput = new SimpleDateFormat(paramsInput);
        SimpleDateFormat dateFormatOutPut = new SimpleDateFormat(paramsOutput);
        try {
            Date date = dateFormatInput.parse(dateInput);
            String dateOutput = dateFormatOutPut.format(date);
            return dateOutput;
        } catch (Exception e) {
            Log.d("Exception ", e.toString());
        }
        return "";
    }

    /**
     * Convert string to date
     * @param dateString
     * @param paramsInput
     * @return
     */
    public Date getDateFromString(String dateString, String paramsInput) {
        Log.d("Start_time_1", dateString + "");
        SimpleDateFormat dateFormat = new SimpleDateFormat(paramsInput);
        try {
            Date date = dateFormat.parse(dateString);
            return date;
        }catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return new Date();
    }

    /**
     * Convert date to string
     * @param date
     * @param paramOutput
     * @return string date with date format you want.
     */
    public String getStringFromDate(Date date, String paramOutput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(paramOutput);
        try {
            String dateOutput = dateFormat.format(date);
            return dateOutput;
        }catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return "";
    }
}

