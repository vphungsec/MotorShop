package com.example.motorshop.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Customer;


public class Add_CustomerActiviy extends AppCompatActivity {

    Customer customer;
    //DBManager db = new DBManager(this);
    EditText etTenKHThem,etCMNDThem,etSDTKH,etDiaChiThem;
    Button btnXacNhanThemKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXacNhanThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CMNDThem = etCMNDThem.getText().toString();
                String tenKHThem = etTenKHThem.getText().toString();
                String SDTKHThem = etSDTKH.getText().toString();
                String diaChiKHThem = etDiaChiThem.getText().toString();
                if(TextUtils.isEmpty(CMNDThem)){
                    Toast.makeText(Add_CustomerActiviy.this,"CMND khách hàng không được rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                InserKH(customer);
                Intent intent = new Intent(Add_CustomerActiviy.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etCMNDThem = findViewById(R.id.etCMNDThem);
        etTenKHThem = findViewById(R.id.etTenKHThem);
        etSDTKH= findViewById(R.id.etSDTKHCT);
        etDiaChiThem= findViewById(R.id.etDCKHCanThem);
        btnXacNhanThemKH = findViewById(R.id.btnXacNhanThemKH);
    }

    public void InserKH(Customer customer){
        customer = new Customer();
        customer.setIdentityId(etCMNDThem.getText().toString().trim());
        customer.setName(etTenKHThem.getText().toString().trim());
        customer.setPhone(etSDTKH.getText().toString().trim());
        customer.setAddress(etDiaChiThem.getText().toString().trim());
        //new DBManager(Add_CustomerActiviy.this).insertCTM(khachHang);
    }
}