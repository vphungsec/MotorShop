/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.MotorDetail;
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
public interface MotorDetailRepository extends JpaRepository<MotorDetail, Integer> {       
    
    @Query("select md.id from MotorDetail md where md.motorId = :motorId and md.motorInfoId = :motorInfoId")
    public String findByMotorIdAndMotorInfoId(@Param("motorId") Integer motorId, @Param("motorInfoId") Integer motorInfoId);   
    
    @Query("select md.id from MotorDetail md where md.id != :id and (md.motorId = :motorId and md.motorInfoId = :motorInfoId)")
    public String findByMotorIdAndMotorInfoIdOtherMotorDetail(@Param("id") Integer id, @Param("motorId") Integer motorId, @Param("motorInfoId") Integer motorInfoId);   
    
    @Query(value =  " select md from MotorDetail where md.content like %:content%",
                    nativeQuery = true)
    public List<MotorDetail> findByContent(@Param("content") String content);      
    
    @Query(value =  " select md.id as id, m.name as motor, mi.name as motor_info, md.content as content " +
                    " from motor_detail md, motor m, motor_info mi " +
                    " where m.motor_id = md.motor_id and md.motor_info_id = mi.motor_info_id ",
                    nativeQuery = true)
    public List<Object[]> findClearAll();
    
    @Query(value =  " select md.id as id, m.name as motor, mi.name as motor_info, md.content as content " +
                    " from motor_detail md, motor m, motor_info mi " +
                    " where m.motor_id = md.motor_id and md.motor_info_id = mi.motor_info_id " +
                    " and m.motor_id = :id ",
                    nativeQuery = true)
    public List<Object[]> findClearById(@Param("id") Integer id);
    
    @Query(value =  " select md.id as id, m.name as motor, mi.name as motor_info, md.content as content " +
                    " from motor_detail md, motor m, motor_info mi " +
                    " where m.motor_id = md.motor_id and md.motor_info_id = mi.motor_info_id " +
                    " and m.motor_id = :id ",
                    nativeQuery = true)
    public List<Object[]> findClearByMotorId(@Param("id") Integer id);
    
    @Query(value =  " select md.id as id, m.name as motor, mi.name as motor_info, md.content as content " +
                    " from motor_detail md, motor m, motor_info mi " +
                    " where m.motor_id = md.motor_id and md.motor_info_id = mi.motor_info_id " +
                    " and md.motor_id = :motorId and md.content like %:content% ",
                    nativeQuery = true)
    public List<Object[]> findClearByMotorIdAndContent(@Param("motorId") Integer motorId, @Param("content") String content);        
    
    @Query(value =  " select md.id as id, m.name as motor, mi.name as motor_info, md.content as content " +
                    " from motor_detail md, motor m, motor_info mi " +
                    " where m.motor_id = md.motor_id and md.motor_info_id = mi.motor_info_id " +
                    " and m.name like %:motorName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByMotorName(@Param("motorName") String motorName);
    
    @Transactional
    @Modifying
    @Query("delete from MotorDetail md where md.motorId = :motorId and md.motorInfoId = :motorInfoId")
    public void deleteByMotorIdAndMotorInfoId(@Param("motorId") Integer motorId, @Param("motorInfoId") Integer motorInfoId);
    
    //hạnh
    @Query(value = "select md.content from motor_detail md "+
                    "where md.motor_info_id = :idmotorInfo "+
                    "and md.content = (select md2.content "+
                                    "from bill b, customer c,bill_detail bd, motor_detail md2 "+
                                    "where b.customer_id = c.customer_id "+
                                    "and b.bill_id = bd.bill_id "+
                                    "and md2.motor_id = bd.motor_id "+
                                    "and c.phone = :phone) ",
                    nativeQuery = true)
    public List<Object[]> getFrameNumber(@Param("idmotorInfo") Integer idmotorInfo, @Param("phone") String phone);
}