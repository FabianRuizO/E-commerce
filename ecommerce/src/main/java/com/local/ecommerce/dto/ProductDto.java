package com.local.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;

public class ProductDto {

    // for create it can be optional
    // for update we need the id
    private Integer id;
    private String role;


    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Image URL is required")
    private String imageURL;

    @Min(value = 0, message = "Price must be greater than 0")
    private double price;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    
    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}