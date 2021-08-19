package com.example.motorshop.activity.staff;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySTSingle {
    private RequestQueue requestQueue;
    private static VolleySTSingle mInstance;
    private VolleySTSingle(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static  synchronized VolleySTSingle getmInstance(Context context){
        if(mInstance == null){
         mInstance = new VolleySTSingle(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
