package com.example.demo.service;
import java.math.BigDecimal;
import java.util.Map;

import com.example.demo.entity.ProductEntity;

public interface ShoppingCartService {

    void addProduct(ProductEntity product);

    void removeProduct(ProductEntity product);

    Map<ProductEntity, Integer> getProductsInCart();

    void checkout();

    BigDecimal getTotal();
}