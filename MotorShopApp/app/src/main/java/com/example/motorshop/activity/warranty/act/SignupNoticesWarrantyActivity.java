package com.example.motorshop.activity.warranty.act;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.motorshop.activity.R;

public class SignupNoticesWarrantyActivity extends AppCompatActivity {
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_notices_warranty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Thông báo đến hạn bảo dưỡng","Thông báo đến hạn bảo dưỡng",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder hiBuilder = new NotificationCompat.Builder(SignupNoticesWarrantyActivity.this,"Thông báo đến hạn bảo dưỡng");
                    hiBuilder.setContentTitle("Thông báo hạn bảo dưỡng");
                    hiBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                    hiBuilder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(SignupNoticesWarrantyActivity.this);
                managerCompat.notify(1,hiBuilder.build());

            }
        });
    }

    private void setControl() {
        btnAdd = findViewById(R.id.btnAdd);
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