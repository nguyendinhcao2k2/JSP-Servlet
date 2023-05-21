package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;
import com.example.assignment_sof3011_caondph20015.viewmodel.response.ChiTietGioHangResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface IChiTietGioHangService {

    boolean save(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> getAllByIdGH(String idGH);

    GioHangChiTiet getOneByIDSanPham(String idSP);

    GioHangChiTiet getOneById(String id);

    boolean delete(String id);

    List<ChiTietGioHangResponse> getAllByIDGioHang1(String idGh);

}
