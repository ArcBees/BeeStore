package com.arcbees.beeshop.common.dto;

public enum Product {
    SHIRT(1),
    BAG(2),
    USB_KEY(3),
    MUG(4),
    THERMOS(5),
    PHONE_CASE(6);

    private final int value;

    Product(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
