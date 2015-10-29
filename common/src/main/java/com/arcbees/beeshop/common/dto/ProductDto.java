package com.arcbees.beeshop.common.dto;

public class ProductDto {
    private Brand brand;

    public Product getProduct() {
        return product;
    }

    private Product product;

    public ProductDto() {
    }

    public ProductDto(Product product, Brand brand) {
        this.product = product;
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ProductType getProductType() {
        return product.getProductType();
    }

    public int getPrice() {
        return product.getProductType().getPrice();
    }
}
