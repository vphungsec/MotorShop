/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.MotorDetail;
import com.example.motorshop.service.MotorDetailService;
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
@RequestMapping("/api/motorshop/motorDetails")
public class MotorDetailController {
    
    @Autowired
    private MotorDetailService motorDetailService;
    
    @PostMapping
    public String create(@RequestBody MotorDetail motorDetail) {
        return motorDetailService.createMotorDetail(motorDetail);
    }
    
    @GetMapping
    public List<MotorDetail> getAll() {
        return motorDetailService.readAll();
    }
    
    @GetMapping("/id")
    public MotorDetail getById(@RequestParam(name = "id") String id) {
        return motorDetailService.readById(id);
    }
    
    @GetMapping("/clear")
    public List<Object[]> getClearAll() {
        return motorDetailService.readClearAll();
    }
    
    @GetMapping("/clear/idAndContent")
    public List<Object[]> getClearByMotorIdAndContent(@RequestParam(name = "id") String id, @RequestParam(name = "content") String content) {
        return motorDetailService.readClearByMotorIdAndContent(id, content);
    }
    
    @GetMapping("/clear/motorName")
    public List<Object[]> getClearByMotorName(@RequestParam(name = "motorName") String motorName) {
        return motorDetailService.readClearByMotorName(motorName);
    }
    
    @PutMapping
    public String update(@RequestBody MotorDetail motorDetail) {
        return motorDetailService.updateMotorDetail(motorDetail);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "motorId") String motorId, @RequestParam(name = "motorInfoId") String motorInfoId) {
        return motorDetailService.deleteMotorDetail(motorId, motorInfoId);
    }
    
    @DeleteMapping("/id")
    public String deleteById(@RequestParam(name = "id") String id) {
        return motorDetailService.deleteById(id);
    }
    
    //hạnh
    @GetMapping("/getFrameNumber/idmotorInfo")
    public List<Object[]>getFrameNumber(@RequestParam("idmotorInfo") String idmotorInfo, @RequestParam("phone") String phone) {
            return motorDetailService.getFrameNumber(idmotorInfo,phone);
    }
}
