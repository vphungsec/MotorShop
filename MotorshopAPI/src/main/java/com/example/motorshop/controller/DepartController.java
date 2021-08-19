/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Depart;
import com.example.motorshop.service.DepartService;
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
@RequestMapping("/api/motorshop/departs")
public class DepartController {
    
    @Autowired
    private DepartService departService;
    
    @PostMapping
    public String create(@RequestBody Depart depart) {
        return departService.createDepart(depart);
    }
    
    @GetMapping
    public List<Depart> getAll() {
        return departService.readAll();
    }
    
    @GetMapping("/id")
    public Depart getById(@RequestParam(name = "id") String id) {
        return departService.readById(id);
    }
    
    @GetMapping("/name")
    public List<Depart> getByName(@RequestParam(name = "name") String name) {
        return departService.readByName(name);
    }
    
    @PutMapping
    public String update(@RequestBody Depart depart) {
        return departService.updateDepart(depart);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return departService.deleteDepart(id);
    }
}
