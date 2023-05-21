package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.GioHang;

import java.util.List;

/**
 * @author caodinh
 */
public interface IGioHangRepository {

   GioHang getGioHangByKhachHangAndTinhTrang(String idKh);

   boolean save(GioHang gioHang);

   String genMaGioHang();

   List<GioHang> getAllByTrangThai1();

   boolean delete(String id);

   GioHang getOneById(String id);

}
