package com.example.motorshop.activity.warranty.utils;

import android.util.Log;

public class SolveMath {
    public static int solveMath(String equation){
        int result = 0;String[] split;
        String newEquation = equation.replace(" ", "");
        Log.d("Math", newEquation);
            if (newEquation.contains("+")) {
                split = newEquation.split(" + ");
                result = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
            }
            if (newEquation.contains("-")) {
                split = newEquation.split(" - ");
                result = Integer.parseInt(split[0]) - Integer.parseInt(split[1]);
            }
            if (newEquation.contains("*")){
                split = newEquation.split(" * ");
                result = Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
            }
            if (newEquation.contains("/")){
                split = newEquation.split(" / ");
                result = Integer.parseInt(split[0]) / Integer.parseInt(split[1]);
            }
            else result = 0;
        return result;
    }
}
