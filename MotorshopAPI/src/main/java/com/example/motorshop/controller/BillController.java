/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Bill;
import com.example.motorshop.service.BillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hungh
 */
@RestController
@RequestMapping("/api/motorshop/bills")
public class BillController {
    
    @Autowired
    private BillService billService;
    
    @PostMapping
    public String create(@RequestBody Bill bill) {
        return billService.create(bill);
    }
    
    @GetMapping
    public List<Bill> getAll() {
        return billService.readAll();
    }
    
    @GetMapping("/id")
    public Bill getById(@RequestParam(name = "id") String id) {
        return billService.readById(id);
    }
    
    @GetMapping("/clear")
    public List<Object[]> getClearAll() {
        return billService.readClearAll();
    }
    
    @GetMapping("/clear/id")
    public Bill getClearById(@RequestParam(name = "id") String id) {
        return billService.readClearById(id);
    }
    
    @GetMapping("/clear/customerName")
    public List<Object[]> geCleartByCustomerName(@RequestParam(name = "customerName") String customerName) {
        return billService.readClearByCustomerName(customerName);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return billService.delete(id);
    }
}
