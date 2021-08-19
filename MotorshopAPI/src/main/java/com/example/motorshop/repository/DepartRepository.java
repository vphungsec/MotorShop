/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Depart;
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
public interface DepartRepository extends JpaRepository<Depart, String> {
     
    public boolean existsByName(String name);  
           
    @Query("select d from Depart d where d.name like %:name%")
    public List<Depart> findByName(@Param("name") String name);
    
    @Query("select d.id from Depart d where d.id != :id and d.name = :name")
    public String existsByNameOtherDepart(@Param("id") String id, @Param("name") String name);
}