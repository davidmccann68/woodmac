package com.reivertech.woodmac;

import com.reivertech.woodmac.model.Discount;
import com.reivertech.woodmac.model.ShoppingItem;

import java.math.BigDecimal;
import java.util.Collection;

public class FixedDiscount implements Discount {
    private BigDecimal fixedAmount;

    public FixedDiscount(BigDecimal amount) {
        fixedAmount = amount;
    }

    @Override
    public BigDecimal apply(Collection<ShoppingItem> items) {
        return items.stream().map(item -> fixedAmount.min(item.getPrice()).negate())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
