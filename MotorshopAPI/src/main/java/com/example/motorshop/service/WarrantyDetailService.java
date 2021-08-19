/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.WarrantyDetail;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.AccessoryRepository;
import com.example.motorshop.repository.WarrantyRepository;
import com.example.motorshop.repository.WarrantyDetailRepository;
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
public class WarrantyDetailService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private WarrantyDetailRepository warraytyDetailRepo;    
    @Autowired
    private WarrantyRepository warrantyRepo;
    @Autowired
    private MotorRepository motorRepo;
    @Autowired
    private AccessoryRepository accessoryRepo;
    
    @Transactional
    public String create(WarrantyDetail warrantyDetail) {      
        try {
            if(isNull(warrantyDetail))
                return "Error! NULL data!";            
            if(!h.isNull(warrantyDetail.getMotorId().toString()) && h.isNull(warrantyDetail.getAccessoryId().toString())){
                if(!h.isNum(warrantyDetail.getMotorId().toString()))
                    return "Error! MotorID only contains numbers!";
            }    
            if(h.isNull(warrantyDetail.getMotorId().toString()) && !h.isNull(warrantyDetail.getAccessoryId().toString())){
                if(!h.isNum(warrantyDetail.getAccessoryId().toString()))
                    return "Error! AccessoryID only contains numbers!";
            }
            if(!h.isNull(warrantyDetail.getMotorId().toString()) && !h.isNull(warrantyDetail.getAccessoryId().toString()))
                return "Error! Both MotorID & AccessoryID was inserted!";
            if(!h.isInfoContent(warrantyDetail.getContent()))
                return "Error! Content contains alphas, nums, spaces and * , . ; : chars only!";
            if(!h.isNum(warrantyDetail.getPrice().toString()))
                return "Error! Price only contains numbers!";            
            if(!warrantyRepo.existsById(warrantyDetail.getWarrantyId()))
                return "Error! WarrantyID does not existed!";
            if(!motorRepo.existsById(warrantyDetail.getMotorId()))
                return "Error! MotorID does not existed!";
            if(!accessoryRepo.existsById(warrantyDetail.getAccessoryId()))
                return "Error! AccessoryID does not existed!";
            else{                
                warraytyDetailRepo.save(warrantyDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<WarrantyDetail> readAll() {
        return warraytyDetailRepo.findAll();
    }
    
    public WarrantyDetail readById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(warraytyDetailRepo.existsById(idValue))
                return warraytyDetailRepo.findById(idValue).get();
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
                        
            return warraytyDetailRepo.findByMotorName(motorName);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAllCombined() {
        try{                                    
            return warraytyDetailRepo.findClearAllCombined();
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAllUnCombined() {
        try{                                    
            return warraytyDetailRepo.findClearAllUnCombined();
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearByWarrantyId(String warrantyId) {
        try{                                    
            if(h.isNull(warrantyId)) return null;
            if(!h.isNum(warrantyId)) return null;
            int id = Integer.parseInt(warrantyId);
            
            return warraytyDetailRepo.findClearByWarrantyId(id);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearByProductName(String productName) {
        try{                                    
            if(h.isNull(productName)) return null;
            productName = h.formatName(productName);
            
            return warraytyDetailRepo.findClearByProductName(productName);
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
            
            if(warraytyDetailRepo.existsById(idValue)){
                warraytyDetailRepo.deleteById(idValue);
                return SUCCESS;
            }else
                return "Error! WarrantyDetail does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    
    public boolean isNull(WarrantyDetail warrantyDetail) {        
        return h.isNullNum(warrantyDetail.getWarrantyId()) || h.isNullNum(warrantyDetail.getPrice())
                || (h.isNullNum(warrantyDetail.getMotorId()) && h.isNullNum(warrantyDetail.getAccessoryId()))
                    || h.isNull(warrantyDetail.getContent());
    }   
    
        //MoreHanh
    public List<Object[]>getFrameName(String nameFrame) {
        try{                                    
            if(h.isNull(nameFrame)) return null;

            return warraytyDetailRepo.getNameFrame(nameFrame);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }

    public List<Object[]>getFrameNumber() {
            return warraytyDetailRepo.getFrame();
    }

    public List<Object[]>getDetailCustomter(String idInfo, String frameNumber) {
        try{
            if(h.isNull(idInfo) || h.isNull(frameNumber)) return null;

            int idmotorInfo = Integer.parseInt(idInfo);

            return warraytyDetailRepo.getDetailCustomter(idmotorInfo, frameNumber);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }

    public List<Object[]>getFrameNumber(String idInfo, String phone) {
        try{
            if(h.isNull(idInfo) || h.isNull(phone)) return null;

            int idmotorInfo = Integer.parseInt(idInfo);

            return warraytyDetailRepo.getFrameNumber(idmotorInfo, phone);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
}
