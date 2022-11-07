package com.company.inventory.controllers;

import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> serchCategories(){

       ResponseEntity<CategoryResponseRest> response = categoryService.search();
       return response;
    }



}