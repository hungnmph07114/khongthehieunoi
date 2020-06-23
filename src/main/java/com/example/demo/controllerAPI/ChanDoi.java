package com.example.demo.controllerAPI;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChanDoi {
    @Autowired
    service sv;
    @RequestMapping("Dmmm")
    public List<ProductEntity> dmmm(){
        return sv.listAll();
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductEntity>update(@PathVariable("id") Integer id, @RequestBody ProductEntity productEntity){
        Optional<ProductEntity> product = sv.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        product.get().setName(productEntity.getName());
        product.get().setPrice(productEntity.getPrice());
        product.get().setDescription(productEntity.getDescription());
        product.get().setImg(productEntity.getImg());
        product.get().setStatus(productEntity.isStatus());

        sv.save(product.get());
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
