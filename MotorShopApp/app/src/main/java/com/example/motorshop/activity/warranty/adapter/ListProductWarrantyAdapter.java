package com.example.motorshop.activity.warranty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.WarrantyDetail;

import java.util.ArrayList;

public class ListProductWarrantyAdapter extends ArrayAdapter<WarrantyDetail> {
    ArrayList<WarrantyDetail> warrantiesDetails;
    Context context;
    int resource;

    public ListProductWarrantyAdapter( Context context, int resource, ArrayList<WarrantyDetail> warrantiesDetails) {
        super(context, resource, warrantiesDetails);
        this.warrantiesDetails = warrantiesDetails;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return warrantiesDetails.size();
    }

    @Override
    public WarrantyDetail getItem(int position) {
        return warrantiesDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView EditTextTenXe,EditTextTenPT,EditTextNDBH, EditTextPBH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_warranty_details,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.EditTextTenXe = convertView.findViewById(R.id.EditTextTenXe);
            viewHolder.EditTextTenPT = convertView.findViewById(R.id.EditTextTenPT);
            viewHolder.EditTextNDBH = convertView.findViewById(R.id.EditTextNDBH);
            viewHolder.EditTextPBH = convertView.findViewById(R.id.EditTextPBH);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WarrantyDetail warrantyDetail = (WarrantyDetail) getItem(position);
        viewHolder.EditTextTenXe.setText(String.valueOf(warrantyDetail.getMotorId()));
        viewHolder.EditTextTenPT.setText(String.valueOf(warrantyDetail.getAccessoryId()));
        viewHolder.EditTextNDBH.setText(warrantyDetail.getContent());
        viewHolder.EditTextPBH.setText(String.valueOf(warrantyDetail.getPrice()));
        return convertView;
    }
}
