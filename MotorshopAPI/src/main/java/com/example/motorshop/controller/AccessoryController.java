/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Accessory;
import com.example.motorshop.service.AccessoryService;
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
@RequestMapping("/api/motorshop/accessories")
public class AccessoryController {
    
    @Autowired
    private AccessoryService accessoryService;
    
    @PostMapping
    public String create(@RequestBody Accessory accessory) {
        return accessoryService.createAccessory(accessory);
    }
    
    @GetMapping
    public List<Accessory> getAll() {
        return accessoryService.readAll();
    }
    
    @GetMapping("/id")
    public Accessory getById(@RequestParam(name = "id") String id) {
        return accessoryService.readById(id);
    }
    
    @GetMapping("/name")
    public List<Accessory> getByName(@RequestParam(name = "name") String name) {
        return accessoryService.readByName(name);
    }        
    
    @PutMapping
    public String update(@RequestBody Accessory accessory) {
        return accessoryService.updateAccessory(accessory);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return accessoryService.deleteAccessory(id);
    }  
}
