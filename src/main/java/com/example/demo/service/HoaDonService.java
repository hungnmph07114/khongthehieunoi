package com.example.demo.service;

import com.example.demo.entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> findAll();
    void delete(Integer id);
    void save(HoaDon id);
}
