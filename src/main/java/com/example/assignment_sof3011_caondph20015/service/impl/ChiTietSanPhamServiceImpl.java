package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.entity.DongSanPham;
import com.example.assignment_sof3011_caondph20015.entity.MauSac;
import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;
import com.example.assignment_sof3011_caondph20015.entity.SanPham;
import com.example.assignment_sof3011_caondph20015.repository.IChiTietSPRepository;
import com.example.assignment_sof3011_caondph20015.repository.IDongSanPhamRepository;
import com.example.assignment_sof3011_caondph20015.repository.IMauSacRepository;
import com.example.assignment_sof3011_caondph20015.repository.INhaSanXuatRepository;
import com.example.assignment_sof3011_caondph20015.repository.ISanPhamRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.ChiTietSPRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.repository.impl.DongSPRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.repository.impl.MauSacRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.repository.impl.NhaSanXuatRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.repository.impl.SanPhamRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.IChiTietSPService;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLChiTietSanPhamErrorMessage;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caodinh
 */
public class ChiTietSanPhamServiceImpl implements IChiTietSPService {
    private IChiTietSPRepository chiTietSPRepository = new ChiTietSPRepositoryImpl();
    private INhaSanXuatRepository nhaSanXuatRepository = new NhaSanXuatRepositoryImpl();
    private IDongSanPhamRepository dongSanPhamRepository = new DongSPRepositoryImpl();
    private IMauSacRepository mauSacRepository = new MauSacRepositoryImpl();

    private ISanPhamRepository sanPhamRepository = new SanPhamRepositoryImpl();

    @Override
    public List<ChiTietSanPham> getTop5() {
        return chiTietSPRepository.getTop5();
    }

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSPRepository.getAll();
    }

    @Override
    public ChiTietSanPham getOne(String id) {
        return chiTietSPRepository.getOne(id);
    }

    @Override
    public List<ChiTietSanPham> getSanPhamByMaNSX(String ma) {
        return chiTietSPRepository.getSanPhamByMaNSX(ma);
    }

    @Override
    public boolean save(QLChiTietSanPham qlChiTietSanPham, QLChiTietSanPhamErrorMessage errorMessage) {
        boolean check = true;
        if (qlChiTietSanPham.getTen().isEmpty()) {
            errorMessage.setTenError("Tên Không Được Để Trống");
            check = false;
        }

        if (qlChiTietSanPham.getImg().isEmpty()) {
            errorMessage.setImgError("Ảnh Không Được Để Trống");
            check = false;
        }

        if (qlChiTietSanPham.getMoTa().isEmpty()) {
            errorMessage.setMoTaError("Mô Tả Không Được Để Trống");
            check = false;
        }

        if (qlChiTietSanPham.getGiaBan().isEmpty()) {
            errorMessage.setGiaBanError("Giá Bán Không Được Để Trống");
            check = false;
        } else {
            if (!qlChiTietSanPham.getGiaBan().matches("-?\\d+(\\.\\d+)?")) {
                errorMessage.setGiaBanError("Giá Bán Phải Là Số");
                check = false;
            }
        }

        if (qlChiTietSanPham.getSoLuongTon().isEmpty()) {
            errorMessage.setSoLuongTonError("Số Lượng Tồn Không Được Để Trống");
            check = false;
        } else {
            if (!qlChiTietSanPham.getSoLuongTon().matches("-?\\d+(\\.\\d+)?")) {
                errorMessage.setSoLuongTonError("Số Lượng Tồn Phải Là Số");
                check = false;
            }
        }
        if (check == true) {
            ChiTietSanPham chiTietSP = new ChiTietSanPham();
            chiTietSP.setImg(qlChiTietSanPham.getImg());
            chiTietSP.setGiaBan(BigDecimal.valueOf(Double.valueOf(qlChiTietSanPham.getGiaBan())));
            chiTietSP.setMoTa(qlChiTietSanPham.getMoTa());
            chiTietSP.setSoLuongTon(Integer.valueOf(qlChiTietSanPham.getSoLuongTon()));
            chiTietSP.setGiaNhap(BigDecimal.valueOf(20));
            chiTietSP.setNamBH(2);
            NhaSanXuat nsx = nhaSanXuatRepository.getOneByMa(qlChiTietSanPham.getNsx());
            chiTietSP.setNhaSanXuat(nsx);
            DongSanPham dongSP = dongSanPhamRepository.getOne();
            chiTietSP.setDongSanPham(dongSP);
            MauSac mauSac = mauSacRepository.getOne(qlChiTietSanPham.getMauSac());
            chiTietSP.setMauSac(mauSac);
            SanPham sanPham = new SanPham();
            sanPham.setMa(sanPhamRepository.genMaSanPham());
            sanPham.setTen(qlChiTietSanPham.getTen());
            sanPhamRepository.save(sanPham);
            chiTietSP.setTenSP(qlChiTietSanPham.getTen());
            chiTietSPRepository.save(chiTietSP);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ChiTietSanPham chiTietSP) {
        return chiTietSPRepository.save(chiTietSP);
    }

    @Override
    public boolean delete(String id) {
        return chiTietSPRepository.delete(id);
    }

}
