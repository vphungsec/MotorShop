package com.example.motorshop.activity.depart;

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
import com.example.motorshop.activity.staff.StaffActivity;
import com.example.motorshop.activity.staff.VolleySTSingle;
import com.example.motorshop.datasrc.Depart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DepartActivity extends AppCompatActivity {
    TextView tvIdDP, tvNameDP;
    MenuItem mnThemBP, mnSuaBP, mnXoaBP;
    ImageButton ibQLKH1,ibQLNV1,ibQLBP1;

    RecyclerView rvDataDP;
    private List<Depart> dataDP ;
    private RequestQueue requestQueue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart);
        setControl();
        setControl();

        rvDataDP.setHasFixedSize(true);
        rvDataDP.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySTSingle.getmInstance(this).getRequestQueue();
        dataDP = new ArrayList<>();
        LoadDP();

    }

    private void LoadDP(){
        String url = "http://172.168.86.127:8080/api/motorshop/departs";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");

                                Depart depart = new Depart(id,name);
                                dataDP.add(depart);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            DepartAdapter departAdapter = new DepartAdapter(DepartActivity.this,  dataDP);
                            rvDataDP.setAdapter(departAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DepartActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void setControl() {
        tvIdDP = findViewById(R.id.tvIdDP);
        tvNameDP = findViewById(R.id.tvNameDP);

        rvDataDP = findViewById(R.id.rvDataDP);
        mnThemBP = findViewById(R.id.mnThemBP);
        mnXoaBP = findViewById(R.id.mnXoaBP);
        mnSuaBP = findViewById(R.id.mnSuaBP);

        ibQLKH1 = findViewById(R.id.ibQLKH1);
        ibQLNV1 = findViewById(R.id.ibQLNV1);
        ibQLBP1 = findViewById(R.id.ibQLBP1);

        ibQLKH1.setOnClickListener(onClickListener);
        ibQLBP1.setOnClickListener(onClickListener);
        ibQLNV1.setOnClickListener(onClickListener);
    }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ibQLNV1:
                        Intent intentQLNV = new Intent(DepartActivity.this, StaffActivity.class);
                        startActivity(intentQLNV);
                        break;
                    case R.id.ibQLKH1:
                        Intent intentQLKH = new Intent(DepartActivity.this, CustomerActivity.class);
                        startActivity(intentQLKH);
                        break;
                }
            }
        };

}