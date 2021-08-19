/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Staff;
import com.example.motorshop.service.StaffService;
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
@RequestMapping("/api/motorshop/staffs")
public class StaffController {
    
    @Autowired
    private StaffService staffService;
    
    @PostMapping
    public String create(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }
    
    @GetMapping("/authen")
    public String authenticate(@RequestParam(name = "usn") String usn, @RequestParam(name = "pwd") String pwd) {
        return staffService.authenticate(usn, pwd);
    }
    
    @GetMapping
    public List<Staff> getAll() {
        return staffService.readAll();
    }        
    
    @GetMapping("/id")
    public Staff getById(@RequestParam(name = "id") String id) {
        return staffService.readById(id);
    }
    
    @GetMapping("/name")
    public List<Staff> getByName(@RequestParam(name = "name") String name) {
        return staffService.readByName(name);
    }
    
    @GetMapping("/phone")
    public List<Staff> getByPhone(@RequestParam(name = "phone") String phone) {
        return staffService.readByPhone(phone);
    }
    
    @GetMapping("/clear")
    public List<Object[]> getClearAll() {
        return staffService.readClearAll();
    }          
    
    @PutMapping
    public String update(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return staffService.deleteStaff(id);
    }        
}