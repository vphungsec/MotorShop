/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.AccessoryDetail;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.AccessoryDetailRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class AccessoryDetailService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private AccessoryDetailRepository accessoryDetailRepo;
    
    @Transactional
    public String createAccessoryDetail(AccessoryDetail accessoryDetail) {       
        try {
            if(isNull(accessoryDetail))
                return "Error! NULL data!";
            if(!h.isNum(accessoryDetail.getAccessoryId().toString()))
                return "Error! AccessoryID only contains numbers!";
            if(!h.isNum(accessoryDetail.getMotorId().toString()))
                return "Error! MotorID only contains numbers!";
            if(!h.isNum(accessoryDetail.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(accessoryDetailRepo.findByAccessoryIdAndMotorId(accessoryDetail.getAccessoryId(), accessoryDetail.getMotorId()) != null)
                return "Error! AccessoryID and MotorID existed!";
            else{                
                accessoryDetailRepo.save(accessoryDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<AccessoryDetail> readAll() {
        return accessoryDetailRepo.findAll();
    } 
    
    public List<Object[]> readClearAll() {
        return accessoryDetailRepo.findClearAll();
    }
    
    //update PRICE column
    @Transactional
    public String updateAccessoryDetail(AccessoryDetail accessoryDetail) {       
        try {
            if(isNull(accessoryDetail))
                return "Error! NULL data!";
            if(h.isNullNum(accessoryDetail.getId()))
                return "Error! Null ID";
            if(!h.isNum(accessoryDetail.getAccessoryId().toString()))
                return "Error! AccessoryID only contains numbers!";
            if(!h.isNum(accessoryDetail.getMotorId().toString()))
                return "Error! MotorID only contains numbers!";
            if(!h.isNum(accessoryDetail.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(accessoryDetailRepo.findByAccessoryIdAndMotorIdOtherAccessoryDetail(accessoryDetail.getId(), accessoryDetail.getAccessoryId(), accessoryDetail.getMotorId()) != null)
                return "Error! AccessoryID and MotorID existed  in other AccessoryDetail!";
            else{                
                accessoryDetailRepo.save(accessoryDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }        
    
    @Transactional    
    public String deleteAccessoryDetail(String accessoryId, String motorId) {        
        try{
            if(h.isNull(accessoryId) || h.isNull(motorId)) return null;
            if(!h.isNum(accessoryId) || !h.isNum(motorId)) return null;
            int accessoryKey = Integer.parseInt(accessoryId);
            int motorKey = Integer.parseInt(motorId);
            
            accessoryDetailRepo.deleteByaccessoryIdAndMotorId(accessoryKey, motorKey);
            return SUCCESS;
            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    @Transactional    
    public String deleteById(String id) {        
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
            
            if(accessoryDetailRepo.existsById(idValue)) {
                accessoryDetailRepo.deleteById(idValue);
                return SUCCESS;
            }else
                return "Error! AccessoryDetail does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(AccessoryDetail accessoryDetail) {        
        return h.isNullNum(accessoryDetail.getAccessoryId()) || h.isNullNum(accessoryDetail.getMotorId()) || h.isNullNum(accessoryDetail.getPrice());
    }    
}
