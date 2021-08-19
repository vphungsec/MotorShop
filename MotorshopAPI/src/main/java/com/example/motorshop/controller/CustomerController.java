/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Customer;
import com.example.motorshop.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hungh
 */
@RestController
@RequestMapping("/api/motorshop/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public String create(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    
    @GetMapping("/authen")
    public String authenticate(@RequestParam(name = "usn") String usn, @RequestParam(name = "pwd") String pwd) {
        return customerService.authenticate(usn, pwd);
    }
    
    @GetMapping
    public List<Customer> getAll() {
        return customerService.readAll();
    }
    
    @GetMapping("/id")
    public Customer getById(@RequestParam(name = "id") String id) {
        return customerService.readById(id);
    }
    
    @GetMapping("/identityId")
    public List<Customer> getByIdentityId(@RequestParam(name = "identityId") String identityId) {
        return customerService.readByIdentityId(identityId);
    }
    
    @GetMapping("/name")
    public List<Customer> getByName(@RequestParam(name = "name") String name) {
        return customerService.readByName(name);
    }
    
    @GetMapping("/phone")
    public List<Customer> getByPhone(@RequestParam(name = "phone") String phone) {
        return customerService.readByPhone(phone);
    }   
    
    @PutMapping
    public String update(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return customerService.deleteCustomer(id);
    }  
}
