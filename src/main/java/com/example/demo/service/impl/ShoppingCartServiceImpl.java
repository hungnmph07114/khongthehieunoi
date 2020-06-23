package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ShoppingCartService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<ProductEntity, Integer> products = new HashMap<>();

	@Override
	public void addProduct(ProductEntity product) {
		if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
		
	}

	@Override
	public void removeProduct(ProductEntity product) {
		 if (products.containsKey(product)) {
	            if (products.get(product) > 1)
	                products.replace(product, products.get(product) - 1);
	            else if (products.get(product) == 1) {
	                products.remove(product);
	            }
	        }
		
	}

	@Override
	public Map<ProductEntity, Integer> getProductsInCart() {
		 return Collections.unmodifiableMap(products);
	}

	@Override
	public void checkout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal getTotal() {
		// TODO Auto-generated method stub
		return null;
	}
}
