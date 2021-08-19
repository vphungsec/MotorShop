/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Image;
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
public interface ImageRepository extends JpaRepository<Image, Integer> {
        
    @Query("select i.image from Image i where i.motorId = :motorId")
    public List<byte[]> findByMotorId(@Param("motorId") Integer motorId); 
    
    @Query("select i.image from Image i where i.accessoryId = :accessoryId")
    public List<byte[]> findByAccessoryId(@Param("accessoryId") Integer accessoryId);
}
