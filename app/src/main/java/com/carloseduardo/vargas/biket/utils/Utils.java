package com.carloseduardo.vargas.biket.utils;

import android.content.Context;
import android.location.LocationManager;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static Context context;
    public static LocationManager locationManager;
    public static Locale BRAZIL = new Locale("pt", "BR");

    public Utils(Context context) {
        this.context = context;
    }

    public Utils() {
    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", BRAZIL);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date dbStringToDateForDb(String date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", BRAZIL);
        Date d = new Date();
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }

    public static String dbDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", BRAZIL);
        String format = dateFormat.format(date);
        return format;
    }

    public static boolean checkGPS() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean checkNetwork() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
