package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository1;
import com.example.demo.service.ProductService;

@Service
@Component
public class ProductServiceImpl implements ProductService {
	 private final ProductRepository1 productRepository;

	    @Autowired
	    public ProductServiceImpl(ProductRepository1 productRepository) {
	        this.productRepository = productRepository;
	    }

	@Override
	public Optional<ProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public Page<ProductEntity> findAllProductsPageable(Pageable pageable) {
//		  return productRepository.findById(pageable);
		 return null;
	}

	@Override
	public List<ProductEntity> find(String keyword) {		
		if(keyword !=null) {
			return productRepository.findProducts(keyword);
		}
		return productRepository.findAll();
	}

}
