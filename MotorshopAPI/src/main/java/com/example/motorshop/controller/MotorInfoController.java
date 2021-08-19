/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.MotorInfo;
import com.example.motorshop.service.MotorInfoService;
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
@RequestMapping("/api/motorshop/motorInfos")
public class MotorInfoController {
    
    @Autowired
    private MotorInfoService motorInfoService;
    
    @PostMapping
    public String create(@RequestBody MotorInfo motorInfo) {
        return motorInfoService.createMotorInfo(motorInfo);
    }
    
    @GetMapping
    public List<MotorInfo> getAll() {
        return motorInfoService.readAll();
    }
    
    @GetMapping("/id")
    public MotorInfo getById(@RequestParam(name = "id") String id) {
        return motorInfoService.readById(id);
    }
    
    @GetMapping("/name")
    public List<MotorInfo> getByName(@RequestParam(name = "name") String name) {
        return motorInfoService.readByName(name);
    }        
    
    @PutMapping
    public String update(@RequestBody MotorInfo motorInfo) {
        return motorInfoService.updateMotorInfo(motorInfo);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return motorInfoService.deleteMotorInfo(id);
    } 
}
