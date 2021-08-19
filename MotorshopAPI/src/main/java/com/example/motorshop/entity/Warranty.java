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
public class Warranty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warrantyId", nullable = false)
    private Integer id;
    
    @Column(name = "createdDate", nullable = false)
    private String createdDate;
    
    @Column(name = "billId", nullable = false)
    private Integer billId;        
    
    @Column(name = "staffId", nullable = false)
    private String staffId;

    public Warranty() {}

    public Warranty(Integer id, String createdDate, Integer billId, String staffId) {
        this.id = id;
        this.createdDate = createdDate;
        this.billId = billId;
        this.staffId = staffId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }        
    
}
