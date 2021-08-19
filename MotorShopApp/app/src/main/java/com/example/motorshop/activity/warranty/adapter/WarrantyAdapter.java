package com.example.motorshop.activity.warranty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Warranty;

import java.util.ArrayList;

public class WarrantyAdapter extends ArrayAdapter<Warranty> {
    ArrayList<Warranty> warranties;
    Context context;
    int resource;

    public WarrantyAdapter( Context context, int resource, ArrayList<Warranty> warranties) {
        super(context, resource, warranties);
        this.warranties = warranties;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return warranties.size();
    }

    @Override
    public Warranty getItem(int position) {
        return warranties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView TextViewMaBH, TextViewNgayTao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_warranty,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.TextViewMaBH = convertView.findViewById(R.id.TextViewMaBH);
            viewHolder.TextViewNgayTao = convertView.findViewById(R.id.TextViewNgayTao);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Warranty w = (Warranty) getItem(position);
        viewHolder.TextViewMaBH.setText(String.valueOf(w.getBillId()));
        viewHolder.TextViewNgayTao.setText(w.getCreatedDate());
        return convertView;
    }
}
