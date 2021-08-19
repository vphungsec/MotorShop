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
public class MotorDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "motorId", nullable = false)
    private Integer motorId;
    
    @Column(name = "motorInfoId", nullable = false)
    private Integer motorInfoId;
    
    @Column(name = "content", nullable = false)
    private String content;
    

    public MotorDetail() {}

    public MotorDetail(Integer id, Integer motorId, Integer motorInfoId, String content) {
        this.id = id;
        this.motorId = motorId;
        this.motorInfoId = motorInfoId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMotorId() {
        return motorId;
    }

    public void setMotorId(Integer motorId) {
        this.motorId = motorId;
    }

    public Integer getMotorInfoId() {
        return motorInfoId;
    }

    public void setMotorInfoId(Integer motorInfoId) {
        this.motorInfoId = motorInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
