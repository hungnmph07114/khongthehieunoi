package com.example.demo.service.impl;

import com.example.demo.entity.HoaDon;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonServicelmpl implements HoaDonService {
    @Autowired
    HoaDonRepository repo;
    @Override
    public List<HoaDon> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
    @Override
    public void save(HoaDon id) {
    repo.save(id);
    }
}
