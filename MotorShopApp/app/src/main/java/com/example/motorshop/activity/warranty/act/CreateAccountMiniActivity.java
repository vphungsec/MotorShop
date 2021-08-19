package com.example.motorshop.activity.warranty.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Customer;
import com.example.motorshop.helper.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CreateAccountMiniActivity extends AppCompatActivity {
    private EditText EditTextName, EditTextIdentityId, EditTextPhone, EditTextAddress, EditTextPass, EditTextRePass;
    private Button btnCreate;
    public static Helper h = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_mini);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
    }

    private void getControl() {
        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextIdentityId = (EditText) findViewById(R.id.EditTextIdentityId);
        EditTextPhone = (EditText) findViewById(R.id.EditTextPhone);
        EditTextAddress = (EditText) findViewById(R.id.EditTextAddress);
        EditTextPass = (EditText) findViewById(R.id.EditTextPass);
        EditTextRePass = (EditText) findViewById(R.id.EditTextRePass);
        btnCreate = (Button) findViewById(R.id.btnCreate);
    }
    private void getEvent() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = h.formatName(EditTextName.getText().toString());
                String identityId = EditTextIdentityId.getText().toString();
                String phone = EditTextPhone.getText().toString();
                String address = EditTextAddress.getText().toString();
                String pass = EditTextPass.getText().toString();
                String rePass = EditTextPass.getText().toString();
                String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());

                if(isValidInfo(name,identityId,phone,address,pass,rePass,currentDate)){

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    Toast.makeText(getApplicationContext(),"Tạo thành công",Toast.LENGTH_SHORT).show();
                    //đưa dữ liệu lên api
                    createAccount(name,identityId,phone,address,pass,currentDate);
                } else {
                    alerLogError();
                }
            }
        });
    }

    public void createAccount(String name, String identityId, String phone, String address, String pass,String currentDate){
        ArrayList<Customer> customers = new ArrayList<>();

        String UrlMotorDetails = "http://172.168.86.127:8080/api/motorshop/customers";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest objectRequest = new StringRequest(
                Request.Method.GET, UrlMotorDetails,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse",response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response.toString());
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Customer c = new Customer();
                                c.setId(object.get("id").toString());
                                c.setId(object.get("identityId").toString());
                                c.setId(object.get("name").toString());
                                c.setId(object.get("address").toString());
                                c.setId(object.get("phone").toString());
                                c.setId(object.get("passWord").toString());
                                c.setId(object.get("createdDate").toString());
                                customers.add(c);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse",error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }

    public boolean isValidInfo(String name, String identityId, String phone, String address, String pass, String rePass,String currentDate) {
        if (isNull(name, identityId, phone, address,pass,rePass)) {
            Toast.makeText(getApplicationContext(), "All must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!h.isNum(identityId)) {
            Toast.makeText(getApplicationContext(), "IdentityId only contains numbers!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!h.isNum(phone)) {
            Toast.makeText(getApplicationContext(), "Phone only contains numbers!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (identityId.length() != 9 && identityId.length() != 12) {
            Toast.makeText(getApplicationContext(), "IdentityId length is must 9 or 12!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() < 10 || phone.length() > 15) {
            Toast.makeText(getApplicationContext(), "Phone's length is in range of 10 & 15!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!rePass.equals(pass)) {
            Toast.makeText(getApplicationContext(), "RePassword wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isNull(String name, String identityId, String phone, String address, String pass, String rePass) {
        return h.isNull(name) || h.isNull(identityId) || h.isNull(phone) || h.isNull(address)|| h.isNull(pass) || h.isNull(rePass);
    }

    public void alerLogError(){
        new AlertDialog.Builder(this)
                .setMessage("Quý khách vui lòng kiểm tra và nhập đầy đủ thông tin yêu cầu!")
                .setPositiveButton("Ok", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                startActivity(new Intent(getApplicationContext(), TabActivity.class));
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}