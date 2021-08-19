package com.example.motorshop.activity.warranty.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class BotResponses {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String basicResponses(String message){
        double random = Math.floor(Math.random() * 0.2);
        Random generator = new Random();
//        int value = generator.nextInt(4) + 1;
        int value = generator.nextInt((2 - 0) + 1) + 0;
        String mess = message.toLowerCase();
        if(mess.contains("xin chào")){
            switch ((int) random){
                case 1:
                    mess = "Chào bạn, bạn cần mình giúp gì?";
                    break;
                case 2:
                    mess = "Bạn cho mình xin sdt, nhân viên bên mình sẽ gọi điện cho bạn nhé";
                    break;
                case 3:
                    mess = "Xin mời đến các cửa hàng gần nhất";
                    break;
                default: mess = "error";
                    break;
            }
        }
        if(mess.contains("cám ơn ad")||mess.contains("cám ơn shop")||mess.contains("cám ơn bạn")){
            switch ((int) random){
                case 1:
                    mess = "Không có gì ạ";
                    break;
                case 2:
                    mess = "Nếu bạn cần thêm gì thì cứ nhắn tin cho shop nhé";
                    break;
                case 3:
                    mess = "Cám ơn bạn";
                    break;
                default: mess = "error";
                    break;
            }
        }
        if(mess.contains("solve")){
//            String equation = mess.substring(Integer.parseInt("solve"));

            String equation = mess.substring(mess.lastIndexOf("solve"));
            try {
                int answer = SolveMath.solveMath(equation);
                String strAn = String.valueOf(answer);
            } catch (Exception e){
                mess = "Thật xin lỗi, bên shop sẽ cố gắng cải thiện app";
            }

        }
        if(mess.contains("time") && mess.contains("?")){
            Time time = new Time();
            mess = time.timeStamp();

        }
        if(mess.contains("mở google")||mess.contains("google")){
            mess = Constants.OPEN_GOOGLE;
        }
        if(mess.contains("tìm kiếm")||mess.contains("search")){
            mess = Constants.OPEN_GOOGLE;
        }
        else mess = "rất vui được tư vấn cho bạn, chúc bạn 1 ngày tốt lành^^";
        return mess;
    }
}
