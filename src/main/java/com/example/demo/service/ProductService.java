package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<ProductEntity> findById(Integer id);

    Page<ProductEntity> findAllProductsPageable(Pageable pageable);
    List<ProductEntity> find(String name);
}