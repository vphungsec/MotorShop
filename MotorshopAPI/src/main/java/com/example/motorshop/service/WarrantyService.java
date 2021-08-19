/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Warranty;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.BillRepository;
import com.example.motorshop.repository.StaffRepository;
import com.example.motorshop.repository.WarrantyRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class WarrantyService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private WarrantyRepository warrantyRepo;
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private StaffRepository staffRepo;
    
    @Transactional
    public String create(Warranty warranty) {       
        try {
            if(isNull(warranty))
                return "Error! NULL data!";
            formatBill(warranty);
            if(!h.isNum(warranty.getBillId().toString()))
                return "Error! BillID only contains numbers!";
            if(!billRepo.existsById(warranty.getBillId()))
                return "Error! BillID does not exist!";
            if(!staffRepo.existsById(warranty.getStaffId()))
                return "Error! StaffID does not exist!";            
            else{
                warrantyRepo.save(warranty);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Warranty> readAll() {
        return warrantyRepo.findAll();
    }
    
    public Warranty readById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(warrantyRepo.existsById(idValue))
                return warrantyRepo.findById(idValue).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAll() {
        return warrantyRepo.findClearAll();
    }
    
    public Warranty readClearById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(warrantyRepo.existsById(idValue))
                return warrantyRepo.findClearById(idValue);
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearByStaffName(String staffName) {
        try{
            if(h.isNull(staffName)) return null;
            staffName = h.formatName(staffName);
            
            return warrantyRepo.findClearByStaffName(staffName);
            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    @Transactional    
    public String delete(String warrantyId) {        
        try{
            if(h.isNull(warrantyId)) return null;
            if(!h.isNum(warrantyId)) return null;
            int id = Integer.parseInt(warrantyId);
            
            if(warrantyRepo.existsById(id)){
                warrantyRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! Warrannty does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public boolean isNull(Warranty warranty) {        
        return h.isNullNum(warranty.getBillId()) || h.isNull(warranty.getCreatedDate()) || h.isNull(warranty.getStaffId());
    }
    
    public void formatBill(Warranty warranty) {           
        warranty.setStaffId(warranty.getStaffId().toUpperCase());
    }       
}
