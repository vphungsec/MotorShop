/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.repository;

import com.example.motorshop.entity.BillDetail;
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
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
    
    @Query(value =  " select bd.* " +
                    " from bill_detail bd, motor m " +
                    " where bd.motor_id = m.motor_id " +
                    " and m.name like %:motorName% ",
                    nativeQuery = true)
    public List<Object[]> findByMotorName(@Param("motorName") String motorName);
    
    @Query(value =  " select bd.* " +
                    " from bill_detail bd, accessory a " +
                    " where bd.accessory_id = a.accessory_id " +
                    " and a.name like %:accessoryName% ",
                    nativeQuery = true)
    public List<Object[]> findByAccessoryName(@Param("accessoryName") String accessoryName);
    
    @Query(value =  " select bd.id, bd.bill_id, m.name as product, bd.amount, bd.price " +
                    " from bill_detail bd, motor m " +
                    " where bd.motor_id = m.motor_id and bd.motor_id is not null " +
                    " union " +
                    " select bd.id, bd.bill_id, a.name as product , bd.amount, bd.price " +
                    " from bill_detail bd, accessory a " +
                    " where bd.accessory_id = a.accessory_id and bd.accessory_id is not null " +                    
                    " order by bd.id ",
                    nativeQuery = true)
    public List<Object[]> findClearAllCombined();
    
    @Query(value =  " select bd.id, bd.bill_id, m.name as motor, a.name as accessory, bd.amount, bd.price " +
                    " from bill_detail bd " +
                    " left join motor m " +
                    " on m.motor_id = bd.motor_id " +
                    " left join accessory a " +
                    " on bd.accessory_id = a.accessory_id" +
                    " order by bd.id ",
                    nativeQuery = true)
    public List<Object[]> findClearAllUnCombined();
    
    @Query(value =  " select bd.id, bd.bill_id, m.name as motor, a.name as accessory, bd.amount, bd.price " +
                    " from bill_detail bd " +
                    " left join motor m " +
                    " on m.motor_id = bd.motor_id " +
                    " left join accessory a " +
                    " on bd.accessory_id = a.accessory_id" +
                    " order by bd.id ",
                    nativeQuery = true)
    public List<Object[]> findClearAllUnCombined(@Param("billId")Integer billId);
                    
    @Query(value =  " select bd.id, bd.bill_id, m.name as product, bd.amount, bd.price " +
                    " from bill_detail bd, motor m " +
                    " where bd.motor_id = m.motor_id and bd.motor_id is not null " +
                    " and bd.bill_id = :billId " +
                    " union " +
                    " select bd.id, bd.bill_id, a.name as product , bd.amount, bd.price " +
                    " from bill_detail bd, accessory a " +
                    " where bd.accessory_id = a.accessory_id and bd.accessory_id is not null " +
                    " and bd.bill_id = :billId " +
                    " order by bd.id ",
                    nativeQuery = true)
    public List<Object[]> findClearByBillId(@Param("billId") Integer billId);
    
    
   
    
    @Query(value =  " select * " +
                    " from ( " +
                    " select bd.id, bd.bill_id, m.name as product, bd.amount, bd.price " +
                    " from bill_detail bd, motor m " +
                    " where bd.motor_id = m.motor_id and bd.motor_id is not null " +
                    " union " +
                    " select bd.id, bd.bill_id, a.name as product , bd.amount, bd.price " +
                    " from bill_detail bd, accessory a " +
                    " where bd.accessory_id = a.accessory_id and bd.accessory_id is not null ) " +
                    " where product like %:productName% ",
                    nativeQuery = true)
    public List<Object[]> findClearByProductName(@Param("productName") String productName);
    
    // repo made by Dang
    
    @Query( value = "select bd.bill_id, b.created_date,c.name, bd.price " +
                    "from bill b, bill_detail bd, customer c " +
                    "where bd.bill_id=b.bill_id and b.customer_id=c.customer_id",
                    nativeQuery = true )
    public List<Object[]>getOrderDetail();
    
    
    @Query( value = "select a.name " +
                     "from accessory a " +
                     "union " +
                     "select m.name " +
                     "from motor m",
                    nativeQuery = true )
    public List<Object[]>getListProductName();
    
    @Query( value = "select a.accessory_id,a.name,a.amount,a.price " +
                     "from accessory a " +
                     "where a.name = :name "+
                     "union " +
                     "select m.motor_id,m.name,m.amount,m.price " +
                     "from motor m "+
                     "where m.name = :name",
                    nativeQuery = true )
    public List<Object[]>getListProductByName(@Param("name")String name);
     // get Product Id Instead BillID
    @Query(value =  " select bd.id, m.motor_id, m.name as product, bd.amount, bd.price " +
                    " from bill_detail bd, motor m " +
                    " where bd.motor_id = m.motor_id and bd.motor_id is not null " +
                    " and bd.bill_id = :billId " +
                    " union " +
                    " select bd.id, a.accessory_id, a.name as product , bd.amount, bd.price " +
                    " from bill_detail bd, accessory a " +
                    " where bd.accessory_id = a.accessory_id and bd.accessory_id is not null " +
                    " and bd.bill_id = :billId " +
                    " order by bd.id ",
                    nativeQuery = true)
    public List<Object[]> findClearByBillIdV2(@Param("billId") Integer billId);
    /////////-*--------------------------------------------------------------------------
    @Query(value="select bd.bill_id, b.created_date,c.name, bd.price " +
                "  from bill b, bill_detail bd, customer c "+
                "   where bd.bill_id=b.bill_id and b.customer_id=c.customer_id " +
                "    order by bd.price"
    ,nativeQuery=true)
    public List<Object[]> getOrderDetailMI();
    
    @Query(value="select bd.bill_id, b.created_date,c.name, bd.price " +
                "  from bill b, bill_detail bd, customer c "+
                "   where bd.bill_id=b.bill_id and b.customer_id=c.customer_id " +
                "    order by bd.price desc"
    ,nativeQuery=true)
    public List<Object[]> getOrderDetailMD();
    
    //
    
 
}
