/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Staff;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.DepartRepository;
import com.example.motorshop.repository.StaffRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class StaffService {
    
    private static final String ID_MODEL = "ST";
    private static final String HASH_MODEL = "MD5";
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();
    
    @Autowired
    private StaffRepository staffRepo;        
    @Autowired
    private DepartRepository departRepo;     
    
    
    @Transactional
    public String createStaff(Staff staff) {        
        try {
            if(isNull(staff))
                return "Error! NULL data!";
            if(!h.isAlpha(staff.getName()))
                return "Error! Name contains alphas & spaces only!";
            if(!h.isNum(staff.getPhone()))
                return "Error! Phone contains numbers only!";
            if(staff.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(staff.getPassWord().length() < 4)
                return "Error! Password's min length is 4!";
            formatStaff(staff);
            if(staffRepo.existsByPhone(staff.getPhone()))
                return "Error! Phone existed!";
            if(!departRepo.existsById(staff.getDepartId()))
                return "Error! DepartID does not exist!";
            else{
                String maxId = staffRepo.findMaxId();
                staff.setId(maxId == null ? (ID_MODEL + "01") : h.findNextId(maxId));  
                staff.setPassWord(h.getCryptoHash(staff.getPassWord(), HASH_MODEL));
                staffRepo.save(staff);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Staff> readAll() {
        return staffRepo.findAll();
    } 
    
    public List<Object[]> readClearAll() {
        return staffRepo.findClearAll();
    }
    
    public Staff readById(String id) {        
        try{
            if(h.isNull(id)) return null;
            id = id.toUpperCase();
            
            if(staffRepo.existsById(id))
                return staffRepo.findById(id).get();
            else
                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Staff> readByName(String name) {        
        try{
            if(h.isNull(name)) return null;
            name = h.formatName(name);
            
//            if(staffRepo.existsByName(name))
                return staffRepo.findByName(name);
//            else
//                return null;
        }catch(Exception e) {            
            throw e;
        }
    } 
    
    public List<Staff> readByPhone(String phone) {           
        try{
            if(h.isNull(phone)) return null;
            
//            if(staffRepo.existsByPhone(phone))
                return staffRepo.findByPhone(phone);
//            else
//                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }    
    
    
    
    @Transactional
    public String updateStaff(Staff staff) {
        try{
            if(isNull(staff))
                return "Error! NULL data!";
            if(h.isNull(staff.getId()))
                return "Error! Null ID!";
            if(!h.isAlpha(staff.getName()))
                return "Error! Name contains alphas & spaces only!";
            if(!h.isNum(staff.getPhone()))
                return "Error! Phone contains numbers only!";
            if(staff.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(staff.getPassWord().length() < 4)
                return "Error! Password's min length is 4!";
            formatStaff(staff);            
            if(!staffRepo.existsById(staff.getId()))
                return "Error! ID does not exist!";
            if(staffRepo.existsByPhoneOtherStaff(staff.getId(), staff.getPhone()) != null)
                return "Error! Phone existed in other Staff!";
            if(!departRepo.existsById(staff.getDepartId()))
                return "Error! DepartID does not exist!";
            else{                            
                staff.setPassWord(h.getCryptoHash(staff.getPassWord(), HASH_MODEL));
                staffRepo.save(staff);
                return SUCCESS;
            }                                 
        }catch(Exception e) {           
            throw e;
        }               
    }
    
    @Transactional
    public String deleteStaff(String staffId) {        
        try{
            if(h.isNull(staffId)) return null;
            staffId = staffId.toUpperCase();
            
            if(staffRepo.existsById(staffId)) {
                staffRepo.deleteById(staffId);
                return SUCCESS;
            }else
                return "Error! Staff does not exist!";            
        }catch(Exception e) {            
            throw e;
        }
    }        
    
    public String authenticate(String usn, String pwd) {
        if(h.isNull(usn) || h.isNull(pwd) || !h.isNum(usn) || usn.length() > 15 || pwd.length() < 4) return null;
        String staffId = staffRepo.existsByPhoneAndPassword(usn, pwd);
        if(staffId != null ) return staffId;
        else return null;
    }
    
    public boolean isNull(Staff staff) {    
        return h.isNull(staff.getName()) || h.isNull(staff.getPhone())
                || h.isNull(staff.getPassWord()) || h.isNull(staff.getDepartId())
                    || h.isNull(staff.getCreatedDate());       
    }   
    
    public void formatStaff(Staff staff) {
        if(!h.isNull(staff.getId()))
            staff.setId(staff.getId().toUpperCase());        
        staff.setName(h.formatName(staff.getName()));        
        staff.setDepartId(staff.getDepartId().toUpperCase());
    }
}