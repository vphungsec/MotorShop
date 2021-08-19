package com.example.motorshop.activity.warranty.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.MessagesAdapter;
import com.example.motorshop.activity.warranty.data.Message;
import com.example.motorshop.activity.warranty.utils.BotResponses;
import com.example.motorshop.activity.warranty.utils.Constants;
import com.example.motorshop.activity.warranty.utils.Time;

import java.util.ArrayList;
import java.util.Random;

public class ChatWithBotActivity extends AppCompatActivity {
    private MessagesAdapter messagesAdapter;
    private String[] botList = new String[]{"Hanh","Hung","Dang"};
    ArrayList<Message> arrayList;
    private EditText et_message;
    private Button btn_send;
    private RecyclerView rv_messages;
    private LinearLayout ll_layout_bar;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_bot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycleView();
        setControl();
        setEvent();
        Random generator = new Random();
        int value = generator.nextInt((2 - 0) + 1) + 0;

        customMessage("Xin chào, mình là "+botList[value]+" có thể giúp gì được cho bạn?");
    }

    private void setControl() {
        et_message = (EditText) findViewById(R.id.et_message);
        btn_send = (Button) findViewById(R.id.btn_send);
        rv_messages = (RecyclerView) findViewById(R.id.rv_messages);
        ll_layout_bar = (LinearLayout) findViewById(R.id.ll_layout_bar);
    }

    private void setEvent() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        et_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            rv_messages.getLayoutManager().scrollToPosition(messagesAdapter.getItemCount()-1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void recycleView() {
        String timestamp = new Time().timeStamp();
        arrayList = new ArrayList<Message>();
//        arrayList.add(new Message(botList[2].toString(), botList[2],timestamp));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messagesAdapter = new MessagesAdapter(this,arrayList);
        rv_messages.setAdapter(messagesAdapter);
        rv_messages.setLayoutManager(linearLayoutManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendMessage(){
        String mess = et_message.getText().toString();
        String timestamp = new Time().timeStamp();
        if(!mess.isEmpty()){
            et_message.setText("");
            messagesAdapter.insertMessage(new Message(mess, Constants.SEND_ID, timestamp));
            rv_messages.getLayoutManager().scrollToPosition(messagesAdapter.getItemCount()-1);

            botResponses(mess);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread thread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    rv_messages.getLayoutManager().scrollToPosition(messagesAdapter.getItemCount()-1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void botResponses(String mess){
        String botResponses = new BotResponses().basicResponses(mess);

        String timestamp = new Time().timeStamp();
        Thread thread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                    messagesAdapter.insertMessage(new Message(botResponses, Constants.RECEIVE_ID, timestamp));
                    rv_messages.getLayoutManager().scrollToPosition(messagesAdapter.getItemCount()-1);

                    switch (botResponses){
                        case Constants.OPEN_GOOGLE:
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
                        case Constants.OPEN_SEARCH:
                            String searchTerm = mess.substring(mess.lastIndexOf("search"));// + 1
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?$q=$searchTerm")));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private void customMessage(String mess) {
        Thread thread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    String timestamp = new Time().timeStamp();
                    messagesAdapter.insertMessage(new Message(mess, Constants.RECEIVE_ID, timestamp));
                    rv_messages.getLayoutManager().scrollToPosition(messagesAdapter.getItemCount()-1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}