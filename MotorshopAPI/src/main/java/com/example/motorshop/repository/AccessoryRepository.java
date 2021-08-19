/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Accessory;
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
public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {
    public boolean existsByName(String name); 
    
    @Query("select a from Accessory a where a.name like %:name%")
    public List<Accessory> findByName(String name);
    
    @Query("select a.id from Accessory a where a.id != :id and a.name = :name")
    public String existsByNameOtherAccessory(@Param("id") Integer id, @Param("name") String name);
}
