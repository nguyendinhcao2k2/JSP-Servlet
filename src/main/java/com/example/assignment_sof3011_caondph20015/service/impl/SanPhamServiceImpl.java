package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.SanPham;
import com.example.assignment_sof3011_caondph20015.repository.ISanPhamRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.SanPhamRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.ISanPhamService;

import java.util.List;

/**
 * @author caodinh
 */
public class SanPhamServiceImpl implements ISanPhamService {
    private ISanPhamRepository sanPhamRepository = new SanPhamRepositoryImpl();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

}
