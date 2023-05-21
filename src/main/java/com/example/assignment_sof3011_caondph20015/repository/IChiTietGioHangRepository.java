package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;

import java.util.List;

/**
 * @author caodinh
 */
public interface IChiTietGioHangRepository {

    boolean save(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> getAllByIDGioHang(String idGh);

    GioHangChiTiet getOneByIDSanPham(String idSP);

    GioHangChiTiet getOneById(String id);

    boolean delete(String id);
    List<GioHangChiTiet> getAllByIDGioHang1(String idGh);

}
