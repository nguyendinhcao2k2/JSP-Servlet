package com.example.assignment_sof3011_caondph20015.controller; /**
 * @author caodinh
 */

import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.service.IChiTietGioHangService;
import com.example.assignment_sof3011_caondph20015.service.IGioHangService;
import com.example.assignment_sof3011_caondph20015.service.impl.ChiTietGioHangServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.GioHangServiceImpl;
import com.example.assignment_sof3011_caondph20015.viewmodel.response.ChiTietGioHangResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuanLyHoaDonController", value = {
        "/admin/quan-ly-hoa-don",
        "/admin/delete-hoa-don",
        "/admin/xac-nhan-hoa-don",
        "/admin/detail-gio-hang"
})
public class QuanLyHoaDonController extends HttpServlet {
    private IGioHangService gioHangService = new GioHangServiceImpl();

    private IChiTietGioHangService chiTietGioHangService = new ChiTietGioHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("admin/quan-ly-hoa-don")) {
            getAllHoaDon(req, resp);
        } else if (uri.contains("admin/delete-hoa-don")) {
            deleteGioHang(req, resp);
        } else if (uri.contains("admin/xac-nhan-hoa-don")) {
            xacNhanHoaDon(req, resp);
        } else if (uri.contains("admin/detail-gio-hang")) {
            detailGioHang(req, resp);
        }
    }

    private void detailGioHang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idGh = req.getParameter("idGH");
        List<ChiTietGioHangResponse> list = chiTietGioHangService.getAllByIDGioHang1(idGh);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        // Thiết lập header và gửi dữ liệu về cho client
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    private void xacNhanHoaDon(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("idGH");
        GioHang gioHang = gioHangService.getOneById(id);
        gioHang.setTinhTrang(2);
        gioHangService.save(gioHang);
        resp.sendRedirect("/admin/quan-ly-hoa-don");
    }

    private void deleteGioHang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("idGH");
        System.out.println("Hi");
        gioHangService.delete(id);
        resp.sendRedirect("/admin/quan-ly-hoa-don");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void getAllHoaDon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GioHang> gioHangList = gioHangService.getAllByTrangThai1();
        req.setAttribute("listGioHang", gioHangList);
        req.getRequestDispatcher("/view/quanlydonhang.jsp").forward(req, resp);
    }

}
