package com.arcbees.beestore.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {
    private Brand brand;
    private Product product;

    public ProductDto() {
    }

    @JsonCreator
    public ProductDto(
            @JsonProperty("product") Product product,
            @JsonProperty("brand") Brand brand) {
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

    @JsonIgnore
    public ProductType getProductType() {
        return product.getProductType();
    }

    @JsonIgnore
    public int getPrice() {
        return product.getPrice();
    }
}
