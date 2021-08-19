/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Motor;
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
public interface MotorRepository extends JpaRepository<Motor, Integer> {
    
    public boolean existsByName(String name); 
    
    @Query("select m from Motor m where m.name like %:name%")
    public List<Motor> findByName(String name);
    
    @Query("select m.id from Motor m where m.id != :id and m.name = :name")
    public String existsByNameOtherMotor(@Param("id") Integer id, @Param("name") String name);
    
//    @Query("")
//    public List<Motor> findClearAll();   
}