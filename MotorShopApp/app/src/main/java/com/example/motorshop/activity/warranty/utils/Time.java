package com.example.motorshop.activity.warranty.utils;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Timestamp;
import java.util.Date;

public class Time {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String timeStamp(){
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String strDate = sdf.format(date);
        String time = sdf.format(new Date(timeStamp.getTime()));
        return time;
    }
}
