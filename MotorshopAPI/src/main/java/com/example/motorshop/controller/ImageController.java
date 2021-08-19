/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.motorshop.controller;

import com.example.motorshop.entity.Image;
import com.example.motorshop.service.ImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hungh
 */
@RestController
@RequestMapping("/api/motorshop/images")
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    @PostMapping
    public String create(@RequestBody Image image) {
        return imageService.createImage(image);
    }
    
    @GetMapping
    public List<Image> getAll() {
        return imageService.readAll();
    }    
    
    @GetMapping("/motorId")
    public List<byte[]> getByMotorId(@RequestParam(name = "motorId") String motorId) {
        return imageService.readByMotorId(motorId);
    }

    @GetMapping("/accessoryId")
    public List<byte[]> getByAccessoryId(@RequestParam(name = "accessoryId") String accessoryId) {
        return imageService.readByAccessoryId(accessoryId);
    }
    
    @DeleteMapping
    public String delete(@RequestParam(name = "id") String id) {
        return imageService.deleteImage(id);
    }  
}
