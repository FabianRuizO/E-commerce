package com.local.ecommerce.controller;

import com.local.ecommerce.common.ApiResponse;
import com.local.ecommerce.dto.ProductDto;
import com.local.ecommerce.model.Category;
import com.local.ecommerce.repository.CategoryRepo;
import com.local.ecommerce.service.ProductService;
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
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
 
    @Autowired
    CategoryRepo categoryRepo;

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        try {
            // Verificamos si la categoría existe en la base de datos
            Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
            
            // Si no se encuentra la categoría, devolvemos un error 400 con un mensaje adecuado
            if (!optionalCategory.isPresent()) {
                return new ResponseEntity<ApiResponse>(
                    new ApiResponse(false, "Category with ID " + productDto.getCategoryId() + " does not exist"),
                    HttpStatus.BAD_REQUEST
                );
            }

            // Si la categoría existe, creamos el producto
            productService.createProduct(productDto, optionalCategory.get());
            
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(true, "Product has been added successfully"),
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(false, "Internal Server Error: " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // create an api to edit the product

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }

}