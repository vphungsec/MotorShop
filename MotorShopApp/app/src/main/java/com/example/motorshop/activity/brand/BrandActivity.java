package com.example.motorshop.activity.brand;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Brand;
import com.example.motorshop.helper.Helper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BrandActivity extends AppCompatActivity {

    FloatingActionButton btnAddBR;
    ListView lvBrand;

    private Bundle extras;
    private ArrayList<Brand> data = new ArrayList<>();
    private BrandAdapter adapter;
    private final Helper h = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        getExtras(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void getExtras(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            extras = getIntent().getExtras();
        else
            extras = savedInstanceState;
    }

    public void sendExtras(Class className, String id) {
        Intent i = new Intent(getApplicationContext(), className);
        i.putExtra("id", id);
        startActivity(i);
    }

    private void setControl() {
        lvBrand = (ListView) findViewById(R.id.lvBrand);
        btnAddBR = (FloatingActionButton) findViewById(R.id.btnAddBR);
    }

    private void setEvent() {
        loadBrands();
        adapter = new BrandAdapter(this, R.layout.item_brand, data);    //important
        lvBrand.setAdapter(adapter);

        btnAddBR.setOnClickListener(v -> {
            sendExtras(BrandDetailActivity.class, null);
        });

        lvBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendExtras(BrandDetailActivity.class, data.get(position).getId());
            }
        });

        lvBrand.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), "Long Click: pos " + position, Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(BrandActivity.this);
            builder.setTitle("Confirm");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("YES", (dialog, which) -> {
                // Do nothing but close the dialog
                Toast.makeText(getApplicationContext(), "Choose YES Dialog: pos " + position, Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            });

            builder.setNegativeButton("NO", (dialog, which) -> {

                // Do nothing
                dialog.dismiss();
            });

            AlertDialog alert = builder.create();
            alert.show();
            return true;
        });

    }

    public void loadBrands() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://172.168.86.127:8080/api/motorshop/brands";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    if (!h.isNull(response)) {
                        loadBrandsSQL(response);
                    }
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "Cannot load brands list right now!", Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(stringRequest);
    }

    public void loadBrandsSQL(String response) {
        data.clear();
        response = response.substring(2, response.length() - 2).replace("},{", "~");

        String array[] = null;
        array = response.split("~");
        for(String str : array) {
            str = "{" + str + "}";
            Brand brand = new Gson().fromJson(str, Brand.class);
            data.add(brand);
//            System.out.println("brandId: " + brand.getId() + "\n" +
//                    "name: " + brand.getName() + "\n" +
//                    "address: " + brand.getAddress() + "\n" +
//                    "phone: " + brand.getPhone() + "\n" +
//                    "email: " + brand.getEmail() + "\n" +
//                    "logo: " + brand.getLogo() + "\n"
//            );
        }
        if(adapter != null) adapter.notifyDataSetChanged();
    }

    public void deleteBrand(Brand brand){
        loadBrands();
    }

    public void editBrand(Brand brand){
        loadBrands();
    }

}













//    public void testImage() {
////        //chuyển data imageview -> byte[]
////        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivPhoTo.getDrawable();
////        Bitmap bitmap = bitmapDrawable.getBitmap();
////        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
////        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
////        byte[] hinhAnh = byteArray.toByteArray();
//
////        //chuyển byte [] -> bitmap
////        byte[] hinhAnh = Byte.valueOf("abc");
////        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
////        ivXe.setImageBitmap(bitmap);
//    }























//    public void insertBrand(){
//        brand = new NhaCungCap();
//        brand.setMaNCC(etBRID.getText().toString().trim());
//        brand.setTenNCC(etBRName.getText().toString().trim());
//        new DBManager(BrandActivity.this).insertBR(brand);
//    }

//    public void updateBrand(){
//        String departmentID = etBRID.getText().toString().trim();
//        String departmentName = etBRName.getText().toString().trim();
//        if(!departmentID.equals("") && !departmentName.equals("")){
//            brand = new NhaCungCap();
//            brand.setMaNCC(departmentID);
//            brand.setTenNCC(departmentName);
//            new DBManager(BrandActivity.this).updateBR(brand);
//        }
//    }