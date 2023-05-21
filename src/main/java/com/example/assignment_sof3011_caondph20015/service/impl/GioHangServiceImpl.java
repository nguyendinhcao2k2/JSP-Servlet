package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;
import com.example.assignment_sof3011_caondph20015.repository.IGioHangRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.GioHangRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.IChiTietSPService;
import com.example.assignment_sof3011_caondph20015.service.IGioHangService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author caodinh
 */
public class GioHangServiceImpl implements IGioHangService {
    private IGioHangRepository gioHangRepository = new GioHangRepositoryImpl();

    private IChiTietSPService chiTietSPService = new ChiTietSanPhamServiceImpl();

    @Override
    public void addCart(HttpSession session, GioHang gioHang) {
        if (gioHang == null) {
            GioHang gh = new GioHang();
            gh.setNgayTao(new Date());
            gh.setMa(gioHangRepository.genMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            gh.setChiTietGioHang(gioHangChiTietList);
            gh.setTinhTrang(0);
            session.setAttribute("gh", gh);
            session.setAttribute("sizeCart", gh.getChiTietGioHang().size());
        }
    }

    @Override
    public void buyNow(HttpSession session, GioHang gioHang, String id,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (gioHang == null) {
            ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
            GioHang gh = new GioHang();
            gh.setNgayTao(new Date());
            gh.setMa(gioHangRepository.genMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            //Thêm Giỏ hàng chi tiết vào giỏ hàng
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setChiTietSP(chiTietSP);
            gioHangChiTiet.setGioHang(gh);
            gioHangChiTiet.setDonGia(chiTietSP.getGiaBan());
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTietList.add(gioHangChiTiet);
            gh.setChiTietGioHang(gioHangChiTietList);
            gh.setTinhTrang(0);
            //Set Tổng tiền
            double totalPrice = 0;
            for (int i = 0; i < gioHangChiTietList.size(); i++) {
                totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
            }
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("gh", gh);
            session.setAttribute("sizeCart", gh.getChiTietGioHang().size());
            resp.setContentType("text/plain");
            req.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Data saved successfully!");
        } else {
            ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
            List<GioHangChiTiet> gioHangChiTietList = gioHang.getChiTietGioHang();
            //Kiểm Tra Xem sản phẩm đã có trong giỏ hàng hay chưa
            boolean check = false;
            if (gioHangChiTietList.size() > 0) {
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    if (gioHangChiTietList.get(i).getChiTietSP().getId().equals(chiTietSP.getId())) {
                        gioHangChiTietList.get(i).setSoLuong(gioHangChiTietList.get(i).getSoLuong() + 1);
                        check = true;
                    }
                }
            }
            //Kiểm tra nếu không có sản phẩm nào trong giỏ hàng sẽ vào if còn lại sẽ vào else
            if (check == false) {
                GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setChiTietSP(chiTietSP);
                gioHangChiTiet.setGioHang(gioHang);
                gioHangChiTiet.setDonGia(chiTietSP.getGiaBan());
                gioHangChiTiet.setSoLuong(1);

                gioHangChiTietList.add(gioHangChiTiet);
                //Set Tổng tiền
                double totalPrice = 0;
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
                }
                session.setAttribute("totalPrice", totalPrice);
                //set lại data cho giỏ hàng rồi trả về trang chủ
                session.setAttribute("gh", gioHang);
                session.setAttribute("sizeCart", gioHang.getChiTietGioHang().size());
                resp.setContentType("text/plain");
                req.setCharacterEncoding("UTF-8");
                resp.getWriter().write("Data saved successfully!");
            } else {
                //Set Tổng tiền
                double totalPrice = 0;
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
                }
                session.setAttribute("totalPrice", totalPrice);
                session.setAttribute("gh", gioHang);
                session.setAttribute("sizeCart", gioHang.getChiTietGioHang().size());
                resp.setContentType("text/plain");
                req.setCharacterEncoding("UTF-8");
                resp.getWriter().write("Data saved successfully!");
            }
        }
    }

    @Override
    public void addToCart(HttpSession session, GioHang gioHang, String id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (gioHang == null) {
            ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
            GioHang gh = new GioHang();
            gh.setNgayTao(new Date());
            gh.setMa(gioHangRepository.genMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            //Thêm Giỏ hàng chi tiết vào giỏ hàng
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setChiTietSP(chiTietSP);
            gioHangChiTiet.setGioHang(gh);
            gioHangChiTiet.setDonGia(chiTietSP.getGiaBan());
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTietList.add(gioHangChiTiet);
            gh.setChiTietGioHang(gioHangChiTietList);
            gh.setTinhTrang(0);
            //Set Tổng tiền
            double totalPrice = 0;
            for (int i = 0; i < gioHangChiTietList.size(); i++) {
                totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
            }
            session.setAttribute("totalPrice", totalPrice);

            session.setAttribute("gh", gh);
            session.setAttribute("sizeCart", gh.getChiTietGioHang().size());
            resp.setContentType("text/plain");
            req.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Data saved successfully!");
        } else {
            ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
            List<GioHangChiTiet> gioHangChiTietList = gioHang.getChiTietGioHang();
            //Kiểm Tra Xem sản phẩm đã có trong giỏ hàng hay chưa
            boolean check = false;
            if (gioHangChiTietList.size() > 0) {
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    if (gioHangChiTietList.get(i).getChiTietSP().getId().equals(chiTietSP.getId())) {
                        gioHangChiTietList.get(i).setSoLuong(gioHangChiTietList.get(i).getSoLuong() + 1);
                        check = true;
                    }
                }
            }
            //Kiểm tra nếu không có sản phẩm nào trong giỏ hàng sẽ vào if còn lại sẽ vào else
            if (check == false) {
                GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setChiTietSP(chiTietSP);
                gioHangChiTiet.setGioHang(gioHang);
                gioHangChiTiet.setDonGia(chiTietSP.getGiaBan());
                gioHangChiTiet.setSoLuong(1);

                gioHangChiTietList.add(gioHangChiTiet);
                //Set Tổng tiền
                double totalPrice = 0;
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
                }
                session.setAttribute("totalPrice", totalPrice);
                //set lại data cho giỏ hàng rồi trả về trang chủ
                session.setAttribute("gh", gioHang);
                session.setAttribute("sizeCart", gioHang.getChiTietGioHang().size());
                resp.setContentType("text/plain");
                req.setCharacterEncoding("UTF-8");
                resp.getWriter().write("Data saved successfully!");
            } else {
                //Set Tổng tiền
                double totalPrice = 0;
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
                }
                session.setAttribute("totalPrice", totalPrice);
                session.setAttribute("gh", gioHang);
                session.setAttribute("sizeCart", gioHang.getChiTietGioHang().size());
                resp.setContentType("text/plain");
                req.setCharacterEncoding("UTF-8");
                resp.getWriter().write("Data saved successfully!");
            }
        }
    }

    @Override
    public void checkOut(String diaChi, String sdt, String tenNguoiNhan, HttpSession session, Account account) {
        if (account == null) {
            GioHang gioHang = (GioHang) session.getAttribute("gh");
            gioHang.setDiaChi(diaChi);
            gioHang.setSdt(sdt);
            gioHang.setTenNguoiNhan(tenNguoiNhan);
            gioHang.setTinhTrang(1);
            gioHangRepository.save(gioHang);
            session.removeAttribute("gh");
        } else {
            GioHang gioHang = (GioHang) session.getAttribute("gh");
            gioHang.setDiaChi(diaChi);
            gioHang.setSdt(sdt);
            gioHang.setTenNguoiNhan(tenNguoiNhan);
            gioHang.setTinhTrang(1);
            gioHang.setAccount(account);
            gioHangRepository.save(gioHang);
            session.removeAttribute("gh");
        }
    }

    @Override
    public void update(String id, String soLuong, HttpSession session, GioHang gioHang,HttpServletResponse resp) throws IOException {
        if (gioHang != null) {
            for (int i = 0; i < gioHang.getChiTietGioHang().size(); i++) {
                if (gioHang.getChiTietGioHang().get(i).getChiTietSP().getId().equals(id)) {
                    gioHang.getChiTietGioHang().get(i).setSoLuong(Integer.valueOf(soLuong));
                }
            }
            resp.sendRedirect("/cart/show-cart");
        }
    }

    @Override
    public void removeCart(String id, HttpSession session, GioHang gioHang, HttpServletResponse resp) throws IOException {
        if (gioHang != null) {
            for (int i = 0; i < gioHang.getChiTietGioHang().size(); i++) {
                if (gioHang.getChiTietGioHang().get(i).getChiTietSP().getId().equals(id)) {
                    gioHang.getChiTietGioHang().remove(i);
                }
            }
            resp.sendRedirect("/cart/show-cart");
        } else {
            resp.sendRedirect("/cart/show-cart");
        }
    }

    @Override
    public void showCart(HttpSession session, GioHang gioHang, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (gioHang == null) {
            GioHang gh = new GioHang();
            gh.setNgayTao(new Date());
            gh.setMa(gioHangRepository.genMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            gh.setChiTietGioHang(gioHangChiTietList);
            gh.setTinhTrang(0);
            double totalPrice = 0;
            for (int i = 0; i < gioHangChiTietList.size(); i++) {
                totalPrice += gioHangChiTietList.get(i).getSoLuong() * gioHangChiTietList.get(i).getDonGia().doubleValue();
            }
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("gh", gh);
            session.setAttribute("sizeCart", gh.getChiTietGioHang().size());
            req.setAttribute("ghList", gh.getChiTietGioHang());
            req.getRequestDispatcher("/view/cart.jsp").forward(req, resp);
        } else {
            double totalPrice = 0;
            for (int i = 0; i < gioHang.getChiTietGioHang().size(); i++) {
                totalPrice += gioHang.getChiTietGioHang().get(i).getSoLuong() * gioHang.getChiTietGioHang().get(i).getDonGia().doubleValue();
            }
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("sizeCart", gioHang.getChiTietGioHang().size());
            req.setAttribute("ghList", gioHang.getChiTietGioHang());
            req.getRequestDispatcher("/view/cart.jsp").forward(req, resp);
        }
    }

    @Override
    public boolean save(GioHang gioHang) {
        gioHang.setMa(gioHangRepository.genMaGioHang());
        return gioHangRepository.save(gioHang);
    }

    @Override
    public String genMaGH() {
        return gioHangRepository.genMaGioHang();
    }

    @Override
    public List<GioHang> getAllByTrangThai1() {
        return gioHangRepository.getAllByTrangThai1();
    }

    @Override
    public boolean delete(String id) {
        return gioHangRepository.delete(id);
    }

    @Override
    public GioHang getOneById(String id) {
        return gioHangRepository.getOneById(id);
    }

}
