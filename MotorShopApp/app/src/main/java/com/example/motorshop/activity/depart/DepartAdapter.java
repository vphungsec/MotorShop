package com.example.motorshop.activity.depart;

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
import com.example.motorshop.datasrc.Depart;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DepartAdapter extends RecyclerView.Adapter<DepartAdapter.ViewHolder> {
    Context context;
    int resource;
    List<Depart> dataList;
    RecyclerView rvDataBP;
    private RequestQueue requestQueue;


    public DepartAdapter(Context context,  List<Depart> dataDP) {
        this.context = context;
        dataList = dataDP;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_depart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Depart currentDepart = dataList.get(position);
        String id = currentDepart.getId();
        String name = currentDepart.getName();
        holder.tvIdDP.setText("Mã BP: " + id);
        holder.tvNameDP.setText("Tên BP: "+name);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdDP, tvNameDP;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvIdDP = itemView.findViewById(R.id.tvIdDP);
            tvNameDP = itemView.findViewById(R.id.tvNameDP);
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
            popupMenu.getMenuInflater().inflate(R.menu.menu_depart, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemBP:
                            Intent intentThemBP = new Intent(context, Add_DepartActivity.class);
                            context.startActivity(intentThemBP);
                            break;
                        case R.id.mnSuaBP:
                            Intent intentSuaBP = new Intent(context,Sua_BPAcitivy.class);
                            context.startActivity(intentSuaBP);
                            break;
                        case R.id.mnXoaBP:
                           int pos = getLayoutPosition();

                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }

    private void deleteST (int pos){
        System.out.println("data size: " + dataList.size());
        System.out.println("id: " + dataList.get(pos).getId());
        String url = "http://172.168.86.127:8080/api/motorshop/depart?id=" + dataList.get(pos).getId();
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
