/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.AccessoryDetail;
import com.example.motorshop.service.AccessoryDetailService;
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
@RequestMapping("/api/motorshop/accessoryDetails")
public class AccessoryDetailController {
    
    @Autowired
    private AccessoryDetailService accessoryDetailService;
    
    @PostMapping
    public String create(@RequestBody AccessoryDetail accessoryDetail) {
        return accessoryDetailService.createAccessoryDetail(accessoryDetail);
    }
    
    @GetMapping
    public List<AccessoryDetail> getAll() {
        return accessoryDetailService.readAll();
    }
    
    @GetMapping("/clear")
    public List<Object[]> getClearAll() {
        return accessoryDetailService.readClearAll();
    }
    
    @PutMapping
    public String update(@RequestBody AccessoryDetail accessoryDetail) {
        return accessoryDetailService.updateAccessoryDetail(accessoryDetail);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "accessoryId") String accessoryId, @RequestParam(name = "motorId") String motorId) {
        return accessoryDetailService.deleteAccessoryDetail(accessoryId, motorId);
    }
    
    @DeleteMapping("/id")
    public String deleteById(@RequestParam(name = "id") String id) {
        return accessoryDetailService.deleteById(id);
    }
    
}
