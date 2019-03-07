package com.reivertech.woodmac.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingItem {

    private String      name;
    private BigDecimal  price;

    public ShoppingItem(String itemName, BigDecimal itemPrice) {
        name = itemName;
        price = itemPrice.setScale(2, RoundingMode.HALF_DOWN);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
