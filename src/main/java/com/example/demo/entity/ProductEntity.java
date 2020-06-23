package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	private double price;
	private String description;
	private String img;
	private boolean status;
	@ManyToMany( fetch = FetchType.LAZY,
			cascade = { CascadeType.REMOVE,
					  CascadeType.PERSIST,
					   CascadeType.MERGE
			},mappedBy = "products")
	private List<HoaDon> hoadons = new ArrayList<>();

	public ProductEntity(Integer id, String code, String name, double price, String description, String img,
						 boolean status) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.img = img;
		this.status = status;
	}

	public ProductEntity(String name, double price, String description, String img) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.img = img;
	}
	public ProductEntity() {

	}

	public List<HoaDon> getHoadons() {
		return hoadons;
	}

	public void setHoadons(List<HoaDon> hoadons) {
		this.hoadons = hoadons;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
