package com.reivertech.woodmac.model;

import com.reivertech.woodmac.model.ShoppingItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ShoppingItemTest {

    private static final String     ITEM_NAME = "Ardmore Triple Wood";
    private static final BigDecimal ITEM_VALUE = new BigDecimal("47.11");

    @Test
    public void checkSanity() {
        final ShoppingItem item = new ShoppingItem(ITEM_NAME, ITEM_VALUE);

        Assert.assertEquals(ITEM_NAME, item.getName());
        Assert.assertEquals(ITEM_VALUE, item.getPrice());
    }
}
