package com.example.orderingsystem.models;

public enum ProductEnum {
    FOOD("FOOD"),
    DRINK("DRINK"),
    DESSERT("DESSERT");

    private final String TYPE_PRODUCT;

    ProductEnum(String type) {
        this.TYPE_PRODUCT = type;
    }

    public ProductEnum ProductEnumType(String type) {
        for (ProductEnum product : values()) {
            if (product.getTYPE_PRODUCT().equals(type)) {
                return product;
            }
        }
        return null;
    }

    public String getTYPE_PRODUCT(){
        return this.TYPE_PRODUCT;
    }

    @Override
    public String toString() {
        return  TYPE_PRODUCT;
    }
}
