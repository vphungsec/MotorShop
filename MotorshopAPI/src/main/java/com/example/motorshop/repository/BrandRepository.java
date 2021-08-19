/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Brand;
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
public interface BrandRepository extends JpaRepository<Brand, String> {
    
    public boolean existsByName(String name);    
    public boolean existsByPhone(String phone);
    public boolean existsByEmail(String email);
    
    @Query("select b.id from Brand b where b.id != :id and b.name = :name")
    public String existsByNameOtherBrand(@Param("id") String id, @Param("name") String name);
    
    @Query("select b.id from Brand b where b.id != :id and b.phone = :phone")
    public String existsByPhoneOtherBrand(@Param("id") String id, @Param("phone") String phone);
    
    @Query("select b.id from Brand b where b.id != :id and b.email = :email")
    public String existsByEmailOtherBrand(@Param("id") String id, @Param("email") String email);
    
    
    @Query("select max(b.id) from Brand b")
    public String findMaxId();
    
    @Query("select b from Brand b where b.name like %:name%")
    public List<Brand> findByName(String name);    
    
    public List<Brand> findByPhone(String phone);    
    
    @Query("select b from Brand b where b.email like %:email%")
    public List<Brand> findByEmail(String email);            
            
}