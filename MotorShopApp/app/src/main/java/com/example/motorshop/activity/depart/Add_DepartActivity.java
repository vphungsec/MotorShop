package com.example.motorshop.activity.depart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Depart;

import org.json.JSONException;
import org.json.JSONObject;


public class Add_DepartActivity extends AppCompatActivity {
    Depart depart;
   // DBManager db = new DBManager(this);
    EditText etMaBPThem,etTenBPThem;
    Button btnXacNhanThemBP;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_depart);
        setControl();

        btnXacNhanThemBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maBPThem = etMaBPThem.getText().toString();
                String tenBPThem = etTenBPThem.getText().toString();
                String url = "http://172.168.86.127:8080/api/motorshop/departs";
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                JSONObject object = new JSONObject();
                try {
                    //input your API parameters
                    object.put("name",etMaBPThem.getText().toString());
                    object.put("amount",etTenBPThem.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url,object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("Response is: "+ response.toString().substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "onErrorResponse: " + error.getMessage());
                    }

                }

                )/*{
                   *//* @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("id",maBPThem);
                        parameters.put("name",tenBPThem);
                        return  parameters;
                    }*//*
                }*/;
                requestQueue.add(objectRequest);

                        Intent intent = new Intent(Add_DepartActivity.this, DepartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etMaBPThem = findViewById(R.id.etMaBPThem);
        etTenBPThem = findViewById(R.id.etTenBPThem);
        btnXacNhanThemBP = findViewById(R.id.btnXacNhanThemBP);
    }

}
