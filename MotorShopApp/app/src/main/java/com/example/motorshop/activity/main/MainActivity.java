package com.example.motorshop.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.bill.QuanLy_DonDat;
import com.example.motorshop.activity.product.QuanLyXeActivity;
import com.example.motorshop.activity.staff.StaffActivity;
import com.example.motorshop.activity.statistic.PieChartActivity;
import com.example.motorshop.activity.warranty.act.TabActivity;
import com.example.motorshop.activity.warranty.act.WarrantyActivity;
import com.example.motorshop.datasrc.Main;
import com.example.motorshop.activity.brand.BrandActivity;
import com.example.motorshop.helper.Helper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imvMain;
    TextView tvMain;
    GridView gvMain;

    private Bundle extras;
    private ArrayList<Main> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void sendExtras(Class className) {
        if(extras != null) {
            Intent i = new Intent(getApplicationContext(), className);
            i.putExtra("userType", extras.getString("userType"));
            i.putExtra("id", extras.getString("id"));
            startActivity(i);
        }
    }

    private void setControl() {
        imvMain = (ImageView) findViewById(R.id.imvMain);
        tvMain = (TextView) findViewById(R.id.tvMain);
        gvMain = (GridView) findViewById(R.id.gvMain);
    }

    private void setEvent() {
        MainAdapter adapter = new MainAdapter(this, R.layout.item_main, itemList);
        gvMain.setAdapter(adapter);
        initItemList();
        adapter.notifyDataSetChanged();

        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    sendExtras(BrandActivity.class);
                }
                if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), QuanLyXeActivity.class);
                    startActivity(intent);
//                    Log.d(TAG, "onItemClick gridview: " + itemList.get(position).getName());
                }
                if(position == 2){
                    Intent intent = new Intent(getApplicationContext(), QuanLy_DonDat.class);
                    startActivity(intent);
                }
                if(position == 3){
                    String getID = getIntent().getStringExtra("id");
                    if(getID.contains("ST")){
                        receiveInfoLoginAndSent(new Intent(getApplicationContext(), WarrantyActivity.class));
                    }else {
                        receiveInfoLoginAndSent(new Intent(getApplicationContext(), TabActivity.class));
                    }
                }
                if(position == 4){
//                    String getID = getIntent().getStringExtra("id");
//                    if(new Helper().isNull(getID)){
//                        Toast.makeText(getApplicationContext(),"Bạn chưa đăng nhập!",Toast.LENGTH_SHORT).show();
//                    }else{
//                        if(!getID.contains("ST")) {
                            Toast.makeText(getApplicationContext(),"Chỉ dành cho nhân viên",Toast.LENGTH_SHORT).show();
//                        }else
                            receiveInfoLoginAndSent(new Intent(getApplicationContext(), PieChartActivity.class));
//                    }
                }
                if(position == 5){
                    Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /*More*/
    public void receiveInfoLoginAndSent(Intent intent){
        String getUsr = getIntent().getStringExtra("user");
        String getPss = getIntent().getStringExtra("pass");
        String getID = getIntent().getStringExtra("id");
        System.out.println(getID);
        intent.putExtra("user",getUsr);
        intent.putExtra("pass",getPss);
        intent.putExtra("id",getID);
        startActivity(intent);
    }

    private void initItemList(){
        itemList.add(new Main(R.drawable.brand, getResources().getString(R.string.brands)));
        itemList.add(new Main(R.drawable.product, getResources().getString(R.string.products)));
        itemList.add(new Main(R.drawable.bill, getResources().getString(R.string.bills)));
        itemList.add(new Main(R.drawable.guarantee, getResources().getString(R.string.guarantees)));
        itemList.add(new Main(R.drawable.statistic, getResources().getString(R.string.statistics)));
        itemList.add(new Main(R.drawable.people, getResources().getString(R.string.people)));
    }

}