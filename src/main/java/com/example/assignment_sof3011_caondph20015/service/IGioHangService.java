package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @author caodinh
 */
public interface IGioHangService {

    void addCart(HttpSession session, GioHang gioHang);

    void buyNow(HttpSession session, GioHang gioHang, String id, HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void addToCart(HttpSession session, GioHang gioHang, String id, HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void checkOut(String diaChi, String sdt, String tenNguoiNhan, HttpSession session, Account account);

    void update(String id, String soLuong, HttpSession session, GioHang gioHang, HttpServletResponse resp) throws IOException;

    void removeCart(String id,HttpSession session,GioHang gioHang,HttpServletResponse resp) throws IOException;

    void showCart(HttpSession session, GioHang gioHang, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    boolean save(GioHang gioHang);

    String genMaGH();

    List<GioHang> getAllByTrangThai1();

    boolean delete(String id);

    GioHang getOneById(String id);

}
