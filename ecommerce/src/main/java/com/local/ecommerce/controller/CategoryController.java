package com.local.ecommerce.controller;

import com.local.ecommerce.common.ApiResponse;
import com.local.ecommerce.model.Category;
import com.local.ecommerce.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        if (!categoryService.existsById(categoryId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Category does not exist"));
        }

        categoryService.editCategory(categoryId, category);
        return ResponseEntity.ok(new ApiResponse(true, "Category has been updated"));
    }
}
