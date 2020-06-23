package com.example.demo.entity;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "hoadon")
public class HoaDon {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	private String name ;
	private String email ;
	private Integer sdt ;
	private String diachi ;
	private int soluong;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayhoadon;
	private String trangthai ;
	@ManyToMany()
	@JoinTable(name = "hoadonchitiet",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "hoadon_id"))
	private List<ProductEntity> products = new ArrayList<>();

	public HoaDon() {
	}
	public HoaDon(String trangthai, List<ProductEntity> products) {
		this.trangthai = trangthai;
		this.products = products;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSdt() {
		return sdt;
	}

	public void setSdt(Integer sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNgayhoadon() {
		return ngayhoadon;
	}

	public void setNgayhoadon(Date ngayhoadon) {
		this.ngayhoadon = ngayhoadon;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
}
