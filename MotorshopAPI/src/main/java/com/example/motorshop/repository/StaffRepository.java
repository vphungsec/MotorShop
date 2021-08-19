/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Staff;
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
public interface StaffRepository extends JpaRepository<Staff, String> {
        
    public boolean existsByName(String name);    
    public boolean existsByPhone(String phone);        
    
    @Query("select s.id from Staff s where s.id != :id and s.phone = :phone")
    public String existsByPhoneOtherStaff(@Param("id") String id, @Param("phone") String phone);   
    
    @Query("select s.id from Staff s where s.phone = :usn and s.passWord = :pwd")
    public String existsByPhoneAndPassword(@Param("usn") String usn, @Param("pwd") String pwd);
    
    @Query("select max(s.id) from Staff s")
    public String findMaxId();
    
    @Query("select s from Staff s where s.name like %:name%")
    public List<Staff> findByName(@Param("name") String name);  
    
    @Query("select s from Staff s where s.phone like %:phone%")
    public List<Staff> findByPhone(@Param("phone") String phone);                 
    
    @Query(value =  " select s.staff_id, s.name, s.phone, s.created_date, d.name as depart " +
                    " from staff s, depart d " +
                    " where s.depart_id = d.depart_id ",
                    nativeQuery = true)
    public List<Object[]> findClearAll();        
    
}