package com.arcbees.beeshop.common.dto;

public enum Product {
    SHIRT(1, 30),
    BAG(2, 25),
    USB_KEY(3, 10),
    MUG(4, 10),
    THERMOS(5, 5),
    PHONE_CASE(6, 15);

    private final int value;

    public int getPrice() {
        return price;
    }

    private final int price;

    Product(int value, int price) {
        this.value = value;
        this.price = price;
    }

    public int getValue() {
        return value;
    }
}
