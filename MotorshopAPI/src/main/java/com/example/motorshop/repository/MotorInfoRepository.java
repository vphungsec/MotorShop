/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.MotorInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hungh
 */
@Repository
public interface MotorInfoRepository extends JpaRepository<MotorInfo, Integer> {
    
    public boolean existsByName(String name);  
           
    @Query("select mi from MotorInfo mi where mi.name like %:name%")
    public List<MotorInfo> findByName(String name);
    
    @Query("select mi.id from MotorInfo mi where mi.id != :id and mi.name = :name")
    public String existsByNameOtherMotorInfo(@Param("id") Integer id, @Param("name") String name);
    
    
}
