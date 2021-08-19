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
public class Staff {
    
    @Id
    @Column(name = "staffId", nullable = false)
    private String id;  //editable false        
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "password", nullable = false)
    private String passWord;
    
    @Column(name = "createdDate", nullable = false)
    private String createdDate;
    
    @Column(name = "departId", nullable = false)
    private String departId;    //editable true

    public Staff() {}

    public Staff(String id, String name, String phone, String passWord, String createdDate, String departId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.passWord = passWord;
        this.createdDate = createdDate;
        this.departId = departId;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }    
}
