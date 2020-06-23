package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class service {
	@Autowired
	ProductRepository repo;
	
	public List<ProductEntity> listAll(){
		return (List<ProductEntity>) repo.findAll();
	}
	public Page<ProductEntity> listAl1(){
		return (Page<ProductEntity>) repo.findAll();
	}
public void save(ProductEntity productEntity) {
	
	repo.save(productEntity);
}
public void delete (Integer id) {
	
	repo.deleteById(id);;
}
public ProductEntity get(Integer id) {
	
	return repo.findById(id).get();
}

	public Optional<ProductEntity> findById(Integer id) {
		return repo.findById(id);
	}

}
