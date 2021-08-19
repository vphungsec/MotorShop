/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.WarrantyDetail;
import com.example.motorshop.service.WarrantyDetailService;
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
@RequestMapping("/api/motorshop/warrantyDetails")
public class WarrantyDetailController {
    
    @Autowired
    private WarrantyDetailService warrantyDetailService;  
    
    @PostMapping
    public String create(@RequestBody WarrantyDetail warrantyDetail) {
        return warrantyDetailService.create(warrantyDetail);
    }
    
    @GetMapping
    public List<WarrantyDetail> getAll() {
        return warrantyDetailService.readAll();
    }
    
    @GetMapping("/id")
    public WarrantyDetail getById(@RequestParam(name = "id") String id) {
        return warrantyDetailService.readById(id);
    }
    
    @GetMapping("/motorName")
    public List<Object[]> getByMotorName(@RequestParam(name = "motorName") String motorName) {
        return warrantyDetailService.readByMotorName(motorName);
    }
    
    @GetMapping("/clear/combined")
    public List<Object[]> getClearAllCombined() {
        return warrantyDetailService.readClearAllCombined();
    }
    
    @GetMapping("/clear/unCombined")
    public List<Object[]> getClearAllUnCombined() {
        return warrantyDetailService.readClearAllUnCombined();
    }
    
    @GetMapping("/clear/warrantyId")
    public List<Object[]> getClearByWarrantyId(@RequestParam(name = "warrantyId") String warrantyId) {
        return warrantyDetailService.readClearByWarrantyId(warrantyId);
    }
    
    @GetMapping("/clear/productName")
    public List<Object[]> getClearByProductName(@RequestParam(name = "productName") String productName) {
        return warrantyDetailService.readClearByProductName(productName);
    }
        
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return warrantyDetailService.delete(id);
    }
    
    //MoreHanh
    @GetMapping("/getFrameNumber/nameFrame")
    public List<Object[]>getFrameName(@RequestParam("nameFrame") String nameFrame) {
            return warrantyDetailService.getFrameName(nameFrame);
    }

    @GetMapping("/getFrame")
    public List<Object[]>getFrame() {
            return warrantyDetailService.getFrameNumber();
    }

    @GetMapping("/getDetailCustomter")
    public List<Object[]>getDetailCustomter(@RequestParam("idmotorInfo") String idmotorInfo, @RequestParam("frameNumber") String frameNumber) {
            return warrantyDetailService.getDetailCustomter(idmotorInfo,frameNumber);
    }

    @GetMapping("/getFrameNumber/idmotorInfo")
    public List<Object[]>getFrameNumber(@RequestParam("idmotorInfo") String idmotorInfo, @RequestParam("phone") String phone) {
            return warrantyDetailService.getFrameNumber(idmotorInfo,phone);
    }
}
