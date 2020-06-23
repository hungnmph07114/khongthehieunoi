package com.example.demo.entity;

public class HoaDonDto {
    private String name ;
    private String email ;
    private Integer sdt ;
    private String diachi ;
    public HoaDonDto(String name, String email, Integer sdt, String diachi) {
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public HoaDonDto() {

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
}
