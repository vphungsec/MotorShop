/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Warranty;
import com.example.motorshop.service.WarrantyService;
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
@RequestMapping("/api/motorshop/warranties")
public class WarrantyController {
    
    @Autowired
    private WarrantyService warrantyService;
    
    @PostMapping
    public String create(@RequestBody Warranty warranty) {
        return warrantyService.create(warranty);
    }
    
    @GetMapping
    public List<Warranty> getAll() {
        return warrantyService.readAll();
    }
    
    @GetMapping("/id")
    public Warranty getById(@RequestParam(name = "id") String id) {
        return warrantyService.readById(id);
    }
    
    @GetMapping("/clear")
    public List<Object[]> getClearAll() {
        return warrantyService.readClearAll();
    }
    
    @GetMapping("/clear/id")
    public Warranty getClearById(@RequestParam(name = "id") String id) {
        return warrantyService.readClearById(id);
    }
    
    @GetMapping("/clear/staffName")
    public List<Object[]> geCleartByStaffName(@RequestParam(name = "staffName") String staffName) {
        return warrantyService.readClearByStaffName(staffName);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return warrantyService.delete(id);
    }
}
