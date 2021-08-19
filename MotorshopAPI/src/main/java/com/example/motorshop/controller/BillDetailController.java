/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.BillDetail;
import com.example.motorshop.service.BillDetailService;
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
@RequestMapping("/api/motorshop/billDetails")
public class BillDetailController {
    
    @Autowired
    private BillDetailService billDetailService;
    
    @PostMapping
    public String create(@RequestBody BillDetail billDetail) {
        return billDetailService.create(billDetail);
    }
    
    @GetMapping
    public List<BillDetail> getAll() {
        return billDetailService.readAll();
    }
    
    @GetMapping("/id")
    public BillDetail getById(@RequestParam(name = "id") String id) {
        return billDetailService.readById(id);
    }
    
    @GetMapping("/motorName")
    public List<Object[]> getByMotorName(@RequestParam(name = "motorName") String motorName) {
        return billDetailService.readByMotorName(motorName);
    }
    
    @GetMapping("/clear/combined")
    public List<Object[]> getClearAllCombined() {
        return billDetailService.readClearAllCombined();
    }
    
    @GetMapping("/clear/unCombined")
    public List<Object[]> getClearAllUnCombined() {
        return billDetailService.readClearAllUnCombined();
    }
    
    @GetMapping("/clear/billId")
    public List<Object[]> getClearByBillId(@RequestParam(name = "billId") String billId) {
        return billDetailService.readClearByBillId(billId);
    }
    
    @GetMapping("/clear/productName")
    public List<Object[]> getClearByProductName(@RequestParam(name = "productName") String productName) {
        return billDetailService.readClearByProductName(productName);
    }
        
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return billDetailService.delete(id);
    }
    
    //controller made by Dang
    @GetMapping("/getOrderDetail")
    public List<Object[]> getOrderDetail( ) {
        return billDetailService.getOrderDetail();
    }
   @GetMapping("/getDetailOfAnOrder")
   public List<Object[]>getOrderDetail(@RequestParam(name="billId")String billId){
       return billDetailService.readClearByBillIdV2(billId);
   }
   @GetMapping("/listPrname")
   public List<Object[]>getListProductName(){
       return billDetailService.getListProductName();
   }
   @GetMapping("/listPrByName")
   public List<Object[]>getListProductByName(@RequestParam(name="name")String name){
       return billDetailService.getListProductByName(name);
   }
      /////////-*-------------------------------------------------------------------------- 
   @GetMapping("/moneyin")
   public List<Object[]> getOrderDetailMI( ) {
        return billDetailService.getOrderDetailMI();
    }
   @GetMapping("/moneyde")
   public List<Object[]> getOrderDetailMD( ) {
        return billDetailService.getOrderDetailMD();
    }
}
