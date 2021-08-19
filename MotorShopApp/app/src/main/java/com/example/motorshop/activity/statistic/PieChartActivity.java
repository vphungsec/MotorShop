package com.example.motorshop.activity.statistic;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motorshop.activity.R;
import com.example.motorshop.helper.Helper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PieChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private PieChart mChart;
    private TextView TextViewTenSP;
    private EditText editTextDateTu, editTextDateDen;
    private ImageView imageViewDateTu, imageViewDateDen, searchTK;
    private CheckBox checkBox_isSpinnerMode_tu, checkBox_isSpinnerMode_den;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private String tgTu, tgDen;
    int yData = 0;
    String xData = "";
    ArrayList<ThongKeTemp> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Thống kê theo số lượng");
        mChart.setCenterTextSize(15);
        mChart.setDrawEntryLabels(true);

        mChart.setOnChartValueSelectedListener(this);

        currentDate();
        setControl();
        setEvent();
    }

    private void setControl() {
        TextViewTenSP = findViewById(R.id.TextViewTenSP);
        editTextDateTu = findViewById(R.id.editText_date_tu);
        editTextDateDen = findViewById(R.id.editText_date_den);
        imageViewDateTu = findViewById(R.id.imageV_date_tu);
        imageViewDateDen = findViewById(R.id.imageV_date_den);
        searchTK = findViewById(R.id.searchTK);
        checkBox_isSpinnerMode_tu = findViewById(R.id.checkBox_isSpinnerMode_tu);
        checkBox_isSpinnerMode_den = findViewById(R.id.checkBox_isSpinnerMode_den);
    }

    public long getDate(String str) throws ParseException {
        return (new SimpleDateFormat("dd/MM/yyyy").parse(str)).getTime();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        for(ThongKeTemp tk : list){
            try {
                if(tk.getNgayDat() != null && getDate(tgTu) <= getDate(tk.getNgayDat()) && getDate(tk.getNgayDat()) <= getDate(tgDen)) {
                    String strIndex = String.valueOf(h.getX());
                    String replaceStr = strIndex.replace(".0", "");
                    String strTen = list.get(Integer.parseInt(replaceStr)).getTenSP();

                    Toast.makeText(this, "Số lượng bán được: "
                            + e.getY()
                            + ", Tên sản phẩm: "
                            + strTen
                            + ", Index: "
                            + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
                    TextViewTenSP.setText(strTen);
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
    }

    @Override
    public void onNothingSelected() {

    }

    public void currentDate(){
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
    }

    private void imageSelectDateDen() {
        final boolean isSpinnerModeDen = this.checkBox_isSpinnerMode_den.isChecked();

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editTextDateDen.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                tgDen = editTextDateDen.getText().toString().trim();

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;

        if(isSpinnerModeDen)  {
            // Create DatePickerDialog:
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }
        // Calendar Mode (Default):
        else {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }

    private void imageSelectDateTu() {
        final boolean isSpinnerModeTu = this.checkBox_isSpinnerMode_tu.isChecked();

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editTextDateTu.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                tgTu = editTextDateTu.getText().toString().trim();

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;

        if(isSpinnerModeTu)  {
            // Create DatePickerDialog:
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }
        // Calendar Mode (Default):
        else {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }

    public boolean ktThoiGian(String dateTu, String dateDen) {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = simpleDateFormat.parse(dateTu);
            date2 = simpleDateFormat.parse(dateDen);

            if (date2.getTime() < date1.getTime() || date1.getTime() > currentDate.getTime()) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setEvent() {
        imageViewDateTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelectDateTu();
            }
        });
        imageViewDateDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelectDateDen();
            }
        });
        searchTK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tgTu = editTextDateTu.getText().toString().trim();
                tgDen = editTextDateDen.getText().toString().trim();

                if(tgTu.length()>0&&tgDen.length()>0) {
                    if(ktThoiGian(tgTu,tgDen)==true){
                        try {
                            loadData(mChart, tgTu, tgDen);
                            Log.d("tg",tgTu+"-"+tgDen);
                            Toast.makeText(PieChartActivity.this, tgTu+"-"+tgDen, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(PieChartActivity.this, "Thoi gian khong hop le", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PieChartActivity.this, "khong dc de trong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loadData(PieChart pieChart, String tu, String den) throws ParseException{
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        String Url = "http://172.168.86.127:8080/api/motorshop/warrantyDetails/satistic";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest oStringRequest = new StringRequest(
                Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RestResponse", response.toString());
                        String ofString = response.toString();
                        if(new Helper().isNull(ofString)) Toast.makeText(getApplicationContext(),"Không có dữ liệu của tháng này",Toast.LENGTH_SHORT).show();
                        else{
                            String ofString2 = ofString.replace("[","");
                            String ofString3 = ofString2.replace("]","");
                            String ofString4 = ofString3.replace("\"","");
                            String[] tachStr = ofString4.split(",");
                            for(int i=0;i<tachStr.length;i=i+4){
                                ThongKeTemp thongKeTemp = new ThongKeTemp();
                                thongKeTemp.setTenSP(tachStr[i].toString().trim());
                                thongKeTemp.setSoLuong(tachStr[i+1].toString().trim());
                                thongKeTemp.setGiaBan(tachStr[i+2].toString().trim());
                                thongKeTemp.setNgayDat(tachStr[i+3].toString().trim());

                                list.add(thongKeTemp);
                            }
                            for(ThongKeTemp tk : list) {
                                Date date = null;
                                try {
                                    date = new SimpleDateFormat("dd/MM/yyyy").parse(tk.getNgayDat());
                                    Date dTu = new SimpleDateFormat("dd/MM/yyyy").parse(tgTu);
                                    Date dDen = new SimpleDateFormat("dd/MM/yyyy").parse(tgDen);
                                    if (tk.getNgayDat() != null && /*(*/dTu.getTime() <= date.getTime() && date.getTime() <= dDen.getTime()/*)*/) {
                                        xEntrys.add(String.valueOf(tk));
                                        yData = Integer.parseInt(tk.getSoLuong());
                                        xData = tk.getTenSP();

                                        yEntrys.add(new PieEntry(yData));
                                        xEntrys.add((xData));
                                    }

                                    PieDataSet pieDataSet = new PieDataSet(yEntrys, "Sản phẩm bán chạy");
                                    pieDataSet.setSliceSpace(2);
                                    pieDataSet.setValueTextSize(15);

                                    ArrayList<Integer> colors = new ArrayList<>();
                                    colors.add(Color.GRAY);
                                    colors.add(Color.BLUE);
                                    colors.add(Color.RED);

                                    pieDataSet.setColors(colors);

                                    Legend legend = pieChart.getLegend();
                                    legend.setForm(Legend.LegendForm.CIRCLE);
                                    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                                    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                                    legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                                    legend.setDrawInside(false);

                                    PieData pieData = new PieData(pieDataSet);
                                    pieChart.setData(pieData);
                                    pieChart.invalidate();

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse", error.toString());
                    }
                }
        );
        requestQueue.add(oStringRequest);
    }

}