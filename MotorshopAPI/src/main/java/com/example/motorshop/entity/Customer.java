/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author hungh
 */
@Entity
public class Customer {
    
    @Id
    @Column(name = "customerId", nullable = false)
    private String id;  //editable true
    
    @Column(name = "identityId", nullable = false)
    private String identityId;  //editable true
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "password", nullable = false)
    private String passWord;
    
    @Column(name = "createdDate", nullable = false)
    private String createdDate;

    public Customer() {}

    public Customer(String id, String identityId, String name, String address, String phone, String passWord, String createdDate) {
        this.id = id;
        this.identityId = identityId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.passWord = passWord;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }    
}
