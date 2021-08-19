/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.service;

import com.example.motorshop.entity.Customer;
import com.example.motorshop.helper.Helper;
import com.example.motorshop.repository.CustomerRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hungh
 */
@Service
public class CustomerService {        
    
    private static final String ID_MODEL = "CM";
    private static final String HASH_MODEL = "MD5";
    
    private static final String SUCCESS = "OK";
    
    private final Helper h = new Helper();
    
    @Autowired
    private CustomerRepository customerRepo;
    
    @Transactional
    public String createCustomer(Customer customer) {       
        try {
            if(isNull(customer))
                return "Error! NULL data!";
            if(!h.isNum(customer.getIdentityId()))
                return "Error! IdentityId only contains numbers!";
            if(customer.getIdentityId().length() != 9)
                return "Error! IdentityId's length is 9!";
            if(!h.isAlpha(customer.getName()))
                return "Error! Name only contains alphas & spaces!";            
            if(!h.isNum(customer.getPhone()))
                return "Error! Phone only contains numbers!";
            if(customer.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(customer.getPassWord().length() < 4)
                return "Error! Password's min length is 4!";
            formatStaff(customer);
            if(customerRepo.existsByIdentityId(customer.getIdentityId()))
                return "Error! IdentityId existed!";
            if(customerRepo.existsByPhone(customer.getPhone()))
                return "Error! Phone existed!";            
            else{         
                String maxId = customerRepo.findMaxId();
                customer.setId(maxId == null ? (ID_MODEL + "01") : h.findNextId(maxId));
                customer.setPassWord(h.getCryptoHash(customer.getPassWord(), HASH_MODEL));
                customerRepo.save(customer);
                return SUCCESS;
            }
        }catch(Exception e) {
            throw e;
        }
    }
    
    public List<Customer> readAll() {
        return customerRepo.findAll();
    }
    
    public Customer readById(String id) {
        try{
            if(h.isNull(id)) return null;
            id = id.toUpperCase();            
            
            if(customerRepo.existsById(id))
                return customerRepo.findById(id).get();
            else
                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Customer> readByIdentityId(String identityId) {
        try{
            if(h.isNull(identityId)) return null;  
            
//            if(ctmRepository.existsByIdentityId(identityId))
                return customerRepo.findByIdentityId(identityId);
//            else
//                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Customer> readByName(String name) {        
        try{
            if(h.isNull(name)) return null;
            name = h.formatName(name);         
            
//            if(customerRepo.existsByName(name))
                return customerRepo.findByName(name);
//            else
//                return  null;
        }catch(Exception e) {            
            throw e;
        }
    }
    
    public List<Customer> readByPhone(String phone) {        
        try{
            if(h.isNull(phone)) return null; 
            
//            if(customerRepo.existsByPhone(phone))
                return customerRepo.findByPhone(phone);
//            else
//                return null;                         
        }catch(Exception e) {            
            throw e;
        }
    }    
    
    
    
    @Transactional
    public String updateCustomer(Customer customer) {
        try{
            if(isNull(customer))
                return "Error! NULL data!";
            if(h.isNull(customer.getId()))
                return "Error! Null ID!";
            if(!h.isNum(customer.getIdentityId()))
                return "Error! IdentityId only contains numbers!";
            if(customer.getIdentityId().length() != 9)
                return "Error! IdentityId's length is 9!";
            if(!h.isAlpha(customer.getName()))
                return "Error! Name only contains alphas & spaces!";            
            if(!h.isNum(customer.getPhone()))
                return "Error! Phone only contains numbers!";
            if(customer.getPhone().length() > 15)
                return "Error! Phone's max length is 15!";
            if(customer.getPassWord().length() < 4)
                return "Error! Password's min length is 4!";
            formatStaff(customer);
            if(!customerRepo.existsById(customer.getId()))
                return "Error! ID does not exist!";
            if(customerRepo.existsByIdentityIdOtherCustomer(customer.getId(), customer.getIdentityId()) != null)
                return "Error! IdentityId existed in other Customer!"; 
            if(customerRepo.existsByPhoneOtherCustomer(customer.getId(), customer.getPhone()) != null)
                return "Error! Phone existed in other Customer!";            
            else{
                customer.setPassWord(h.getCryptoHash(customer.getPassWord(), HASH_MODEL));
                customerRepo.save(customer);
                return SUCCESS;
            }                        
        }catch(Exception e) {            
            throw e;
        }
    }
    
    @Transactional
    public String deleteCustomer(String customerId) {        
        try{
            if(h.isNull(customerId)) return null;
            customerId = customerId.toUpperCase();
            
            if(customerRepo.existsById(customerId)) {
                customerRepo.deleteById(customerId);
                return SUCCESS;
            }else
                return "Error! Customer does not exist!";            
        }catch(Exception e) {            
            throw e;
        }
    }        
    
    public String authenticate(String usn, String pwd) {
        if(h.isNull(usn) || h.isNull(pwd) || !h.isNum(usn) || usn.length() > 15 || pwd.length() < 4) return null;
        String customerId = customerRepo.existsByPhoneAndPassword(usn, pwd);
        if(customerId != null ) return customerId;
        else return null;
    }
    
    public boolean isNull(Customer customer) { 
        return h.isNull(customer.getIdentityId()) || h.isNull(customer.getName())
                || h.isNull(customer.getAddress()) || h.isNull(customer.getPhone())
                    || h.isNull(customer.getPassWord()) || h.isNull(customer.getCreatedDate());
    }
    
    public void formatStaff(Customer customer) {
        if(h.isNull(customer.getId()))
            customer.setId(customer.getId().toUpperCase());
        customer.setName(h.formatName(customer.getName()));
    }
}
