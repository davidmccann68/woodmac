package com.reivertech.woodmac.service;

import com.reivertech.woodmac.model.ShoppingItem;

import java.math.BigDecimal;
import java.util.List;

public class BasketDTO {

    private List<ShoppingItem>  items;
    private BigDecimal          subtotal;
    private BigDecimal          discount;
    private BigDecimal          total;

    public List<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
