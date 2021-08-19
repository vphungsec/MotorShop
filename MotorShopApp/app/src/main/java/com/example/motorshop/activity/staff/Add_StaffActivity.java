package com.example.motorshop.activity.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Staff;

import java.util.HashMap;
import java.util.Map;


public class Add_StaffActivity extends AppCompatActivity {
    Staff staff;
    EditText etAddNameST,etAddPhoneST,etAddPassST,etAddDeParIdST;
    Button btnAddST;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_staff);
        setControl();
        //setEvent();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnAddST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addNameST = etAddNameST.getText().toString();
                String addPhoneST = etAddPhoneST.getText().toString();
                String addPassST = etAddPassST.getText().toString();
                String addDepartIdST = etAddDeParIdST.getText().toString();
                String url = "http://172.168.86.127:8080/api/motorshop/staffs";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }

                ){
                    @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("name",addNameST);
                        parameters.put("phone",addPhoneST);
                        parameters.put("passWord",addPassST);
                        parameters.put("departId",addDepartIdST);
                        return  parameters;
                    }
                };
               requestQueue.add(stringRequest);
                //InsertNV(staff);
                Intent intent = new Intent(Add_StaffActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });

    }

   /* private void setEvent() {
        btnAddST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addNameST = etAddNameST.getText().toString();
                String addPhoneST = etAddPhoneST.getText().toString();
                String addPassST = etAddPassST.getText().toString();
                String addDepartIdST = etAddDeParIdST.getText().toString();
                String url = "http://172.168.86.127:8080/api/motorshop/staffs";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
                InsertNV(staff);
                Intent intent = new Intent(Add_StaffActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });
    }*/

    private void setControl() {
        etAddNameST = findViewById(R.id.etAddNameST);
        etAddPhoneST = findViewById(R.id.etAddPhoneST);
        etAddPassST= findViewById(R.id.etAddPassST);
        etAddDeParIdST = findViewById(R.id.etAddDeParIdST);
        btnAddST = findViewById(R.id.btnAddST);
    }

    public void InsertNV(Staff Staff){
        staff = new Staff();
        staff.setName( etAddNameST.getText().toString().trim());
        staff.setPhone(etAddPhoneST.getText().toString().trim());
        staff.setPassword(etAddPassST.getText().toString().trim());
        staff.setDepartId(etAddDeParIdST.getText().toString().trim());

    }
}