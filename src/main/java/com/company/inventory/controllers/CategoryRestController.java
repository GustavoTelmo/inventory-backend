package com.company.inventory.controllers;
import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> serchCategoriesId(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> serchCategoriesId(@RequestBody Category category){

        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category,@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = categoryService.uptade(category,id);
        return response;
    }
    @DeleteMapping ("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }
}
