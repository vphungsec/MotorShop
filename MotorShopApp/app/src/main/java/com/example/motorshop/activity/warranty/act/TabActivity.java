package com.example.motorshop.activity.warranty.act;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.login.LoginActivity;
import com.example.motorshop.activity.warranty.fragment.CaseFragment;
import com.example.motorshop.activity.warranty.fragment.ContentFragment;
import com.example.motorshop.activity.warranty.fragment.GetWarrantyFragment;
import com.example.motorshop.activity.warranty.fragment.InheritFragment;
import com.example.motorshop.activity.warranty.fragment.NoteWhenUsingFragment;
import com.example.motorshop.activity.warranty.fragment.RangeFragment;
import com.example.motorshop.activity.warranty.fragment.RightsOfCustomersFragment;
import com.example.motorshop.activity.warranty.fragment.TimeFragment;
import com.example.motorshop.activity.warranty.fragment.ValidityFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class TabActivity extends AppCompatActivity {
    private TabLayout tabDots;
    private ViewPager viewPager;
    private FloatingActionButton btnMore, btnChat, btnCalander;
    private boolean check = false;
    String usr, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
        getFlag();
        hideFab();

        usr = getIntent().getStringExtra("user");
        pass = getIntent().getStringExtra("pass");
    }

    private void showFab(){
        btnChat.show();
        btnCalander.show();
    }

    private void hideFab(){
        btnChat.hide();
        btnCalander.hide();
    }

    private void getEvent() {
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == false) {
                    Toast.makeText(getApplicationContext(), "Chọn", Toast.LENGTH_SHORT).show();
                    showFab();
                    check = true;
                } else {
                    hideFab();
                    check = false;
                }
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chat với nhân viên", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ChatWithBotActivity.class));
            }
        });

        btnCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thêm xe để quản lý", Toast.LENGTH_LONG).show();

                if(usr == null && pass == null){
                    alerLogError();
                } else {
                    //luc nay chi xet user va pass dang nhap//chua lay tat cả gia tri
                    Intent intent = new Intent(getApplicationContext(), SignupNoticesWarrantyActivity.class);
                    intent.putExtra("user",usr);
                    startActivity(intent);
                }
            }
        });
    }

    private void getControl() {
        tabDots = (TabLayout) findViewById(R.id.tabDots);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnMore = (FloatingActionButton) findViewById(R.id.fab);
        btnChat = (FloatingActionButton) findViewById(R.id.fab_chat);
        btnCalander = (FloatingActionButton) findViewById(R.id.fab_calander);
    }

    private void getFlag() {
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public int getCount() {
                return 9;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ContentFragment();
                    case 1:
                        return new TimeFragment();
                    case 2:
                        return new CaseFragment();
                    case 3:
                        return new RightsOfCustomersFragment();
                    case 4:
                        return new ValidityFragment();
                    case 5:
                        return new InheritFragment();
                    case 6:
                        return new GetWarrantyFragment();
                    case 7:
                        return new RangeFragment();
                    case 8:
                        return new NoteWhenUsingFragment();
                    default:
                        return new ContentFragment();
                }
            }
        };
        viewPager.setAdapter(adapter);
        tabDots.setupWithViewPager(viewPager);
    }
    public void alerLogError(){
        new AlertDialog.Builder(this)
            .setMessage("Qúy khách chưa có tài khoản. Tạo tài khoản để tiếp tục?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            })
            .setNegativeButton("No",null)
            .show();
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