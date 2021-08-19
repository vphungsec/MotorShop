/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Accessory;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.BrandRepository;
import com.example.motorshop.repository.AccessoryRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class AccessoryService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private AccessoryRepository accessoryRepo;
    @Autowired
    private BrandRepository brandRepo;
    
    @Transactional
    public String createAccessory(Accessory accessory) {       
        try {
            if(isNull(accessory))
                return "Error! NULL data!";
            if(!h.isAlpha(accessory.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(accessory.getAmount().toString()))
                return "Error! Amount only contains numbers!";
            if(!h.isNum(accessory.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(!h.isNum(accessory.getWarrantyPeriod().toString()))
                return "Error! Warranty Period only contains numbers! (months)";
            formatBrand(accessory);
            if(accessoryRepo.existsByName(accessory.getName()))
                return "Error! Name existed!";
            if(!brandRepo.existsById(accessory.getBrandId()))
                return "Error! BrandID does not existed!";
            else{                
                accessoryRepo.save(accessory);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Accessory> readAll() {
        return accessoryRepo.findAll();
    }
    
    public Accessory readById(String accessoryId) {        
        try{
            if(h.isNull(accessoryId)) return null;
            if(!h.isNum(accessoryId)) return null;
            int id = Integer.parseInt(accessoryId);
                        
            if(accessoryRepo.existsById(id))
                return accessoryRepo.findById(id).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Accessory> readByName(String name) {        
        try{
            if(h.isNull(name)) return null;
            name = h.formatName(name);
            
//            if(ctmRepository.existsByName(name))
                return accessoryRepo.findByName(name);
//            else
//                return  null;
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String updateAccessory(Accessory accessory) {       
        try {
            if(isNull(accessory))
                return "Error! NULL data!";
            if(h.isNullNum(accessory.getId()))
                return "Error! Null ID!";
            if(!h.isAlpha(accessory.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(accessory.getAmount().toString()))
                return "Error! Amount only contains numbers!";
            if(!h.isNum(accessory.getPrice().toString()))
                return "Error! Price only contains numbers!";
            if(!h.isNum(accessory.getWarrantyPeriod().toString()))
                return "Error! Warranty Period only contains numbers! (months)";
            formatBrand(accessory);
            if(!accessoryRepo.existsById(accessory.getId()))
                return "Error! ID does not existed!";    
            if(accessoryRepo.existsByNameOtherAccessory(accessory.getId(), accessory.getName()) != null)
                return "Error! Name existed in other accessory!";
            if(!brandRepo.existsById(accessory.getBrandId()))
                return "Error! BrandID does not existed!";
            else{                
                accessoryRepo.save(accessory);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    @Transactional
    public String deleteAccessory(String accessoryId) {        
        try{
            if(h.isNull(accessoryId)) return null;
            if(!h.isNum(accessoryId)) return null;
            int id = Integer.parseInt(accessoryId);
            
            if(accessoryRepo.existsById(id)) {
                accessoryRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! Staff does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(Accessory accessory) {        
        return h.isNull(accessory.getName()) || h.isNullNum(accessory.getAmount())
                || h.isNullNum(accessory.getPrice()) || h.isNullNum(accessory.getWarrantyPeriod())
                    || h.isNull(accessory.getBrandId());
    }
    
    public void formatBrand(Accessory accessory) {
        accessory.setName(h.formatName(accessory.getName()));
        accessory.setBrandId(accessory.getBrandId().toUpperCase());
    }
    
}
