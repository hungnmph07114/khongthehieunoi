package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import com.example.demo.entity.HoaDon;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.service;


@Controller
@RequestMapping("/admin")
public class adminController {
	 @Autowired   
	 service  service ;
	 @Autowired
	HoaDonService hoadonservice;
	@RequestMapping("/search")
	public String search(ModelMap map) {
		List<ProductEntity> products = service.listAll();
		map.addAttribute("product", products);
		return "admin/admin";
	}

}
