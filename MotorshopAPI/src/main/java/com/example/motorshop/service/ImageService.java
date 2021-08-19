/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Image;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.ImageRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class ImageService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private ImageRepository imageRepo;
    
    
    @Transactional
    public String createImage(Image image) {       
        try {
            if(isNull(image))                
                return "Error! NULL data!";             
            if(!h.isNull(image.getMotorId().toString()) && h.isNull(image.getAccessoryId().toString())){
                if(!h.isNum(image.getMotorId().toString()))
                    return "Error! MotorID only contains numbers!";
            }    
            if(h.isNull(image.getMotorId().toString()) && !h.isNull(image.getAccessoryId().toString())){
                if(!h.isNum(image.getAccessoryId().toString()))
                    return "Error! AccessoryID only contains numbers!";
            }
            if(!h.isNull(image.getMotorId().toString()) && !h.isNull(image.getAccessoryId().toString()))
                return "Error! Both MotorID & AccessoryID was inserted!";
            else{
                imageRepo.save(image);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Image> readAll() {
        return imageRepo.findAll();
    }
    
    public List<byte[]> readByMotorId(String motorId) {
        try{
            if(h.isNull(motorId)) return null;
            if(!h.isNum(motorId)) return null;
            int id = Integer.parseInt(motorId);
                        
            if(imageRepo.existsById(id))
                return imageRepo.findByMotorId(id);
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<byte[]> readByAccessoryId(String motorId) {
        try{
            if(h.isNull(motorId)) return null;
            if(!h.isNum(motorId)) return null;
            int id = Integer.parseInt(motorId);
                        
            if(imageRepo.existsById(id))
                return imageRepo.findByAccessoryId(id);
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    @Transactional
    public String deleteImage(String imageId) {      
        try{
            if(h.isNull(imageId)) return null;
            if(!h.isNum(imageId)) return null;
            int id = Integer.parseInt(imageId);
            
            if(imageRepo.existsById(id)) {
                imageRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! Image does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(Image image) {        
        return (h.isNullNum(image.getMotorId()) && h.isNullNum(image.getAccessoryId())) || image.getImage() == null || (image.getImage()).length <= 0;
    }
}
