package com.example.motorshop.activity.warranty.act;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.ListProductWarrantyAdapter;
import com.example.motorshop.datasrc.WarrantyDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WarrantyDetailActivity extends AppCompatActivity {
    EditText EditTextNDBH,EditTextPBH;
    TextView TextViewMaNV,TextViewMaDH,TextViewNgayTao,TextViewTongPBH,TextViewMaBH;
    Spinner spnTenSP;
    ImageButton ImageButtonEdit;
    ListView lvDetail;
    ArrayList<WarrantyDetail> arrayList = new ArrayList<>();
    ListProductWarrantyAdapter listProductWarrantyAdapter;
    String billId, warrantyId, staffId;
    List<String> getNamePro = new ArrayList<>();
    List<String> getIdPro = new ArrayList<>();
    String idPro;int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setControl();
        receive();

    }

    public void receive(){
        billId = getIntent().getStringExtra("billId");
        warrantyId = getIntent().getStringExtra("id");
        staffId = getIntent().getStringExtra("staffId");
//        System.out.println(billId);
//        System.out.println(warrantyId);
//        System.out.println(staffId);

        TextViewMaBH.setText(warrantyId);
        TextViewMaNV.setText(staffId);
        TextViewMaDH.setText(billId);

        getProWarrantyByBillId(Integer.parseInt(billId));
        getDataWarrantyDetailByWarrantyID(Integer.parseInt(warrantyId));
        getPriceByWaId(Integer.parseInt(warrantyId));
        System.out.println(value);

        addContent(Integer.parseInt(warrantyId),0,value);
    }

    private void setControl() {
        lvDetail = findViewById(R.id.lvDetail);
        TextViewMaDH = findViewById(R.id.TextViewMaDH);
        EditTextNDBH = findViewById(R.id.EditTextNDBH);
        EditTextPBH = findViewById(R.id.EditTextPBH);
        TextViewMaNV = findViewById(R.id.TextViewMaNV);
        TextViewMaBH = findViewById(R.id.TextViewMaBH);
        TextViewNgayTao = findViewById(R.id.TextViewNgayTao);
        spnTenSP = findViewById(R.id.spnTenSP);
        TextViewTongPBH = findViewById(R.id.TextViewTongPBH);
        ImageButtonEdit = findViewById(R.id.ImageButtonEdit);
    }

    public void getProWarrantyByBillId(Integer bill_id) {

        String UrlWarranty = "http://172.168.86.127:8080/api/motorshop/warranties/getNameProduct/bill_id?bill_id="+bill_id;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, UrlWarranty,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        String ofString = response.toString();
                        String ofString2 = ofString.replace("[","");
                        String ofString3 = ofString2.replace("]","");
                        String ofString4 = ofString3.replace("\"","");
                        String[] tachStr = ofString4.split(",");
                        for(int i=0;i<tachStr.length;i=i+2){
                            getNamePro.add(tachStr[i+1].toString().trim());
                            getIdPro.add(tachStr[i].toString().trim());
                        }
                        // Creating adapter for spinner
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(WarrantyDetailActivity.this, android.R.layout.simple_spinner_item, getNamePro);

                        // Drop down layout style - list view with radio button
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spnTenSP.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();

                        spnTenSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //get values
                                Object item = parent.getItemAtPosition(position);
                                System.out.println(getIdPro.get(position));

                                idPro = getIdPro.get(position);
                                value = Integer.parseInt(idPro);
                            }

                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse", error.toString());
                    }
                }
        );
        requestQueue.add(oStringRequest);
    }

    private void create(int warrantyId, int motorId, int accessoryId) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonBody = new JSONObject();
        try {
            //input your API parameters
            jsonBody.put("warrantyId", warrantyId);
            jsonBody.put("motorId", 0);
            jsonBody.put("accessoryId", 1);
            jsonBody.put("content", EditTextNDBH.getText().toString().trim());
            jsonBody.put("price", EditTextPBH.getText().toString().trim());



        } catch (JSONException e) {
            e.printStackTrace();

        }
        String URL = "http://172.168.86.127:8080/api/motorshop/warrantyDetails";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody,
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
        });

        requestQueue.add(jsonObjectRequest);










