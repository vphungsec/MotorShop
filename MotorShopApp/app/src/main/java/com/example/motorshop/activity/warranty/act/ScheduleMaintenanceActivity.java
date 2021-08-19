package com.example.motorshop.activity.warranty.act;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ScheduleMaintenanceActivity extends AppCompatActivity {
    private TabLayout tabDots;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_maintenance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
        receiveData();
    }

    private void getControl() {
        tabDots = (TabLayout) findViewById(R.id.tabDots);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void getEvent() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        tabDots.setupWithViewPager(viewPager);

    }

    private void receiveData() {
        String getUsr = getIntent().getStringExtra("user");
        String name = getIntent().getStringExtra("name");
        String identityId = getIntent().getStringExtra("identityId");
        String address = getIntent().getStringExtra("address");
        String phone = getIntent().getStringExtra("phone");

        System.out.println(name+","+
                identityId+","+
                address+","+
                phone);
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