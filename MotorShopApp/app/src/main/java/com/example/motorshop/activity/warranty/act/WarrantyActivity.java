package com.example.motorshop.activity.warranty.act;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.WarrantyAdapter;
import com.example.motorshop.datasrc.Warranty;
import com.example.motorshop.helper.Helper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WarrantyActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView lvWarranty;
    private ArrayList<Warranty> arrayList = new ArrayList<Warranty>();
    public static Helper h = new Helper();
    private EditText EditTextSearchView;
    WarrantyAdapter adapter;
    private FloatingActionButton add;
    String usr,idSt;
    String billID, createDate, staffID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
        usr = getIntent().getStringExtra("user");
        idSt = getIntent().getStringExtra("id");
        getAllDataWarranty();
    }

    private void getControl() {
        lvWarranty = findViewById(R.id.lvWarranty);
        EditTextSearchView = findViewById(R.id.EditTextSearchView);
        searchView = (SearchView)findViewById(R.id.search);
        add = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void getEvent() {
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchWarranty(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    arrayList.clear();
                    searchWarranty(newText);

                } else {
                    arrayList.clear();
                    getAllDataWarranty();
                }
                return false;
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(WarrantyActivity.this);
                dialog.setTitle("Tạo đơn bảo hành");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_add_warranty);
                EditText EditTextBillID = dialog.findViewById(R.id.EditTextBillID);
                TextView EditTextIDStaff = dialog.findViewById(R.id.EditTextIDStaff);

                EditTextIDStaff.setText(idSt);

                ImageView btnAdd = dialog.findViewById(R.id.btnAdd);
                ImageView btnCancel = dialog.findViewById(R.id.btnCancel);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        billID = EditTextBillID.getText().toString().trim();
                        createDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                        staffID = EditTextIDStaff.getText().toString().trim();

                        if(isValid(billID)){
                            checkBill(Integer.parseInt(billID),createDate,staffID,dialog);
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    private void searchWarranty(String input) {
        ArrayList<Warranty> warranties = searchData(input);
        if (warranties != null) {
            lvWarranty.setAdapter(new WarrantyAdapter(getApplicationContext(), R.layout.item_list_warranty,arrayList));
        }
    }

    private ArrayList<Warranty> searchData(String input){

        String url = "http://172.168.86.127:8080/api/motorshop/warranties/getWarrantyByIdentityid/identityid?identityid=" + input;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("Response is: " + response.toString());
                arrayList = null;
                arrayList = new ArrayList<Warranty>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Warranty w = new Warranty();
                        w.setId(Integer.parseInt(object.get("id").toString()));
                        w.setBillId(Integer.parseInt(object.get("billId").toString()));
                        w.setCreatedDate(object.get("createdDate").toString());
                        arrayList.add(w);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new WarrantyAdapter(getApplicationContext(), R.layout.item_list_warranty, arrayList);
                lvWarranty.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
        return (ArrayList<Warranty>) arrayList;
    }

    public boolean isValid(String billID) {
        if (isNull(billID)) {
            Toast.makeText(getApplicationContext(), "billID must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isNull(String billID) {
        return h.isNull(billID);
    }

    public void getAllDataWarranty() {
        String UrlWarranty = "http://172.168.86.127:8080/api/motorshop/warranties";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, UrlWarranty,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                Warranty w = new Warranty();
                                w.setId(Integer.parseInt(object.get("id").toString()));
                                w.setBillId(Integer.parseInt(object.get("billId").toString()));
                                w.setCreatedDate(object.get("createdDate").toString());
                                arrayList.add(w);
                            }

                            adapter = new WarrantyAdapter(getApplicationContext(), R.layout.item_list_warranty, arrayList);
                            lvWarranty.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            lvWarranty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> listView, View view,
                                                        int position, long id) {
                                    // Get the cursor, positioned to the corresponding row in the result set
                                    // Get the state's capital from this row in the database.

                                    Intent intent = new Intent(WarrantyActivity.this, WarrantyDetailActivity.class);

                                    intent.putExtra("billId",String.valueOf(arrayList.get(position).getBillId()));
                                    intent.putExtra("id",String.valueOf(arrayList.get(position).getId()));
                                    intent.putExtra("staffId",idSt);
                                    startActivity(intent);

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

    public void sendInfoLogin(Intent intent){

    }

    private void createDonBH(String date, int billID, String idStaff) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://172.168.86.127:8080/api/motorshop/warranties";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("billId", billID);
            jsonBody.put("createdDate", date);
            jsonBody.put("staffId", idStaff);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkStaffWarranty(String date, int billID, String idStaff,Dialog dialog){
        String UrlWarranty = "http://172.168.86.127:8080/api/motorshop/warranties/checkStaff/staff_id?staff_id="+idStaff;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, UrlWarranty,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        String ofString = response.toString();
                        String ofString2 = ofString.replace("[","");
                        String ofString3 = ofString2.replace("]","");
                        String[] tachStr = ofString3.split(",");
                        String idStaff = tachStr[0];
                        String departId = tachStr[1];

                        if (idStaff.contains(idStaff) && departId.contains("BH")) {
                            createDonBH(date, billID, idStaff);
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Không phải nhân viên Bảo hành",Toast.LENGTH_SHORT).show();
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

    public void checkBill(Integer id, String createDate, String staffID, Dialog dialog){
        String url = "http://172.168.86.127:8080/api/motorshop/warranties/checkBillID/id?id="+id;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String ofString = response.toString().trim();
                        String ofString2 = ofString.replace("[","");
                        String ofString3 = ofString2.replace("]","");
                        System.out.println(ofString3);
                        if(!ofString3.contains(String.valueOf(id))){
                            Toast.makeText(getApplicationContext(),"ID not exist",Toast.LENGTH_SHORT).show();
                        } else {
                            checkStaffWarranty(createDate,Integer.parseInt(billID),staffID,dialog);
                            Toast.makeText(getApplicationContext(),"ID exist",Toast.LENGTH_SHORT).show();
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