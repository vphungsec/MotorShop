/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Brand;
import com.example.motorshop.service.BrandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hungh
 */
@RestController
@RequestMapping("/api/motorshop/brands")
public class BrandController {
    
    @Autowired
    private BrandService brandService;
    
    @PostMapping
    public String create(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }
    
    @GetMapping
    public List<Brand> getAll() {
        return brandService.readAll();
    }
    
    @GetMapping("/id")
    public Brand getById(@RequestParam(name = "id") String id) {
        return brandService.readById(id);
    }        
    
    @GetMapping("/name")
    public List<Brand> getByName(@RequestParam(name = "name") String name) {
        return brandService.readByName(name);
    }
    
    @GetMapping("/phone")
    public List<Brand> getByPhone(@RequestParam(name = "phone") String phone) {
        return brandService.readByPhone(phone);
    }
    
    @PutMapping
    public String update(@RequestBody Brand brand) {
        return brandService.updateBrand(brand);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return brandService.deleteBrand(id);
    }
}
