package com.ecommerce.project.controller;


import com.ecommerce.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.project.service.CategoryService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

/*
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
*/

    //@GetMapping("api/public/categories")
    @RequestMapping(value = "public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //@PostMapping("api/public/categories")
    @RequestMapping(value = "public/categories", method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody  Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
    }

    //@DeleteMapping("/api/admin/categories/{id}")
    @RequestMapping(value = "/admin/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        try {
            String status = categoryService.deleteCategory(id);
            return ResponseEntity.ok(status);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getReason());
        }
    }

    //@PutMapping("/api/admin/categories/{id}")
    @RequestMapping(value = "/admin/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@PathVariable long id, @RequestBody Category category) {
        try {
            Category status = categoryService.updateCategory(id, category);
            return ResponseEntity.ok("Category updated successfully");
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
