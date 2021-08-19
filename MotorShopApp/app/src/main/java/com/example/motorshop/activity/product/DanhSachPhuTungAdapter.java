package com.example.motorshop.activity.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.Accessory;
import com.example.motorshop.datasrc.Image;

import java.util.ArrayList;

public class DanhSachPhuTungAdapter extends ArrayAdapter<Accessory> {
    Context context;
    int resource;
    public ArrayList<Accessory> data;
    @Nullable
    public ArrayList<Image> images;

    public DanhSachPhuTungAdapter(Context context, int resource, ArrayList data, @Nullable ArrayList images) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.images = images;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Accessory getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_phu_tung, null);


        if (data.size() > 0) {

            TextView tvTenPhuTung = convertView.findViewById(R.id.tvTenPhuTung);
            TextView tvTenHang = convertView.findViewById(R.id.tvTenHang);
            TextView tvMaPhuTung = convertView.findViewById(R.id.tvMaPhuTung);
            TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            TextView tvDonGia = convertView.findViewById(R.id.tvDonGia);
            TextView tvHanBaoHanh = convertView.findViewById(R.id.tvHanBaoHanh);

            ImageView ivPhuTung = convertView.findViewById(R.id.ivPhuTung);

            Accessory accessory = data.get(position);
            //Image image = images.get(position);

            tvMaPhuTung.setText(Integer.toString(accessory.getId()));
            tvTenPhuTung.setText(accessory.getName());

            if (accessory.getBrandId().toString().equals("BR04"))
                tvTenHang.setText("Ohlins");
            if (accessory.getBrandId().toString().equals("BR05"))
                tvTenHang.setText("Akrapovic");
            tvSoLuong.setText(Integer.toString(accessory.getAmount()));
            tvDonGia.setText(Integer.toString(accessory.getPrice()));
            tvHanBaoHanh.setText(accessory.getWarrantyPeriod() + " tháng");

            //byte[] hinhAnh = image.getImage();

            //chuyển byte [] -> bitmap
            //Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            //ivPhuTung.setImageBitmap(bitmap);
        }

        return convertView;
    }
}