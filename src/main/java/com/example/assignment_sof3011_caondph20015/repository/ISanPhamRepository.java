package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.SanPham;

import java.util.List;

/**
 * @author caodinh
 */
public interface ISanPhamRepository {

    List<SanPham> findAll();

    boolean save(SanPham sp);

     String genMaSanPham();

     SanPham getSPBYName(String name);

}
