package com.example.motorshop.activity.customer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.motorshop.activity.R;
import com.example.motorshop.activity.staff.VolleySTSingle;
import com.example.motorshop.datasrc.Customer;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    Context context;
    List<Customer> dataList;
    private RequestQueue requestQueue;
    RecyclerView rvDataKH;
    public CustomerAdapter(Context context, List<Customer> dataCT) {
        this.context = context;
       dataList = dataCT;
        }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
      /*  if (data != null && data.size() > 0) {
            Customer customer = data.get(position);
            holder.tvIdCT.setText(customer.getId());
            holder.tvIndenCT.setText(customer.getIdentityId());
            holder.tvNameCT.setText(customer.getName());
            holder.tvAddCT.setText(customer.getAddress());
            holder.tvPhoneCT.setText(customer.getPhone());
            holder.tvPassCT.setText(customer.getPassWord());
            holder.tvDateCT.setText(customer.getCreatedDate());
        }*/

        Customer currentCustomer = dataList.get(position);
        String id = currentCustomer.getId();
        String identityId = currentCustomer.getIdentityId();
        String name = currentCustomer.getName();
        String address = currentCustomer.getAddress();
        String phone = currentCustomer.getPhone();
        String passWord = currentCustomer.getPassWord();
        String createdDate = currentCustomer.getCreatedDate();


        holder.tvIdCT.setText("Mã KH: " + id);
        holder.tvIndenCT.setText("CMND: "+ identityId);
        holder.tvNameCT.setText("Họ tên: " + name);
        holder.tvAddCT.setText("Địa chỉ: " + address);
        holder.tvPhoneCT.setText("SĐT: " + phone);
        holder.tvPassCT.setText("Mật khẩu: "+passWord);
        holder.tvDateCT.setText("Ngày tạo: " +createdDate);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdCT,tvIndenCT, tvNameCT,tvAddCT ,tvPhoneCT,tvPassCT,tvDateCT;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvIdCT= itemView.findViewById(R.id.tvIdCT);
            tvIndenCT= itemView.findViewById(R.id.tvIdenIdCT);
            tvNameCT= itemView.findViewById(R.id.tvNameCT);
            tvAddCT= itemView.findViewById(R.id.tvAddCT);
            tvPhoneCT= itemView.findViewById(R.id.tvPhoneCT);
            tvPassCT= itemView.findViewById(R.id.tvPassCT);
            tvDateCT= itemView.findViewById(R.id.tvDateCT);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopUpKH();
                    return true;
                }
            });
        }

        private void showPopUpKH() {
            PopupMenu popupMenu = new PopupMenu(context, itemView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_customer, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemKH:
                            Intent intentThemNV = new Intent(context, Add_CustomerActiviy.class);
                            context.startActivity(intentThemNV);
                            break;
                        case R.id.mnSuaKH:
                            Intent intentSuaNV = new Intent(context,Sua_KHAcitivy.class);
                            context.startActivity(intentSuaNV);
                            break;
                        case R.id.mnXoaKH:
                            int pos = getLayoutPosition();
                            deleteDP(pos);
                            ((CustomerActivity)context).LoadCT();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
    private void deleteDP (int pos){
        System.out.println("data size: " + dataList.size());
        System.out.println("id: " + dataList.get(pos).getId());
        String url = "http://172.168.86.127:8080/api/motorshop/customers?id=" + dataList.get(pos).getId();
        System.out.println(url);
        requestQueue = VolleySTSingle.getmInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE,
                url , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(url);
                try {
                    if(response.getBoolean("OK")){
                        dataList.remove(pos);
                        response.remove(dataList.get(pos).getId());
                        Toast.makeText(context,response.getString("message"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("Thanh cong");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Looi","oneErrorRespone" + error.getMessage());
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }

}