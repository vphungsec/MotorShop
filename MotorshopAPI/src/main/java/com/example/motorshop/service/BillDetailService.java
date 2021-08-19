/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.BillDetail;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.AccessoryRepository;
import com.example.motorshop.repository.BillRepository;
import com.example.motorshop.repository.BillDetailRepository;
import com.example.motorshop.repository.MotorRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class BillDetailService {    
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private BillDetailRepository billDetailRepo;
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private MotorRepository motorRepo;
    @Autowired
    private AccessoryRepository accessoryRepo;
    
    @Transactional
    public String create(BillDetail billDetail) {      
        try {
            if(isNull(billDetail))
                return "Error! NULL data!";            
            if(!h.isNull(billDetail.getMotorId().toString()) && h.isNull(billDetail.getAccessoryId().toString())){
                if(!h.isNum(billDetail.getMotorId().toString()))
                    return "Error! MotorID only contains numbers!";
            }    
            if(h.isNull(billDetail.getMotorId().toString()) && !h.isNull(billDetail.getAccessoryId().toString())){
                if(!h.isNum(billDetail.getAccessoryId().toString()))
                    return "Error! AccessoryID only contains numbers!";
            }
            if(!h.isNull(billDetail.getMotorId().toString()) && !h.isNull(billDetail.getAccessoryId().toString()))
                return "Error! Both MotorID & AccessoryID was inserted!";
            if(!h.isNum(billDetail.getAmount().toString()))
                return "Error! Amount only contains numbers!";
            if(!h.isNum(billDetail.getPrice().toString()))
                return "Error! Price only contains numbers!";            
            if(!billRepo.existsById(billDetail.getBillId()))
                return "Error! BillID does not existed!";
            if(!motorRepo.existsById(billDetail.getMotorId()))
                return "Error! MotorID does not existed!";
            if(!accessoryRepo.existsById(billDetail.getAccessoryId()))
                return "Error! AccessoryID does not existed!";
            else{                
                billDetailRepo.save(billDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<BillDetail> readAll() {
        return billDetailRepo.findAll();
    }
    
    public BillDetail readById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(billDetailRepo.existsById(idValue))
                return billDetailRepo.findById(idValue).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readByMotorName(String motorName) {
        try{
            if(h.isNull(motorName)) return null;
            motorName = h.formatName(motorName);
                        
            return billDetailRepo.findByMotorName(motorName);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAllCombined() {
        try{                                    
            return billDetailRepo.findClearAllCombined();
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAllUnCombined() {
        try{                                    
            return billDetailRepo.findClearAllUnCombined();
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAllUnCombined(String billId) {
        try{      
            int bId = Integer.parseInt(billId);
            return billDetailRepo.findClearAllUnCombined(bId);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearByBillId(String billId) {
        try{                                    
            if(h.isNull(billId)) return null;
            if(!h.isNum(billId)) return null;
            int id = Integer.parseInt(billId);
            
            return billDetailRepo.findClearByBillId(id);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
   
    
    public List<Object[]> readClearByProductName(String productName) {
        try{                                    
            if(h.isNull(productName)) return null;
            productName = h.formatName(productName);
            
            return billDetailRepo.findClearByProductName(productName);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    @Transactional    
    public String delete(String id) {        
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
            
            if(billDetailRepo.existsById(idValue)){
                billDetailRepo.deleteById(idValue);
                return SUCCESS;
            }else
                return "Error! BillDetail does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(BillDetail billDetail) {        
        return h.isNullNum(billDetail.getBillId()) || h.isNullNum(billDetail.getAmount())
                || (h.isNullNum(billDetail.getMotorId()) && h.isNullNum(billDetail.getAccessoryId()))
                    || h.isNullNum(billDetail.getPrice());
    }  
    // service made by Dang
     public List<Object[]> readClearByBillIdV2(String billId) {
        try{                                    
            if(h.isNull(billId)) return null;
            if(!h.isNum(billId)) return null;
            int id = Integer.parseInt(billId);
            
            return billDetailRepo.findClearByBillIdV2(id);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    public List<Object[]>getOrderDetail(){
        return billDetailRepo.getOrderDetail();
    }
    
    public List<Object[]>getListProductName(){
        return billDetailRepo.getListProductName();
    }
     public List<Object[]>getListProductByName(String name){
        return billDetailRepo.getListProductByName(name);
    }
        /////////-*-------------------------------------------------------------------------- 
     public List<Object[]>getOrderDetailMI(){
        return billDetailRepo.getOrderDetailMI();
    }
 
      public List<Object[]>getOrderDetailMD(){
        return billDetailRepo.getOrderDetailMD();
    }
    
}
