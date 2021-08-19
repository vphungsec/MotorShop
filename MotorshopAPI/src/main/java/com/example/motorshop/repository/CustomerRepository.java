/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Customer;
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
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    public boolean existsByIdentityId(String identityId);    
    public boolean existsByName(String name);    
    public boolean existsByPhone(String phone);
    
    @Query("select c.id from Customer c where c.id != :id and c.identityId = :identityId")
    public String existsByIdentityIdOtherCustomer(@Param("id") String id, @Param("identityId") String identityId);
    
    @Query("select c.id from Customer c where c.id != :id and c.phone = :phone")
    public String existsByPhoneOtherCustomer(@Param("id") String id, @Param("phone") String phone);    
    
    @Query("select c.id from Customer c where c.phone = :usn and c.passWord = :pwd")
    public String existsByPhoneAndPassword(@Param("usn") String usn, @Param("pwd") String pwd);
    
    @Query("select max(c.id) from Customer c")
    public String findMaxId();
    
    @Query("select c from Customer c where c.identityId like %:identityId%")    
    public List<Customer> findByIdentityId(String identityId);
    
    @Query("select c from Customer c where c.name like %:name%")
    public List<Customer> findByName(String name);        
        
    @Query("select c from Customer c where c.phone like %:phone%")
    public List<Customer> findByPhone(String phone);
    
}