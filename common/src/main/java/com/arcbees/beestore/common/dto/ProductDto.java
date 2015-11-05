package com.arcbees.beestore.common.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private Brand brand;
    private Product product;

    public ProductDto() {
    }

    public ProductDto(Product product, Brand brand) {
        this.product = product;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        if (brand != that.brand) return false;
        return !(product != null ? !product.equals(that.product) : that.product != null);
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    public Product getProduct() {
        return product;
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
