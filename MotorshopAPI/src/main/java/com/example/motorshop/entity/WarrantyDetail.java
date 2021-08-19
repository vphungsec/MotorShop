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
public class WarrantyDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "warrantyId", nullable = false)
    private Integer warrantyId;
    
    @Column(name = "motorId", nullable = true)
    private Integer motorId;
    
    @Column(name = "accessoryId", nullable = true)
    private Integer accessoryId;        
    
    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name = "price", nullable = false)
    private Integer price;

    public WarrantyDetail() {}

    public WarrantyDetail(Integer id, Integer warrantyId, Integer motorId, Integer accessoryId, String content, Integer price) {
        this.id = id;
        this.warrantyId = warrantyId;
        this.motorId = motorId;
        this.accessoryId = accessoryId;
        this.content = content;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(Integer warrantyId) {
        this.warrantyId = warrantyId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
        
}
