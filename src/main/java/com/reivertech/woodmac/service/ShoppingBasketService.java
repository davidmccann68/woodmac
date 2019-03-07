package com.reivertech.woodmac.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.reivertech.woodmac.model.Discount;
import com.reivertech.woodmac.model.ShoppingBasket;
import com.reivertech.woodmac.model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Business service which would be called directly from a Controller.
 */
public class ShoppingBasketService {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingBasketService.class);

    private DiscountService discountService;

    /**
     * Return the contents of a ShoppingBasket, any applied discount and the total amount
     * as a JsonElement.
     *
     * @param basket    the ShoppingBasket to process
     * @return          a JSON object describing the basket
     */
    public JsonElement getShoppingBasket(ShoppingBasket basket) {
        final List<Discount>    discounts = discountService.getTodaysDiscounts();

        final BigDecimal        subtotal = basket.getSubTotal();
        final BigDecimal        discountAmount = basket.getDiscountTotal(discounts);
        final BigDecimal        totalAmount = basket.getTotal(discounts);

        final BasketDTO dto = new BasketDTO();

        dto.setItems(basket.getContents());
        dto.setSubtotal(subtotal);
        dto.setDiscount(discountAmount);
        dto.setTotal(totalAmount);

        final Gson  gson = new Gson();
        final JsonElement   retVal = gson.toJsonTree(dto);

        LOG.debug("Shopping Basket: " + retVal);
        return retVal;
    }

    public void addItem(ShoppingBasket basket, ShoppingItem item) {
        basket.addItems(Arrays.asList(item));
    }

    public void removeItem(ShoppingBasket basket, ShoppingItem item) {
        basket.removeItems(Arrays.asList(item));
    }

    public void setDiscountService(DiscountService svc) {
        discountService = svc;
    }
}
