/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.AccessoryDetail;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hungh
 */
@Repository
public interface AccessoryDetailRepository extends JpaRepository<AccessoryDetail, Integer> {
    
    @Query("select ad.id from AccessoryDetail ad where ad.accessoryId = :accessoryId and ad.motorId = :motorId")
    public String findByAccessoryIdAndMotorId(@Param("accessoryId") Integer accessoryId, @Param("motorId") Integer motorId);   
    
    @Query("select ad.id from AccessoryDetail ad where ad.id != :id and (ad.accessoryId = :accessoryId and ad.motorId = :motorId)")
    public String findByAccessoryIdAndMotorIdOtherAccessoryDetail(@Param("id") Integer id, @Param("accessoryId") Integer accessoryId, @Param("motorId") Integer motorId);              
    
    @Query(value =  " select ad.id, a.name, m.name as motor, ad.price " +
                    " from accessory_detail ad, accessory a, motor m " +
                    " where a.accessory_id = ad.accessory_id " +
                    " and ad.motor_id = m.motor_id ",
                    nativeQuery = true)
    public List<Object[]> findClearAll();
    
    @Query(value =  " select ad.id, a.name, m.name as motor, ad.price " +
                    " from accessory_detail ad, accessory a, motor m " +
                    " where a.accessory_id = ad.accessory_id and ad.motor_id = m.motor_id " +
                    " and ad.accessory_id = :accessoryId",
                    nativeQuery = true)
    public List<Object[]> findClearByAccessoryId(@Param("accessoryId") Integer accessoryId);
    
    @Query(value =  " select ad.id, a.name, m.name as motor, ad.price " +
                    " from accessory_detail ad, accessory a, motor m " +
                    " where a.accessory_id = ad.accessory_id and ad.motor_id = m.motor_id " +
                    " and ad.accessory_id = :accessoryId and ad.motor_id = :motorId",
                    nativeQuery = true)
    public List<Object[]> findClearByAccessoryIdAndMotorId(@Param("accessoryId") Integer accessoryId, @Param("motorId") Integer motorId);        
    
    @Query(value =  " select ad.id, a.name, m.name as motor, ad.price " +
                    " from accessory_detail ad, accessory a, motor m " +
                    " where a.accessory_id = ad.accessory_id and ad.motor_id = m.motor_id " +
                    " and a.name like %:accessoryName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByAccessoryName(@Param("accessoryName") String accessoryName);
    
    @Query(value =  " select ad.id, a.name, m.name as motor, ad.price " +
                    " from accessory_detail ad, accessory a, motor m " +
                    " where a.accessory_id = ad.accessory_id and ad.motor_id = m.motor_id " +
                    " and m.name like %:motorName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByMotorName(@Param("motorName") String motorName);
    
    @Transactional
    @Modifying
    @Query("delete from AccessoryDetail ad where ad.accessoryId = :accessoryId and ad.motorId = :motorId")
    public void deleteByaccessoryIdAndMotorId(@Param("accessoryId") Integer accessoryId, @Param("motorId") Integer motorId);
    
}
