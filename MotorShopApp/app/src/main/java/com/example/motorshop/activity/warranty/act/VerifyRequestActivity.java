package com.example.motorshop.activity.warranty.act;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.helper.Helper;

public class VerifyRequestActivity extends AppCompatActivity {
    private ImageView ImgVProduct;
    private TextView frameNums;
    private EditText EditTextName, EditTextIdentityId, EditTextPhone, EditTextAddress;
    private Button btnAddInfo;
    public static Helper h = new Helper();
    String getUsr,numberFr,dateMode;
    String name,identityId,address,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numberFr = getFrameNumsIntent();
        getUsr = getIntent().getStringExtra("user");

        getControl();
        getEvent();

        frameNums.setText(numberFr);
        getValues(getUsr);
    }

    public String getFrameNumsIntent() {
        return getIntent().getStringExtra("content");
    }

    private void getControl() {
        ImgVProduct = (ImageView) findViewById(R.id.ImgVProduct);
        frameNums = (TextView) findViewById(R.id.frameNums);
        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextIdentityId = (EditText) findViewById(R.id.EditTextIdentityId);
        EditTextPhone = (EditText) findViewById(R.id.EditTextPhone);
        EditTextAddress = (EditText) findViewById(R.id.EditTextAddress);
        btnAddInfo = (Button) findViewById(R.id.btnAddInfo);
    }

    private void getEvent() {
        btnAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidAddFrameNumber(dateMode)){

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    alerLogError();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Xác minh không hợp lệ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getValues(String userPhone) {
        name = getIntent().getStringExtra("name");
        identityId = getIntent().getStringExtra("identityId");
        address = getIntent().getStringExtra("address");
        phone = getIntent().getStringExtra("phone");

        EditTextName.setText(name);
        EditTextIdentityId.setText(identityId);
        EditTextAddress.setText(address);
        EditTextPhone.setText(phone);
    }

    public boolean isValidAddFrameNumber(String dateMode) {
        if (isNull(dateMode)) {
            Toast.makeText(getApplicationContext(), "Date must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dateMode.length()<9 && dateMode.length()>10) {
            Toast.makeText(getApplicationContext(), "Date Error!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isNull(String dateMode) {
        return h.isNull(dateMode);
    }

    public void alerLogError(){
        new AlertDialog.Builder(this)
                .setMessage("Bạn có muốn thêm xe để dễ quản lý?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sentData(new Intent(getApplicationContext(), ScheduleMaintenanceActivity.class));
                        Toast.makeText(getApplicationContext(),"send",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    private void sentData(Intent intent) {

        intent.putExtra("user",getUsr);
        intent.putExtra("name",name);
        intent.putExtra("identityId",identityId);
        intent.putExtra("address",address);
        intent.putExtra("phone",phone);

        startActivity(intent);
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