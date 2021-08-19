/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.MotorInfo;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.MotorInfoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class MotorInfoService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();
    
    @Autowired
    private MotorInfoRepository motorInfoRepo;
    
    @Transactional
    public String createMotorInfo(MotorInfo motorInfo) {        
        try {            
            if(isNull(motorInfo))
                return "Error! NULL Name!";            
            if(!h.isInfoContent(motorInfo.getName()))
                return "Error! Name contains alphas, nums, spaces and * , . ; : chars only!";
            formatMotorInfo(motorInfo);
            if(motorInfoRepo.existsByName(motorInfo.getName()))
                return "Error! Name existed!";
            else{
                motorInfoRepo.save(motorInfo);
                return SUCCESS;
            }                
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<MotorInfo> readAll() {
        return motorInfoRepo.findAll();
    } 
    
    public MotorInfo readById(String motorInfoId){        
        try{
            if(h.isNull(motorInfoId)) return null;
            if(!h.isNum(motorInfoId)) return null;
            int id = Integer.parseInt(motorInfoId);
        
            if(motorInfoRepo.existsById(id))
                return motorInfoRepo.findById(id).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<MotorInfo> readByName(String motorInfoName){        
        try{
            if(h.isNull(motorInfoName)) return null;
            motorInfoName = h.formatName(motorInfoName);
            
//            if(departRepo.existsById(departName))
                return motorInfoRepo.findByName(motorInfoName);
//            else
//                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String updateMotorInfo(MotorInfo motorInfo) {        
        try {            
            if(isNull(motorInfo))
                return "Error! NULL Name!"; 
            if(h.isNullNum(motorInfo.getId()))
                return "Error! Null ID!";
            if(!h.isInfoContent(motorInfo.getName()))
                return "Error! Name contains alphas, nums, spaces and * , . ; : chars only!";
            formatMotorInfo(motorInfo);
            if(!motorInfoRepo.existsById(motorInfo.getId()))
                return "Error! ID does not existed!";  
            if(motorInfoRepo.existsByNameOtherMotorInfo(motorInfo.getId(), motorInfo.getName()) != null)
                return "Error! Name existed!";
            else{
                motorInfoRepo.save(motorInfo);
                return SUCCESS;
            }                
        }catch(Exception e) {
            throw e;
        }
    }
    
    @Transactional
    public String deleteMotorInfo(String motorInfoId) {        
        try{
            if(h.isNull(motorInfoId)) return null;
            if(!h.isNum(motorInfoId)) return null;
            int id = Integer.parseInt(motorInfoId);
            
            if(motorInfoRepo.existsById(id)) {
                motorInfoRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! MotorInfo does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(MotorInfo motorInfo) {
        return h.isNull(motorInfo.getName());
    }
    
    public void formatMotorInfo(MotorInfo motorInfo) {
        motorInfo.setName(h.formatName(motorInfo.getName()));
    }
}
