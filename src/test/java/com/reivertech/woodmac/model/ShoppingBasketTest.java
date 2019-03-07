package com.reivertech.woodmac.model;

import com.reivertech.woodmac.FixedDiscount;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

public class ShoppingBasketTest {

    private static final String ITEM_NAME_1 = "Glenkinchie";
    private static final String ITEM_NAME_2 = "Lagavulin";
    private static final BigDecimal ITEM_PRICE_1 = new BigDecimal("34.22");
    private static final BigDecimal ITEM_PRICE_2 = new BigDecimal("54.17");

    private ShoppingBasket basket = new ShoppingBasket();

    @Test
    public void getSubTotal() {
        basket.addItems(Arrays.asList(
            new ShoppingItem(ITEM_NAME_1, ITEM_PRICE_1),
            new ShoppingItem(ITEM_NAME_2, ITEM_PRICE_2))
        );

        final BigDecimal expected = ITEM_PRICE_1.add(ITEM_PRICE_2);

        Assert.assertEquals(expected, basket.getSubTotal());
    }

    @Test
    public void getSubTotalEmptyBasket() {
        Assert.assertEquals(BigDecimal.ZERO, basket.getSubTotal());
    }

    @Test
    public void getTotalEmptyBasket() {
        Assert.assertEquals(BigDecimal.ZERO, basket.getTotal(Collections.emptyList()));
    }

    @Test
    public void getTotalWithFivePoundsDiscount() {
        basket.addItems(Arrays.asList(
            new ShoppingItem(ITEM_NAME_1, ITEM_PRICE_1),
            new ShoppingItem(ITEM_NAME_2, ITEM_PRICE_2))
        );

        final BigDecimal    expected = ITEM_PRICE_1.add(ITEM_PRICE_2).subtract(java.math.BigDecimal.TEN);

        final BigDecimal    total = basket.getTotal(Arrays.asList(new FixedDiscount(new BigDecimal("5.00"))));

        Assert.assertEquals(expected, total);
    }
}
