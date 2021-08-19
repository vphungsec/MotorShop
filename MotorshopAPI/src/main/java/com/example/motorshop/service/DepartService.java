/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Depart;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.DepartRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class DepartService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();
    
    @Autowired
    private DepartRepository departRepo;
    
    @Transactional
    public String createDepart(Depart depart) {        
        try {            
            if(isNull(depart))
                return "Error! NULL data!";
            if(!h.isAlphaAndNum(depart.getId()) || !h.isNoSpace(depart.getId()) || depart.getId().length() > 4)
                return "Error! ID's max length is 4, contains alphas and nums only!";
            if(!h.isAlphaAndNum(depart.getName()))
                return "Error! Name contains alphas, nums & spaces only!";
            formatDepart(depart);
            if(departRepo.existsById(depart.getId()) || departRepo.existsByName(depart.getName()))
                return "Error! ID or Name existed!";
            else{
                departRepo.save(depart);
                return SUCCESS;
            }                
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Depart> readAll() {
        return departRepo.findAll();
    } 
    
    public Depart readById(String departId){        
        try{
            if(h.isNull(departId)) return null;
            departId = departId.toUpperCase();
        
            if(departRepo.existsById(departId))
                return departRepo.findById(departId).get();
            else
                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Depart> readByName(String departName){        
        try{
            if(h.isNull(departName)) return null;
            departName = h.formatName(departName);
            
//            if(departRepo.existsById(departName))
                return departRepo.findByName(departName);
//            else
//                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String updateDepart(Depart depart) {
        try{
            if(isNull(depart))
                return "Error! NULL data!";            
            if(!h.isAlphaAndNum(depart.getId()) || !h.isNoSpace(depart.getId()) || depart.getId().length() > 4)
                return "Error! ID's max length is 4, contains alphas and nums only!";
            if(!h.isAlphaAndNum(depart.getName()))
                return "Error! Name contains alphas, nums & spaces only!";
            formatDepart(depart);
            if(!departRepo.existsById(depart.getId()))
                return "Error! ID does not exist!";
            if(departRepo.existsByNameOtherDepart(depart.getId(), depart.getName()) != null)
                return "Error! Name existed in other Depart!";
            else{
                departRepo.save(depart);
                return SUCCESS;
            }                
        }catch(Exception e) {            
            throw e;
        }               
    }
    
    @Transactional
    public String deleteDepart(String departId) {        
        try{
            if(h.isNull(departId)) return null;
            departId = departId.toUpperCase();
            
            if(departRepo.existsById(departId)) {
                departRepo.deleteById(departId);
                return SUCCESS;
            }else
                return "Error! Depart does not exist!";            
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public boolean isNull(Depart depart) {
        return h.isNull(depart.getId()) || h.isNull(depart.getName());
    }
    
    public void formatDepart(Depart depart) {
        depart.setId(depart.getId().toUpperCase());
        depart.setName(h.formatName(depart.getName()));
    }
}
