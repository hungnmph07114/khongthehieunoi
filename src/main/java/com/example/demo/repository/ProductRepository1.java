package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ProductEntity;
public interface ProductRepository1  extends JpaRepository<ProductEntity, Integer> {
	
	Optional<ProductEntity> findById(Integer id);
	
	@Query(value="select * from products  where name like %:keyword%", nativeQuery=true)
	List<ProductEntity> findProducts(@Param("keyword") String keyword);
	 
}
