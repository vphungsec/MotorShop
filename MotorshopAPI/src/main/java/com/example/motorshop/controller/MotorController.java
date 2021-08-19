/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Motor;
import com.example.motorshop.service.MotorService;
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
@RequestMapping("/api/motorshop/motors")
public class MotorController {
    
    @Autowired
    private MotorService motorService;
    
    @PostMapping
    public String create(@RequestBody Motor motor) {
        return motorService.createMotor(motor);
    }
    
    @GetMapping
    public List<Motor> getAll() {
        return motorService.readAll();
    }
    
    @GetMapping("/id")
    public Motor getById(@RequestParam(name = "id") String id) {
        return motorService.readById(id);
    }
    
    @GetMapping("/name")
    public List<Motor> getByName(@RequestParam(name = "name") String name) {
        return motorService.readByName(name);
    }        
    
    @PutMapping
    public String update(@RequestBody Motor motor) {
        return motorService.updateMotor(motor);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return motorService.deleteMotor(id);
    }  
}
