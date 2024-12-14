package com.local.ecommerce.service;

import com.local.ecommerce.dto.ProductDto;
import com.local.ecommerce.exceptions.ProductNotExistsException;
import com.local.ecommerce.model.Category;
import com.local.ecommerce.model.Product;
import com.local.ecommerce.repository.CategoryRepo;
import com.local.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    CategoryRepo categoryRepo;

    // public ProductService(ProductRepository productRepository, CategoryRepo categoryRepo) {
    //     this.productRepository = productRepository;
    //     this.categoryRepo = categoryRepo;
    // }
    public Product createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
    
        // Guardar el producto en la base de datos
        return productRepository.save(product);
    }


    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // throw an exception if product does not exists
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
    
    public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
}