//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            String URL = "http://172.168.86.127:8080/api/motorshop/warrantyDetails";
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("warrantyId", warrantyId);
//            jsonBody.put("motorId", 0);
//            jsonBody.put("accessoryId", 1);
//            jsonBody.put("content", EditTextNDBH.getText().toString().trim());
//            jsonBody.put("price", EditTextPBH.getText().toString().trim());
//            final String requestBody = jsonBody.toString();
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.i("VOLLEY", response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("VOLLEY", error.toString());
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return requestBody == null ? null : requestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                        return null;
//                    }
//                }
//
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//                        responseString = String.valueOf(response.statusCode);
//                        // can get more details such as response.headers
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
//            };
//
//            requestQueue.add(stringRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private void addContent(int warrantyId, int motorId, int accessoryId) {
        ImageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(WarrantyDetailActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thêm Nội Dung")
                        .setMessage("Bạn có muốn thêm nội dung và phí BH?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                create( warrantyId, motorId, accessoryId);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    public void getDataWarrantyDetailByWarrantyID(Integer warrantyId){

        String Url = "http://172.168.86.127:8080/api/motorshop/warrantyDetails/getWarrantyDetailByWaId/warrantyId?warrantyId="+warrantyId;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String motorÍD="0",accessoryId="0";

                                if(object.get("motorId").toString()==null){
                                    motorÍD = object.get("motorId").toString().trim();
                                }else if(object.get("accessoryId").toString()==null){
                                    accessoryId = object.get("accessoryId").toString().trim();
                                }
                                WarrantyDetail wd = new WarrantyDetail();
                                wd.setId(Integer.parseInt(object.get("id").toString()));
                                wd.setWarrantyId(Integer.parseInt(object.get("warrantyId").toString()));
                                wd.setMotorId(Integer.parseInt(motorÍD));
                                wd.setAccessoryId(Integer.parseInt(accessoryId));
                                wd.setContent(object.get("content").toString());
                                wd.setPrice(Integer.parseInt(object.get("price").toString()));
                                arrayList.add(wd);
                            }

                            listProductWarrantyAdapter = new ListProductWarrantyAdapter(getApplicationContext(),
                                    R.layout.item_list_warranty_details, arrayList);
                            lvDetail.setAdapter(listProductWarrantyAdapter);
                            listProductWarrantyAdapter.notifyDataSetChanged();

                            lvDetail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                    delNoiDung(position,arrayList);
                                    return false;
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse", error.toString());
                    }
                }
        );
        requestQueue.add(oStringRequest);
    }

    public void delNoiDung(int pos, ArrayList<WarrantyDetail> arr){
        new AlertDialog.Builder(this)
                .setTitle("Xóa Nội Dung")
                .setMessage("Bạn có muốn xóa nội dung này không?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"xoa",Toast.LENGTH_SHORT).show();
                        callDell(pos);
                        arr.remove(pos);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    public void callDell(int pos){

        String Url = "http://172.168.86.127:8080/api/motorshop/warrantyDetails?id="+pos;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest oStringRequest = new StringRequest(
                Request.Method.DELETE, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RestResponse", response.toString());
                        Toast.makeText(getApplicationContext(),"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse", error.toString());
                        Toast.makeText(getApplicationContext(),"Xoa lỗi",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(oStringRequest);
    }

    public void getPriceByWaId(Integer warrantyId){

        String Url = "http://172.168.86.127:8080/api/motorshop/warrantyDetails/getPriceByWaId/warrantyId?warrantyId="+warrantyId;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        String ofString = response.toString();
                        String ofString2 = ofString.replace("[","");
                        String ofString3 = ofString2.replace("]","");

                        TextViewTongPBH.setText(ofString3);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse", error.toString());
                    }
                }
        );
        requestQueue.add(oStringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}