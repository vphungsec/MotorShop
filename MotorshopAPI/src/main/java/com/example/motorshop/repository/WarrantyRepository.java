/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Warranty;
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
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {
    
    @Query("select w from Warranty w where w.staffId like %:staffId%")
    public List<Warranty> findByStaffId(@Param("staffId") String staffId);
    
    @Query("select w from Warranty w where w.billId like %:billId%")
    public List<Warranty> findByBillId(@Param("billId") String billId);     
    
    @Query(value =  " select w.warranty_id, w.bill_id, w.created_date, s.name as staff " +
                    " from warranty w, staff s " +
                    " where w.staff_id = s.staff_id ",
                    nativeQuery = true)
    public List<Object[]> findClearAll();
    
    @Query(value =  " select w.warranty_id, w.bill_id, w.created_date, s.name as staff " +
                    " from warranty w, staff s " +
                    " where w.staff_id = s.staff_id " +
                    " and w.warranty_id = :id ",
                    nativeQuery = true)
    public Warranty findClearById(@Param("id") Integer id);
    
    @Query(value =  " select w.warranty_id, w.bill_id, w.created_date, s.name as staff " +
                    " from warranty w, staff s " +
                    " where w.staff_id = s.staff_id " +
                    " and s.name like %:staffName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByStaffName(@Param("staffName") String staffName); 
    
    
}
