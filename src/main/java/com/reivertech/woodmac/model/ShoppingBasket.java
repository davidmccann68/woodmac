package com.reivertech.woodmac.model;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingBasket {

    private List<ShoppingItem>   contents = new ArrayList<>();

    public void addItems(Collection<ShoppingItem> items) {
        contents.addAll(items);
    }

    public void removeItems(Collection<ShoppingItem> items) {
        contents.removeAll(items);
    }

    public List<ShoppingItem> getContents() {
        return new ArrayList<>(contents);
    }

    public BigDecimal getSubTotal() {
        return contents.stream()
            .map(item -> item.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountTotal(List<Discount> discounts) {
        return discounts.stream()
            .map(d -> d.apply(contents))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal(List<Discount> discounts) {
        return getSubTotal().add(getDiscountTotal(discounts));
    }
}
