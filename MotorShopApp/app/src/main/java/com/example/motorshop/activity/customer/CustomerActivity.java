package com.example.motorshop.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.motorshop.activity.R;

import com.example.motorshop.activity.depart.DepartActivity;
import com.example.motorshop.activity.staff.StaffActivity;
import com.example.motorshop.activity.staff.StaffAdapter;
import com.example.motorshop.activity.staff.VolleySTSingle;
import com.example.motorshop.datasrc.Customer;
import com.example.motorshop.datasrc.Staff;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    TextView tvIdCT,tvIndenCT, tvNameCT,tvAddCT ,tvPhoneCT,tvPassCT,tvDateCT;
    MenuItem mnThemKH, mnSuaKH, mnXoaKH;
    ImageButton ibQLKH2,ibQLNV2,ibQLBP2;

    RecyclerView rvDataCT;
    private List<Customer> dataCT ;
    private RequestQueue requestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setControl();

        rvDataCT.setHasFixedSize(true);
        rvDataCT.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySTSingle.getmInstance(this).getRequestQueue();
        dataCT = new ArrayList<>();
        LoadCT();
    }

    public void LoadCT() {
        String url = "http://172.168.86.127:8080/api/motorshop/customers";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String identityId = jsonObject.getString("identityId");
                                String name = jsonObject.getString("name");
                                String address = jsonObject.getString("address");
                                String phone = jsonObject.getString("phone");
                                String passWord = jsonObject.getString("passWord");
                                String createdDate = jsonObject.getString("createdDate");
                                Customer customer = new Customer(id,identityId,name,address,phone,passWord,createdDate);
                                dataCT.add(customer);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            CustomerAdapter customerAdapter = new CustomerAdapter(CustomerActivity.this,  dataCT);
                            rvDataCT.setAdapter(customerAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CustomerActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }



    private void setControl() {
       /* tvIdCT= findViewById(R.id.tvIdST);
        tvIndenCT= findViewById(R.id.tvIdenIdCT);
        tvNameCT= findViewById(R.id.tvNameCT);
        tvAddCT= findViewById(R.id.tvAddCT);
        tvPhoneCT= findViewById(R.id.tvPhoneCT);
        tvPassCT= findViewById(R.id.tvPassST);
        tvDateCT= findViewById(R.id.tvDateST);*/

        rvDataCT = findViewById(R.id.rvDataCT);
        mnThemKH = findViewById(R.id.mnThemKH);
        mnXoaKH = findViewById(R.id.mnXoaKH);
        mnSuaKH = findViewById(R.id.mnSuaKH);

        ibQLKH2 = findViewById(R.id.ibQLKH2);
        ibQLNV2 = findViewById(R.id.ibQLNV2);
        ibQLBP2 = findViewById(R.id.ibQLBP2);

        ibQLKH2.setOnClickListener(onClickListener);
        ibQLBP2.setOnClickListener(onClickListener);
        ibQLNV2.setOnClickListener(onClickListener);
    }
    ImageButton.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ibQLNV2:
                    Intent intentQLNV = new Intent(CustomerActivity.this, StaffActivity.class);
                    startActivity(intentQLNV);
                    break;
                case R.id.ibQLBP2:
                    Intent intentQLBP = new Intent(CustomerActivity.this, DepartActivity.class);
                    startActivity(intentQLBP);
                    break;
            }
        }
    };


}