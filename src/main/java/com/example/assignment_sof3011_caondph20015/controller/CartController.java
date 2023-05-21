package com.example.assignment_sof3011_caondph20015.controller;

/**
 * @author caodinh
 */

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.service.IChiTietGioHangService;
import com.example.assignment_sof3011_caondph20015.service.IGioHangService;
import com.example.assignment_sof3011_caondph20015.service.impl.ChiTietGioHangServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.GioHangServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "cart-controller", value = {
        "/cart/show-cart",
        "/cart/add-to-cart",
        "/cart/buy-now",
        "/cart/remove-cart",
        "/cart/update-cart",
        "/cart/check-out-cart"
})
public class CartController extends HttpServlet {

    private IChiTietGioHangService chiTietGioHangService = new ChiTietGioHangServiceImpl();
    private IGioHangService gioHangService = new GioHangServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("cart/show-cart")) {
            showCart(req, resp);
        } else if (uri.contains("cart/remove-cart")) {
            removeCart(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("cart/add-to-cart")) {
            addToCart(req, resp);
        } else if (uri.contains("cart/update-cart")) {
            updateCart(req, resp);
        } else if (uri.contains("cart/check-out-cart")) {
            checkOut(req, resp);
        } else if (uri.contains("cart/buy-now")) {
            buyNow(req, resp);
        }
    }

    private void buyNow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        GioHang gioHang = (GioHang) session.getAttribute("gh");
        gioHangService.buyNow(session, gioHang, id, req, resp);
    }

    public void removeCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idCart = req.getParameter("idCart");
        HttpSession session = req.getSession();
        GioHang gioHang = (GioHang) session.getAttribute("gh");
        gioHangService.removeCart(idCart, session, gioHang, resp);
    }

    public void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GioHang gioHang = (GioHang) session.getAttribute("gh");
        gioHangService.showCart(session, gioHang, req, resp);

    }

    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        GioHang gioHang = (GioHang) session.getAttribute("gh");
        gioHangService.addToCart(session, gioHang, id, req, resp);
    }

    public void checkOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String diaChi = req.getParameter("diaChi");
        String sdt = req.getParameter("sdt");
        String tenNguoiNhan = req.getParameter("tenNguoiNhan");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("user");
        gioHangService.checkOut(diaChi, sdt, tenNguoiNhan, session, account);
    }

    public void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String soLuong = req.getParameter("soLuong");
        HttpSession session = req.getSession();
        GioHang gioHang = (GioHang) session.getAttribute("gh");
        gioHangService.update(id, soLuong, session, gioHang, resp);
    }
}
