package com.reivertech.woodmac.model;

import java.math.BigDecimal;
import java.util.Collection;

public interface Discount {

    /**
     * Calculate any applicable discount to a collection of {@link ShoppingItem}s. If none applies,
     * BigDecimal.ZERO will be returned.
     *
     * @param items items to process
     * @return      the discount
     */
    public BigDecimal apply(Collection<ShoppingItem> items);
}
