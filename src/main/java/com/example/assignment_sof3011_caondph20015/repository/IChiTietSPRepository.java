package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;

import java.util.List;

/**
 * @author caodinh
 */
public interface IChiTietSPRepository {

     List<ChiTietSanPham> getTop5();

     List<ChiTietSanPham> getAll();

     ChiTietSanPham getOne(String id);

     List<ChiTietSanPham> getSanPhamByMaNSX(String ma);

     boolean save(ChiTietSanPham chiTietSP);

     boolean delete(String id);

}
