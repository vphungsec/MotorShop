/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.Bill;
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
public interface BillRepository extends JpaRepository<Bill, Integer> {
    
    @Query("select b from Bill b where b.staffId like %:staffId%")
    public List<Bill> findByStaffId(@Param("staffId") String staffId);
    
    @Query("select b from Bill b where b.customerId like %:customerId%")
    public List<Bill> findByCustomerId(@Param("customerId") String customerId);                
    
    @Query(value =  " select b.bill_id, b.created_date, c.name as customer_name, s.name as staff_name " +
                    " from bill b, staff s, customer c " +
                    " where b.staff_id = s.staff_id and b.customer_id = c.customer_id ",
                    nativeQuery = true)
    public List<Object[]> findClearAll();
    
    @Query(value =  " select b.bill_id, b.created_date, c.name as customer_name, s.name as staff_name " +
                    " from bill b, staff s, customer c " +
                    " where b.staff_id = s.staff_id and b.customer_id = c.customer_id " +
                    " and b.id = :id ",
                    nativeQuery = true)
    public Bill findClearById(@Param("id") Integer id);
    
    @Query(value =  " select b.bill_id, b.created_date, c.name as customer_name, s.name as staff_name " +
                    " from bill b, staff s, customer c " +
                    " where b.staff_id = s.staff_id and b.customer_id = c.customer_id " +
                    " and c.name like %:customerName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByCustomerName(@Param("customerName") String customerName); 
    
    @Query(value =  " select b.bill_id, b.created_date, c.name as customer_name, s.name as staff_name " +
                    " from bill b, staff s, customer c " +
                    " where b.staff_id = s.staff_id and b.customer_id = c.customer_id " +
                    " and c.name like %:staffName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByStaffName(@Param("staffName") String staffName); 
    
    @Query(value =  " select b.bill_id, b.created_date, c.name as customer_name, s.name as staff_name " +
                    " from bill b, staff s, customer c " +
                    " where b.staff_id = s.staff_id and b.customer_id = c.customer_id " +
                    " and s.staff_id = :staffId and c.name like %:customerName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByStaffIdAndCustomerName(@Param("staffId") String staffId, @Param("customerName") String customerName);            
}
