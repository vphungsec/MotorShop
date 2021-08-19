/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Bill;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.BillRepository;
import com.example.motorshop.repository.CustomerRepository;
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
public class BillService {
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();    
    
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private StaffRepository staffRepo;
    @Autowired
    private CustomerRepository customerRepo;
       
    @Transactional
    public String create(Bill bill) {       
        try {
            if(isNull(bill))
                return "Error! NULL data!";
            formatBill(bill);
            if(!customerRepo.existsById(bill.getCustomerId()))
                return "Error! CustomerID does not exist!";
            if(!staffRepo.existsById(bill.getStaffId()))
                return "Error! StaffID does not exist!";            
            else{                
                billRepo.save(bill);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Bill> readAll() {
        return billRepo.findAll();
    }
    
    public Bill readById(String id) {
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                        
            if(billRepo.existsById(idValue))
                return billRepo.findById(idValue).get();
            else
                return null;                         
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearAll() {
        return billRepo.findClearAll();
    }
    
    public Bill readClearById(String id){
        try{
            if(h.isNull(id)) return null;
            if(!h.isNum(id)) return null;
            int idValue = Integer.parseInt(id);
                
            return billRepo.findClearById(idValue);
            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    public List<Object[]> readClearByCustomerName(String customerName){
        try{
            if(h.isNull(customerName)) return null;
//            customerName = h.formatName(customerName);            
                
            return billRepo.findClearByCustomerName(customerName);
            
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional    
    public String delete(String billId) {        
        try{
            if(h.isNull(billId)) return null;
            if(!h.isNum(billId)) return null;
            int id = Integer.parseInt(billId);
            
            if(billRepo.existsById(id)){
                billRepo.deleteById(id);
                return SUCCESS;
            }else
                return "Error! Bill does not exist!";            
        }catch(NumberFormatException e) {            
            throw e;
        }
    }
    
    
    public boolean isNull(Bill bill) {        
        return h.isNull(bill.getCreatedDate()) || h.isNull(bill.getCustomerId()) || h.isNull(bill.getStaffId());
    }
    
    public void formatBill(Bill bill) {        
        bill.setCustomerId(bill.getCustomerId().toUpperCase());        
        bill.setStaffId(bill.getStaffId().toUpperCase());
    }
    
}
