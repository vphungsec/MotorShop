package com.example.motorshop.activity.warranty.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.data.Message;
import com.example.motorshop.activity.warranty.utils.Constants;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ItemHolder> {

    Context context;
    ArrayList<Message> arrMess;

    public MessagesAdapter(Context context, ArrayList<Message> arrMess) {
        this.context = context;
        this.arrMess = arrMess;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item,parent,false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Message mess = arrMess.get(position);
        switch (mess.getId()){
            case Constants.SEND_ID: {
                holder.tv_message.setText(mess.getMessage());
                holder.tv_bot_message.setText(View.GONE);
            }
            case Constants.RECEIVE_ID: {
                holder.tv_bot_message.setText(mess.getMessage());
                holder.tv_message.setText(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrMess.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tv_bot_message, tv_message;

        public ItemHolder(View itemView){
            super(itemView);

            tv_bot_message = itemView.findViewById(R.id.tv_bot_message);
            tv_message = itemView.findViewById(R.id.tv_message);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrMess.remove(v);
                    notifyItemRemoved(v.getVerticalScrollbarPosition());
                }
            });
        }
    }

    private void notifyItemRemoved(View v) {
    }

    public void insertMessage(Message message){
        notifyItemInserted(arrMess.size());
        notifyDataSetChanged();
    }
}
