/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.MotorDetail;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.MotorDetailRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class MotorDetailService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private MotorDetailRepository motorDetailRepo;
    
    @Transactional
    public String createMotorDetail(MotorDetail motorDetail) {       
        try {
            if(isNull(motorDetail))
                return "Error! NULL data!";
            if(!h.isNum(motorDetail.getMotorId().toString()))
                return "Error! MotorID only contains numbers!";
            if(!h.isNum(motorDetail.getMotorInfoId().toString()))
                return "Error! MotorInfoID only contains numbers!";
            if(motorDetailRepo.findByMotorIdAndMotorInfoId(motorDetail.getMotorId(), motorDetail.getMotorInfoId()) != null)
                return "Error! MotorID and MotorInfoID existed!";
            else{                
                motorDetailRepo.save(motorDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<MotorDetail> readAll() {
        return motorDetailRepo.findAll();
    } 
    
    public MotorDetail readById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(motorDetailRepo.existsById(idValue))
                return motorDetailRepo.findById(idValue).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    } 
    
    public List<Object[]> readClearAll() {
        return motorDetailRepo.findClearAll();
    }
    
    public List<Object[]> readClearByMotorName(String motorName){
        try{
            if(h.isNull(motorName)) return null;
            motorName = h.formatName(motorName);            
                
            return motorDetailRepo.findClearByMotorName(motorName);
            
        }catch(Exception e) {            
            throw e;
        }
    }        
    
    public List<Object[]> readClearByMotorIdAndContent(String motorId, String content){
        try{
            if(h.isNull(motorId) || h.isNull(content)) return null;
            if(!h.isNum(motorId)) return null;
            int id = Integer.parseInt(motorId);
                
            return motorDetailRepo.findClearByMotorIdAndContent(id, content);
            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }        
    
    //update CONTENT column
    @Transactional
    public String updateMotorDetail(MotorDetail motorDetail) {       
        try {
            if(isNull(motorDetail))
                return "Error! NULL data!";
            if(h.isNullNum(motorDetail.getId()))
                return "Error! Null ID";
            if(!h.isNum(motorDetail.getMotorId().toString()))
                return "Error! MotorID only contains numbers!";
            if(!h.isNum(motorDetail.getMotorInfoId().toString()))
                return "Error! MotorInfoID only contains numbers!";
            if(!motorDetailRepo.existsById(motorDetail.getId()))
                return "Error! ID does not exist!";
            if(motorDetailRepo.findByMotorIdAndMotorInfoIdOtherMotorDetail(motorDetail.getId(), motorDetail.getMotorId(), motorDetail.getMotorInfoId()) != null)
                return "Error! MotorID and MotorInfoID existed in other MotorDetail!";
            else{                
                motorDetailRepo.save(motorDetail);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    @Transactional    
    public String deleteMotorDetail(String motorId, String motorInfoId) {        
        try{
            if(h.isNull(motorId) || h.isNull(motorInfoId)) return null;
            if(!h.isNum(motorId) || !h.isNum(motorInfoId)) return null;
            int motorKey = Integer.parseInt(motorId);
            int motorInfoKey = Integer.parseInt(motorInfoId);
            
            motorDetailRepo.deleteByMotorIdAndMotorInfoId(motorKey, motorInfoKey);
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
            
            if(motorDetailRepo.existsById(idValue)){
                motorDetailRepo.deleteById(idValue);
                return SUCCESS;
            }else
                return "Error! MotorDetail does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }        
    
    public boolean isNull(MotorDetail motorDetail) {        
        return h.isNullNum(motorDetail.getMotorId()) || h.isNullNum(motorDetail.getMotorInfoId()) || h.isNull(motorDetail.getContent());
    }    
    
    
    //hanh
    public List<Object[]>getFrameNumber(String idInfo, String phone) {
        try{
            if(h.isNull(idInfo) || h.isNull(phone)) return null;
            
            int idmotorInfo = Integer.parseInt(idInfo);
         
            return motorDetailRepo.getFrameNumber(idmotorInfo, phone);
        }catch(NumberFormatException e) {            
            throw e;
        }
    }        
}
