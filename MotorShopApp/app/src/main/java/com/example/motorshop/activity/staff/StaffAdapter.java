package com.example.motorshop.activity.staff;

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
import com.example.motorshop.datasrc.Staff;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    Context context;
    int resource;
    List<Staff> dataList;
    RecyclerView rvDataNV;
    private RequestQueue requestQueue;

    public StaffAdapter(Context context,int resource,List<Staff> dataST) {
        this.context = context;
        this.resource = resource;
        dataList= dataST;


    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_staff, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
       /* if (data != null && data.size() > 0) {
            Staff staff = data.get(position);
            holder.tvIdST.setText(staff.getId());
            holder.tvNameST.setText(staff.getName());
            holder.tvPhoneST.setText(staff.getPhone());
            holder.tvPassST.setText(staff.getPassword());
            holder.tvDateST.setText(staff.getCreatedDate());
            holder.tvDepartST.setText(staff.getDepartId());

        }*/
        Staff currentStaff = dataList.get(position);
        String id = currentStaff.getId();
        String name = currentStaff.getName();
        String phone = currentStaff.getPhone();
        String passWord = currentStaff.getPassword();
        String createdDate = currentStaff.getCreatedDate();
        String dePartId = currentStaff.getDepartId();

        holder.tvIdST.setText("Mã NV: " + id);
        holder.tvNameST.setText("Họ Tên NV: "+name);
        holder.tvPhoneST.setText("SĐT: " +phone);
        holder.tvPassST.setText("Mật khẩu: "+passWord);
        holder.tvDateST.setText("Ngày tạo: " +createdDate);
        holder.tvDepartST.setText("Mã BP: " +dePartId);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdST, tvNameST, tvPhoneST,tvPassST,tvDateST,tvDepartST;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvIdST= itemView.findViewById(R.id.tvIdST);
            tvNameST= itemView.findViewById(R.id.tvNameST);
            tvPhoneST= itemView.findViewById(R.id.tvPhoneST);
            tvPassST= itemView.findViewById(R.id.tvPassST);
            tvDateST= itemView.findViewById(R.id.tvDateST);
            tvDepartST= itemView.findViewById(R.id.tvDepartST);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopUpBP();
                    return true;
                }
            });
        }

        private void showPopUpBP() {
            PopupMenu popupMenu = new PopupMenu(context, itemView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_staff, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemNV:
                            Intent intentThemNV = new Intent(context, Add_StaffActivity.class);
                            context.startActivity(intentThemNV);
                            break;
                        case R.id.mnSuaNV:
                            Intent intentSuaNV = new Intent(context, Sua_NVActivity.class);
                            context.startActivity(intentSuaNV);
                            break;
                        case R.id.mnXoaNV:
                            int pos = getLayoutPosition();
                           deleteST(pos);
                            ((StaffActivity)context).LoadST();
                                    break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }




                private void deleteST (int pos){
                    System.out.println("data size: " + dataList.size());
                    System.out.println("id: " + dataList.get(pos).getId());
                    String url = "http://172.168.86.127:8080/api/motorshop/staffs?id=" + dataList.get(pos).getId();
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


}
