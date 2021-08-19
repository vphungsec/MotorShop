package com.example.motorshop.activity.staff;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;


public class Sua_NVActivity extends AppCompatActivity {
    /*NhanVien nhanVien;
    DBManager db = new DBManager(this);
    EditText etTenNVSua,etMaNVSua,etSDTSua,etPBNVSua;
    Button btnXacNhanSuaNV;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_staff);
       /* setControl();
        setEvent();*/
    }
/*
    private void setEvent() {
        btnXacNhanSuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNVSua = etMaNVSua.getText().toString();
                String tenNVSua = etTenNVSua.getText().toString();
                String SDTNVSua = etSDTSua.getText().toString();
                String PBNVSua = etPBNVSua.getText().toString();
                if(TextUtils.isEmpty(maNVSua)){
                    Toast.makeText(Sua_NVActivity.this,"Mã nhân viên không được rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                updateDepartment();
                Intent intent = new Intent(Sua_NVActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etMaNVSua = findViewById(R.id.etMaNVSua);
        etTenNVSua = findViewById(R.id.etTenNVSua);
        etSDTSua= findViewById(R.id.etSDTCS);
        etPBNVSua = findViewById(R.id.etPBNVCanSua);
        btnXacNhanSuaNV = findViewById(R.id.btnXacNhanSuaNV);
    }

    public void updateDepartment(){
        String MaNV = etMaNVSua.getText().toString().trim();
        String TenNV = etTenNVSua.getText().toString().trim();
        String SDT = etTenNVSua.getText().toString().trim();
        String MaBP = etTenNVSua.getText().toString().trim();
        if(!MaNV.equals("") && !TenNV.equals("") && !SDT.equals("")&& !MaBP.equals("")){
            nhanVien = new NhanVien();
            nhanVien.setMaNV(MaNV);
            nhanVien.setHoTen(TenNV);
            nhanVien.setSdt(SDT);
            nhanVien.setMaBP(MaBP);
            new DBManager(Sua_NVActivity.this).updateST(nhanVien);
        }
    }*/
}