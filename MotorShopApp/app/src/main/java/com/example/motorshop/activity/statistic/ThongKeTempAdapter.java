package com.example.motorshop.activity.statistic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.motorshop.activity.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThongKeTempAdapter extends ArrayAdapter<ThongKeTemp> {
    ArrayList<ThongKeTemp> thongKeTemps;
    Context context;
    int resource;


    public ThongKeTempAdapter(Context context, int resource, ArrayList<ThongKeTemp> thongKeTemps) {
        super(context, resource, thongKeTemps);
        this.thongKeTemps = thongKeTemps;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return thongKeTemps.size();
    }


    @Override
    public ThongKeTemp getItem(int position) {
        return thongKeTemps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView TextViewTenSP, TextViewSoLuong, TextViewGiaBan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_thong_ke,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.TextViewTenSP = convertView.findViewById(R.id.TextViewTenSP);
            viewHolder.TextViewSoLuong = convertView.findViewById(R.id.TextViewSoLuong);
            viewHolder.TextViewGiaBan = convertView.findViewById(R.id.TextViewGiaBan);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThongKeTemp tk = (ThongKeTemp) getItem(position);

        viewHolder.TextViewTenSP.setText(tk.getTenSP());
        viewHolder.TextViewGiaBan.setText(tk.getGiaBan());
        viewHolder.TextViewSoLuong.setText(tk.getSoLuong());

        return convertView;
    }

    public String formatGiaTien(int tong){
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(tong)+"đ";
    }
}
