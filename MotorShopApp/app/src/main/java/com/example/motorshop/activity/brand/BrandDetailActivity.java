package com.example.motorshop.activity.brand;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Brand;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.helper.Icon_Manager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class BrandDetailActivity extends AppCompatActivity {

    TextView tvBrandId, tvBrandRefresh, tvBrandSave;
    EditText etBrandName, etBrandAddress, etBrandPhone, etBrandEmail;
    ImageView imvBrandLogo;
    ProgressBar pbLoading;

    private Brand brand;
    private String imagePath = null;
    private Bundle extras;
    private final Helper h = new Helper();
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        getExtras(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        initInterface();
        setEvent();
    }

    public void getExtras(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            extras = getIntent().getExtras();
        else
            extras = savedInstanceState;
    }

    private void setControl() {
        tvBrandId = (TextView) findViewById(R.id.tvBrandId);
        etBrandName = (EditText) findViewById(R.id.etBrandName);
        etBrandAddress = (EditText) findViewById(R.id.etBrandAddress);
        etBrandPhone = (EditText) findViewById(R.id.etBrandPhone);
        etBrandEmail = (EditText) findViewById(R.id.etBrandEmail);
        imvBrandLogo = (ImageView) findViewById(R.id.imvBrandLogo);
        tvBrandRefresh = (TextView) findViewById(R.id.tvBrandRefresh);
        tvBrandSave = (TextView) findViewById(R.id.tvBrandSave);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoadingBrandDetail);
    }

    private void setEvent() {
        loadBrand();

        imvBrandLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, RESULT_LOAD_IMAGE);
            }
        });

        tvBrandRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etBrandName.setText(null);
                etBrandAddress.setText(null);
                etBrandPhone.setText(null);
                etBrandEmail.setText(null);
                imvBrandLogo.setImageURI(null);
            }
        });

        tvBrandSave.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                getBrand();
                if(!isNull(brand)) {
                    if(isValid(brand)) {
                        if(extras != null) {
                            if(h.isNull(brand.getId())) {
                                postBrand(brand);
                            } else {
                                putBrand(brand);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            imvBrandLogo.setBackground(null);
            Uri selectedImageUri = data.getData();
            imvBrandLogo.setImageURI(selectedImageUri);
            imagePath = data.getData().getPath();
        }
    }

    public void loadBrand() {
        if(extras != null) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "http://172.168.86.127:8080/api/motorshop/brands/id?id=" + extras.getString("id");

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (!h.isNull(response)) {
                            brand = new Gson().fromJson(response, Brand.class);
                            if(!isNull(brand)) {
                                tvBrandId.setText(brand.getId());
                                etBrandName.setText(brand.getName());
                                etBrandAddress.setText(brand.getAddress());
                                etBrandPhone.setText(brand.getPhone());
                                etBrandEmail.setText(brand.getEmail());
                                if(!h.isNull(brand.getLogo())) {
                                    File imgFile = new  File(brand.getLogo());
                                    if(imgFile.exists()){
                                        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                                        imvBrandLogo.setImageBitmap(bitmap);
                                    }
                                }
                            }
                        }
                    },
                    error -> {
                        Toast.makeText(getApplicationContext(), "Cannot load brands list right now!", Toast.LENGTH_SHORT).show();
                    }
            );
            queue.add(stringRequest);
        }
    }

    private void postBrand(Brand newBrand) {

        JSONObject object = new JSONObject();
        try {
            object.put("name", newBrand.getName());
            object.put("address", newBrand.getAddress());
            object.put("phone", newBrand.getPhone());
            object.put("email", newBrand.getEmail());
            object.put("logo", newBrand.getLogo());
            System.out.println("post: "+object.getString("logo"));
        } catch (JSONException e) {
            Log.d(BrandDetailActivity.class.toString(), e.getMessage());
        }

        String url = "http://172.168.86.127:8080/api/motorshop/brands/";
        pbLoading.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(BrandDetailActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(BrandDetailActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

//        String url = "http://172.168.86.127:8080/api/motorshop/brands";
//        pbLoading.setVisibility(View.VISIBLE);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                pbLoading.setVisibility(View.GONE);
//                Toast.makeText(BrandDetailActivity.this, "Brand added to API", Toast.LENGTH_SHORT).show();
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(BrandDetailActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("name", newBrand.getName());
//                params.put("address", newBrand.getAddress());
//                params.put("phone", newBrand.getPhone());
//                params.put("email", newBrand.getEmail());
//
//                return params;
//            }
//        };
        queue.add(jsonObjectRequest);
    }

    private void putBrand(Brand updatedBrand) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", updatedBrand.getId());
            object.put("name", updatedBrand.getName());
            object.put("address", updatedBrand.getAddress());
            object.put("phone", updatedBrand.getPhone());
            object.put("email", updatedBrand.getEmail());
            object.put("logo", updatedBrand.getLogo());
            System.out.println("put: "+object.getString("logo"));
        } catch (JSONException e) {
            Log.d(BrandDetailActivity.class.toString(), e.getMessage());
        }

        String url = "http://172.168.86.127:8080/api/motorshop/brands/";
        pbLoading.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(BrandDetailActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(BrandDetailActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

//        StringRequest request = new StringRequest(Request.Method.PUT, url,
//            new com.android.volley.Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(BrandDetailActivity.this, "Brand added to API", Toast.LENGTH_SHORT).show();
//                    pbLoading.setVisibility(View.GONE);
//                }
//            },
//            new com.android.volley.Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(BrandDetailActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                    pbLoading.setVisibility(View.GONE);
//                }
//            }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("id", updatedBrand.getId());
//                params.put("name", updatedBrand.getName());
//                params.put("address", updatedBrand.getAddress());
//                params.put("phone", updatedBrand.getPhone());
//                params.put("email", updatedBrand.getEmail());
//                params.put("logo", updatedBrand.getLogo());
////                if(updatedBrand.getLogo() != null)
////                    params.put("logo", updatedBrand.getLogo());
//
////                if(updatedBrand.getLogo() == null)
////                    params.put("logo", "");
////                else
////                    params.put("logo", new String(updatedBrand.getLogo()));
////
//                return params;
//            }
//        };
        queue.add(jsonObjectRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JSONArray readBytes(InputStream inputStream) throws IOException, JSONException {

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        JSONArray array=new JSONArray();
        int len = 0,i=0;
        while ((len = inputStream.read(buffer)) != -1) {

            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            byteBuffer.write(buffer, 0, len);
            byte[] b= byteBuffer.toByteArray();
            array.put(i,Base64.getEncoder().encodeToString(b));
            i++;
        }
        return array;
    }

    public void getBrand () {
        brand = new Brand();
        if(tvBrandId.getText().length() > 0)
            brand.setId(tvBrandId.getText().toString());
        else
            brand.setId(null);
        if(etBrandName.getText().length() > 0)
            brand.setName(etBrandName.getText().toString());
        else
            brand.setName(null);
        if(etBrandAddress.getText().length() > 0)
            brand.setAddress(etBrandAddress.getText().toString());
        else
            brand.setAddress(null);
        if(etBrandPhone.getText().length() > 0)
            brand.setPhone(etBrandPhone.getText().toString());
        else
            brand.setPhone(null);
        if(etBrandEmail.getText().length() > 0)
            brand.setEmail(etBrandEmail.getText().toString());
        else
            brand.setEmail(null);
        if(!h.isNull(imagePath))
            brand.setLogo(imagePath);
        else
            brand.setLogo(null);
    }

    public void initInterface() {
        Icon_Manager icon_manager = new Icon_Manager();
        tvBrandRefresh.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",this));
        tvBrandRefresh.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",this));
        tvBrandRefresh.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",this));
        tvBrandSave.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",this));
        tvBrandSave.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",this));
        tvBrandSave.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",this));
    }

    public boolean isNull(Brand brand) {
        if(h.isNull(brand.getName())) {
            Toast.makeText(BrandDetailActivity.this, "Name is not allowed null!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(h.isNull(brand.getAddress())) {
            Toast.makeText(BrandDetailActivity.this, "Address is not allowed null!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(h.isNull(brand.getPhone())) {
            Toast.makeText(BrandDetailActivity.this, "Phone is not allowed null!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(h.isNull(brand.getEmail())) {
            Toast.makeText(BrandDetailActivity.this, "Email is not allowed null!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else return false;
    }

    public boolean isValid(Brand brand) {
        if(!h.isAlpha(brand.getName())) {
            Toast.makeText(BrandDetailActivity.this, "Name only contains alphas & spaces!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!h.isNum(brand.getPhone())) {
            Toast.makeText(BrandDetailActivity.this, "Phone only contains numbers!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(brand.getPhone().length() > 15) {
            Toast.makeText(BrandDetailActivity.this, "Phone's max length is 15!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!h.isValidEmail(brand.getEmail())) {
            Toast.makeText(BrandDetailActivity.this, "Email wrong format! Contains alphas, nums, dots and @!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }
}