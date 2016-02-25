package com.arcbees.beestore.client.application;

public enum ShippingMethod {
    STANDARD(10),
    INTERNATIONAL(40);

    private float price;

    ShippingMethod(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
