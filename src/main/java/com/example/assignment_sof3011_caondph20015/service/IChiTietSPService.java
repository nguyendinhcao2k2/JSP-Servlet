package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLChiTietSanPhamErrorMessage;

import java.util.List;

/**
 * @author caodinh
 */
public interface IChiTietSPService {

    List<ChiTietSanPham> getTop5();

    List<ChiTietSanPham> getAll();

    ChiTietSanPham getOne(String id);

    List<ChiTietSanPham> getSanPhamByMaNSX(String ma);

    boolean save(QLChiTietSanPham qlChiTietSanPham, QLChiTietSanPhamErrorMessage errorMessage);

    boolean update(ChiTietSanPham chiTietSP);

     boolean delete(String id);

}
