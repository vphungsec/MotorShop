package com.example.motorshop.activity.staff;

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
import com.example.motorshop.activity.customer.CustomerActivity;
import com.example.motorshop.activity.depart.DepartActivity;
import com.example.motorshop.datasrc.Staff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaffActivity extends AppCompatActivity {
    TextView tvIdST, tvNameST, tvPhoneST,tvPassST,tvDateST,tvDepartST;
    MenuItem mnThemNV, mnSuaNV, mnXoaNV;
    ImageButton ibQLKH,ibQLNV,ibQLBP;

    RecyclerView rvDataST;
    private  List<Staff> dataST ;
    //ArrayList<Staff> dataST;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_staff);
        setControl();

        rvDataST.setHasFixedSize(true);
        rvDataST.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySTSingle.getmInstance(this).getRequestQueue();
        dataST = new ArrayList<>();
        LoadST();
    }
    public void LoadST(){
        String url = "http://172.168.86.127:8080/api/motorshop/staffs";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(url);
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject  jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String phone = jsonObject.getString("phone");
                                String passWord = jsonObject.getString("passWord");
                                String createdDate = jsonObject.getString("createdDate");
                                String dePartId = jsonObject.getString("departId");
                                Staff staff = new Staff(id,name,phone,passWord,createdDate,dePartId);
                                dataST.add(staff);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //suwar lai
                            StaffAdapter staffAdapter = new StaffAdapter(StaffActivity.this, R.layout.item_staff,dataST);
                            rvDataST.setAdapter(staffAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(StaffActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                }
        });
        requestQueue.add(jsonArrayRequest);
    }






    private void setControl() {
        tvIdST= findViewById(R.id.tvIdST);
        tvNameST= findViewById(R.id.tvNameST);
        tvPhoneST= findViewById(R.id.tvPhoneST);
        tvPassST= findViewById(R.id.tvPassST);
        tvDateST= findViewById(R.id.tvDateST);
        tvDepartST= findViewById(R.id.tvDepartST);

        rvDataST = findViewById(R.id.rvDataST);
        mnThemNV = findViewById(R.id.mnThemNV);
        mnXoaNV = findViewById(R.id.mnXoaNV);
        mnSuaNV = findViewById(R.id.mnSuaNV);

        ibQLKH = findViewById(R.id.ibQLKH);
        ibQLNV = findViewById(R.id.ibQLNV);
        ibQLBP = findViewById(R.id.ibQLBP);

        ibQLKH.setOnClickListener(onClickListener);
        ibQLBP.setOnClickListener(onClickListener);
        ibQLNV.setOnClickListener(onClickListener);
    }
        ImageButton.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ibQLBP:
                        Intent intentQLBP = new Intent(StaffActivity.this, DepartActivity.class);
                        startActivity(intentQLBP);
                        break;
                    case R.id.ibQLKH:
                        Intent intentQLKH = new Intent(StaffActivity.this, CustomerActivity.class);
                        startActivity(intentQLKH);
                        break;
                }
            }
        };

}



