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
public class Motor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motorId", nullable = false)
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @Column(name = "price", nullable = false)
    private Integer price;
    
    @Column(name = "warrantyPeriod", nullable = false)
    private Integer warrantyPeriod; 
    
    @Column(name = "brandId", nullable = false)
    private String brandId;

    public Motor() {}

    public Motor(Integer id, String name, Integer amount, Integer price, Integer warrantyPeriod, String brandId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.warrantyPeriod = warrantyPeriod;
        this.brandId = brandId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }    

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }    
    
}
