/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author hungh
 */
@Entity
public class BillDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "billId", nullable = false)
    private Integer billId;
    
    @Column(name = "motorId", nullable = true)
    private Integer motorId;
    
    @Column(name = "accessoryId", nullable = true)
    private Integer accessoryId;        
    
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @Column(name = "price", nullable = false)
    private Integer price;

    public BillDetail() {}

    public BillDetail(Integer id, Integer billId, Integer motorId, Integer accessoryId, Integer amount, Integer price) {
        this.id = id;
        this.billId = billId;
        this.motorId = motorId;
        this.accessoryId = accessoryId;
        this.amount = amount;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getMotorId() {
        return motorId;
    }

    public void setMotorId(Integer motorId) {
        this.motorId = motorId;
    }

    public Integer getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
           
}
