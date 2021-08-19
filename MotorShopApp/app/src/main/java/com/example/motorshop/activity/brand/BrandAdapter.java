package com.example.motorshop.activity.brand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Brand;
import com.example.motorshop.helper.Icon_Manager;

import java.util.ArrayList;

public class BrandAdapter extends ArrayAdapter<Brand> {

    private Context context;
    private Integer resource;
    private ArrayList<Brand> data;

    public BrandAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        ImageView imvBR = convertView.findViewById(R.id.imvBR);
        TextView tvBRName = convertView.findViewById(R.id.tvBRName);
        TextView tvEditBR = convertView.findViewById(R.id.tvEditBR);
        TextView tvDeleteBR = convertView.findViewById(R.id.tvDeleteBR);

        Brand brand = this.data.get(position);
//        imvBR.setImageResource(brand.getLogo());
        tvBRName.setText(brand.getName());

        Icon_Manager icon_manager = new Icon_Manager();
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvEditBR.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-brands-400.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-regular-400.ttf",getContext()));
        tvDeleteBR.setTypeface(icon_manager.get_icons("fonts/fa-solid-900.ttf",getContext()));

        tvEditBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrandActivity)context).editBrand(brand);
            }
        });

        tvDeleteBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrandActivity)context).deleteBrand(brand);
            }
        });

        return convertView;
    }

}