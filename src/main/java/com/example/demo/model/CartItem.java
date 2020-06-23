package com.example.demo.model;

import com.example.demo.entity.ProductEntity;

/**
 * Created by Devon Ravihansa on 8/29/2017.
 */
public class CartItem {
    private final ProductEntity product;
    private int quantity;
    private double subTotal;

    public CartItem(ProductEntity product) {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        subTotal = product.getPrice() * quantity;
        return subTotal;
    }
}
