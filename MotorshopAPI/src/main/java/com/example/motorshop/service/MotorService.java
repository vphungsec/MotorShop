/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Motor;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.BrandRepository;
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
public class MotorService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private MotorRepository motorRepo;
    @Autowired
    private BrandRepository brandRepo;
    
    @Transactional
    public String createMotor(Motor motor) {       
        try {
            if(isNull(motor))
                return "Error! NULL data!";
            if(!h.isAlpha(motor.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(motor.getAmount().toString()))
                return "Error! Amount only contains numbers!";
            if(!h.isNum(motor.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(!h.isNum(motor.getWarrantyPeriod().toString()))
                return "Error! Warranty Period only contains numbers! (months)";
            formatBrand(motor);
            if(motorRepo.existsByName(motor.getName()))
                return "Error! Name existed!";
            if(!brandRepo.existsById(motor.getBrandId()))
                return "Error! BrandID does not existed!";
            else{                
                motorRepo.save(motor);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Motor> readAll() {
        return motorRepo.findAll();
    }
    
    public Motor readById(String motorId) {        
        try{
            if(h.isNull(motorId)) return null;
            if(!h.isNum(motorId)) return null;
            int id = Integer.parseInt(motorId);
                        
            if(motorRepo.existsById(id))
                return motorRepo.findById(id).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Motor> readByName(String name) {        
        try{
            if(h.isNull(name)) return null;
            name = h.formatName(name);
            
//            if(ctmRepository.existsByName(name))
                return motorRepo.findByName(name);
//            else
//                return  null;
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String updateMotor(Motor motor) {       
        try {
            if(isNull(motor))
                return "Error! NULL data!";
            if(h.isNullNum(motor.getId()))
                return "Error! Null ID!";
            if(!h.isAlpha(motor.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(motor.getAmount().toString()))
                return "Error! Amount only contains numbers!";
            if(!h.isNum(motor.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(!h.isNum(motor.getWarrantyPeriod().toString()))
                return "Error! Warranty Period only contains numbers! (months)";
            formatBrand(motor);
            if(!motorRepo.existsById(motor.getId()))
                return "Error! ID does not existed!";    
            if(motorRepo.existsByNameOtherMotor(motor.getId(), motor.getName()) != null)
                return "Error! Name existed in other Motor!";
            if(!brandRepo.existsById(motor.getBrandId()))
                return "Error! BrandID does not existed!";
            else{                
                motorRepo.save(motor);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    @Transactional
    public String deleteMotor(String motorId) {        
        try{
            if(h.isNull(motorId)) return null;
            if(!h.isNum(motorId)) return null;
            int id = Integer.parseInt(motorId);
            
            if(motorRepo.existsById(id)) {
                motorRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! Motor does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(Motor motor) {        
        return h.isNull(motor.getName()) || h.isNullNum(motor.getAmount())
                || h.isNullNum(motor.getPrice()) || h.isNullNum(motor.getWarrantyPeriod())
                    || h.isNull(motor.getBrandId());
    }
    
    public void formatBrand(Motor motor) {
        motor.setName(h.formatName(motor.getName()));
        motor.setBrandId(motor.getBrandId().toUpperCase());
    }        
}
