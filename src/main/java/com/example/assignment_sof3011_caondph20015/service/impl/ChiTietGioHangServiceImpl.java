package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;
import com.example.assignment_sof3011_caondph20015.repository.IChiTietGioHangRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.ChiTietGioHangRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.IChiTietGioHangService;
import com.example.assignment_sof3011_caondph20015.viewmodel.response.ChiTietGioHangResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caodinh
 */
public class ChiTietGioHangServiceImpl implements IChiTietGioHangService {

    private IChiTietGioHangRepository gioHangRepository = new ChiTietGioHangRepositoryImpl();

    @Override
    public boolean save(GioHangChiTiet gioHangChiTiet) {
        return gioHangRepository.save(gioHangChiTiet);
    }

    @Override
    public List<GioHangChiTiet> getAllByIdGH(String idGH) {
        return gioHangRepository.getAllByIDGioHang(idGH);
    }

    @Override
    public GioHangChiTiet getOneByIDSanPham(String idSP) {
        return gioHangRepository.getOneByIDSanPham(idSP);
    }

    @Override
    public GioHangChiTiet getOneById(String id) {
        return gioHangRepository.getOneById(id);
    }

    @Override
    public boolean delete(String id) {
        return gioHangRepository.delete(id);
    }

    @Override
    public List<ChiTietGioHangResponse> getAllByIDGioHang1(String idGh) {
        List<ChiTietGioHangResponse> chiTietGioHangResponses = new ArrayList<>();
        for (GioHangChiTiet gioHangChiTiet : gioHangRepository.getAllByIDGioHang1(idGh)) {
            ChiTietGioHangResponse chiTietGioHangResponse = new ChiTietGioHangResponse();
            chiTietGioHangResponse.setImg(gioHangChiTiet.getChiTietSP().getImg());
            chiTietGioHangResponse.setTenSP(gioHangChiTiet.getChiTietSP().getTenSP());
            chiTietGioHangResponse.setSoLuong(gioHangChiTiet.getSoLuong());
            chiTietGioHangResponse.setTotalPrice(gioHangChiTiet.getSoLuong() * gioHangChiTiet.getDonGia().doubleValue());
            chiTietGioHangResponses.add(chiTietGioHangResponse);
        }
        return chiTietGioHangResponses;
    }

}
