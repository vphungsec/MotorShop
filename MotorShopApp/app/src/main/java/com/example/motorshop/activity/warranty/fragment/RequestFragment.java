package com.example.motorshop.activity.warranty.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.WarrantyAdapter;
import com.example.motorshop.datasrc.Warranty;
import com.example.motorshop.helper.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment {
    private ImageView ImgVSearch;
    private ListView lvWarranty;
    private ArrayList<Warranty> arrayList = new ArrayList<Warranty>();
    public static Helper h = new Helper();
    private EditText EditTextSearchView;
    WarrantyAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        setControl(view);
//        getEvent(view);

        getAllDataWarranty(view);
        return view;
    }

    private void setControl(View v) {
        lvWarranty = v.findViewById(R.id.lvWarranty);
        EditTextSearchView = v.findViewById(R.id.EditTextSearchView);
        ImgVSearch = v.findViewById(R.id.ImgVSearch);

//        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(tk.getNgayDat());
//        Date dTu = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(tgTu);
//        Date dDen = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(tgDen);
    }

    public void getAllDataWarranty(View v) {
        ArrayList<Warranty> warranties = new ArrayList<>();

        String UrlWarranty = "http://192.168.11.5:8080/api/motorshop/warranties";
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
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
                            for(Warranty w : arrayList){
                                try {
                                    Date currentDate = new Date();
                                    String fromatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(currentDate);
                                    Date toDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(w.getCreatedDate());

                                    if(currentDate.getTime() < toDate.getTime()){
                                        warranties.add(w);
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapter = new WarrantyAdapter(v.getContext(), R.layout.item_list_warranty, warranties);
                            lvWarranty.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            EditTextSearchView.addTextChangedListener(new TextWatcher() {
                                public void afterTextChanged(Editable s) {
                                }
                                public void beforeTextChanged(CharSequence s, int start,
                                                              int count, int after) {
                                }
                                public void onTextChanged(CharSequence s, int start,
                                                          int before, int count) {
                                    adapter.getFilter().filter(s.toString());
                                }
                            });
                            lvWarranty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> listView, View view,
                                                        int position, long id) {
                                    // Get the cursor, positioned to the corresponding row in the result set
                                    // Get the state's capital from this row in the database.

                                    Toast.makeText(v.getContext(),"ckecl"+position,Toast.LENGTH_SHORT).show();
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






















//    private void setEvent(View v) {
//
//        ArrayList<Warranty> warranties = new ArrayList<>();
//        String search = EditTextSearchView.getText().toString().trim();
//
//        String UrlWarranty = "http://192.168.11.5:8080/api/motorshop/warranties/billID?billID=" + search;
//        ImgVSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
//                StringRequest oStringRequest = new StringRequest(
//                        Request.Method.GET, UrlWarranty,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Log.e("RestResponse", response.toString());
//                                JSONArray jsonArray = null;
//                                try {
//                                    jsonArray = new JSONArray(response.toString());
//                                    for (int i = 0; i < jsonArray.length(); i++) {
//                                        JSONObject object = jsonArray.getJSONObject(i);
//                                        Warranty w = new Warranty();
//                                        w.setId(Integer.parseInt(object.get("id").toString()));
//                                        w.setBillId(Integer.parseInt(object.get("billId").toString()));
//                                        w.setCreatedDate(object.get("createdDate").toString());
//                                        arrayList.add(w);
//
//                                    }
//                                    adapter = new WarrantyAdapter(v.getContext(), R.layout.item_list_warranty, arrayList);
//                                    lvWarranty.setAdapter(adapter);
//                                    adapter.notifyDataSetChanged();
//
//                                    EditTextSearchView.addTextChangedListener(new TextWatcher() {
//                                        public void afterTextChanged(Editable s) {
//                                        }
//                                        public void beforeTextChanged(CharSequence s, int start,
//                                                                      int count, int after) {
//                                        }
//                                        public void onTextChanged(CharSequence s, int start,
//                                                                  int before, int count) {
//                                            RequestFragment.this.adapter.getFilter().filter(s);
//                                        }
//                                    });
//                                    // Assign adapter to ListView
//                                    lvWarranty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                        @Override
//                                        public void onItemClick(AdapterView<?> listView, View view,
//                                                                int position, long id) {
//                                            // Get the cursor, positioned to the corresponding row in the result set
//                                            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//                                            // Get the state's capital from this row in the database.
//
//                                            Toast.makeText(v.getContext(),"ckecl",Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("ErrorResponse", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(oStringRequest);
//            }
//        });
//    }
}