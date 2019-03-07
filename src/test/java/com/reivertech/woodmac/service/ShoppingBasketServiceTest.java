package com.reivertech.woodmac.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reivertech.woodmac.FixedDiscount;
import com.reivertech.woodmac.model.ShoppingBasket;
import com.reivertech.woodmac.model.ShoppingItem;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;


public class ShoppingBasketServiceTest {

    private static final ShoppingItem   ITEM = new ShoppingItem("An Item", BigDecimal.TEN);
    private static final ShoppingItem   ITEM2 = new ShoppingItem("Another Item", BigDecimal.TEN);

    private DiscountService         discountService = EasyMock.createMock(DiscountService.class);
    private ShoppingBasketService   service = new ShoppingBasketService();

    @Before
    public void setUp() {
        service.setDiscountService(discountService);
    }

    @Test
    public void addItem() {
        final ShoppingBasket    basket = new ShoppingBasket();
        Assert.assertTrue(basket.getContents().isEmpty());

        service.addItem(basket, ITEM);
        Assert.assertEquals(1, basket.getContents().size());
    }

    @Test
    public void removeItem() {
        final ShoppingBasket    basket = new ShoppingBasket();
        basket.addItems(Collections.singletonList(ITEM));

        service.removeItem(basket, ITEM);
        Assert.assertTrue(basket.getContents().isEmpty());
    }

    @Test
    public void getShoppingBasket() {
        EasyMock.expect(discountService.getTodaysDiscounts())
            .andReturn(Collections.singletonList(new FixedDiscount(new BigDecimal("1.00"))));
        EasyMock.replay(discountService);

        final ShoppingBasket    basket = new ShoppingBasket();
        basket.addItems(Arrays.asList(ITEM, ITEM2));

        final JsonElement   json = service.getShoppingBasket(basket);

        final JsonObject    jsonObject = json.getAsJsonObject();

        Assert.assertEquals(2, jsonObject.getAsJsonArray("items").size());

        final BigDecimal    subtotal = jsonObject.getAsJsonPrimitive("subtotal").getAsBigDecimal();
        final BigDecimal    discount = jsonObject.getAsJsonPrimitive("discount").getAsBigDecimal();
        final BigDecimal    total = jsonObject.getAsJsonPrimitive("total").getAsBigDecimal();

        Assert.assertEquals(new BigDecimal("20.00"), subtotal);
        Assert.assertEquals(new BigDecimal("2.00").negate(), discount);
        Assert.assertEquals(new BigDecimal("18.00"), total);

        EasyMock.verify(discountService);
    }
}
