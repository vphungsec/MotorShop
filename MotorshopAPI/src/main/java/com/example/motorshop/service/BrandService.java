/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Brand;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.BrandRepository;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 *
 * @author hungh
 */
@Service
public class BrandService {
    
    private static final String ID_MODEL = "BR";
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private BrandRepository brandRepo;
    
    @Transactional
    public String createBrand(Brand brand) {       
        try {
            if(isNull(brand))
                return "Error! NULL data!";
            if(!h.isAlpha(brand.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(brand.getPhone()))
                return "Error! Phone only contains numbers!";
            if(brand.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(!h.isValidEmail(brand.getEmail()))
                return "Error! Email only contains alphas, nums, dots and @!";
            formatBrand(brand);
            if(brandRepo.existsByName(brand.getName()))
                return "Error! Name existed!";
            if(brandRepo.existsByPhone(brand.getPhone()))
                return "Error! Phone existed!";
            if(brandRepo.existsByPhone(brand.getEmail()))
                return "Error! Email existed!";            
            else{           
                String maxId = brandRepo.findMaxId();
                brand.setId(maxId == null ? (ID_MODEL + "01") : h.findNextId(maxId));
                brandRepo.save(brand);
                return SUCCESS;
            }
        } catch(Exception e) {
            throw e;
        }
    }
    
    public List<Brand> readAll() {
        return brandRepo.findAll();
    } 
    
    public Brand readById(String id) {        
        try{
            if(h.isNull(id)) return null;
            id = id.toUpperCase();            
                        
            if(brandRepo.existsById(id))
                return brandRepo.findById(id).get();
            else
                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }    
    
    public List<Brand> readByName(String name) {        
        try{
            if(h.isNull(name)) return null;
            name = h.formatName(name);
            
//            if(ctmRepository.existsByName(name))
                return brandRepo.findByName(name);
//            else
//                return  null;
        } catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Brand> readByPhone(String phone) {        
        try{
            if(h.isNull(phone)) return null; 
            
//            if(ctmRepository.existsByPhone(phone))
                return brandRepo.findByPhone(phone);
//            else
//                return null;                         
        } catch(Exception e) {            
            throw e;
        }
    }            
    
    public List<Brand> readByEmail(String email) {        
        try{
            if(h.isNull(email)) return null;
            email = email.toLowerCase();
            
//            if(ctmRepository.existsByPhone(phone))
                return brandRepo.findByEmail(email);
//            else
//                return null;                         
        } catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String updateBrand(Brand brand) {
        try{
            if(isNull(brand))
                return "Error! NULL data!";
            if(h.isNull(brand.getId()))
                return "Error! Null ID!";
            if(!h.isAlpha(brand.getName()))
                return "Error! Name only contains alphas & spaces!";
            if(!h.isNum(brand.getPhone()))
                return "Error! Phone only contains numbers!";
            if(brand.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(!h.isValidEmail(brand.getEmail()))
                return "Error! Email only contains alphas, nums, dots and @!";
            formatBrand(brand);
            if(!brandRepo.existsById(brand.getId()))
                return "Error! ID does not exist!";
            if(brandRepo.existsByNameOtherBrand(brand.getId(), brand.getName()) != null)
                return "Error! Name existed in other Brand!"; 
            if(brandRepo.existsByPhoneOtherBrand(brand.getId(), brand.getPhone()) != null)
                return "Error! Phone existed in other Brand!";            
            if(brandRepo.existsByEmailOtherBrand(brand.getId(), brand.getEmail()) != null)
                return "Error! Email existed in other Brand!";
            else{                
                brandRepo.save(brand);
                return SUCCESS;
            }                        
        } catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String deleteBrand(String brandId) {        
        try{
            if(h.isNull(brandId)) return null; 
            brandId = brandId.toUpperCase();
            
            if(brandRepo.existsById(brandId)) {
                brandRepo.deleteById(brandId);
                return SUCCESS;
            }else
                return "Error! Brand does not exist!";            
        } catch(Exception e) {            
            throw e;
        }
    }        
    
    public boolean isNull(Brand brand) {        
        return h.isNull(brand.getName()) || h.isNull(brand.getAddress()) || h.isNull(brand.getPhone()) || h.isNull(brand.getEmail());
    }
    
    public void formatBrand(Brand brand) {
        if(brand.getId() != null)
            brand.setId(brand.getId().toUpperCase());
        brand.setName(h.formatName(brand.getName()));
        brand.setEmail(brand.getEmail().toLowerCase());
    }
}
