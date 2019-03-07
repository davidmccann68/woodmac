package com.reivertech.woodmac.service;

import com.reivertech.woodmac.model.Discount;

import java.util.List;

public interface DiscountService {

    /**
     * Retrieve the Discounts which apply today.
     *
     * @return  a List of Discounts, which may be empty
     */
    public List<Discount> getTodaysDiscounts();
}
