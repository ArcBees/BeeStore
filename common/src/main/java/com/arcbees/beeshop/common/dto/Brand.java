package com.arcbees.beeshop.common.dto;

public enum Brand {
    GAE_STUDIO("gae-studio"),
    CHOSEN("chosen"),
    GWTP("gwtp"),
    JUKITO("jukito"),
    GSSS("gsss"),
    GQUERY("gquery"),
    ARCBEES("arcbees");

    private final String value;

    Brand(String value) {
        this.value = value;
    }

    public static Brand createFromValue(String value) {
        for (Brand brand : values()) {
            if (brand.value.equals(value)) {
                return brand;
            }
        }

        return ARCBEES;
    }

    public static Brand getDefaultValue() {
        return Brand.ARCBEES;
    }

    public String getValue() {
        return value;
    }
}
