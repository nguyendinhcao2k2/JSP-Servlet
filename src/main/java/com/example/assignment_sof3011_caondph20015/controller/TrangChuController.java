package com.example.assignment_sof3011_caondph20015.controller; /**
 * @author caodinh
 */

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.service.IChiTietGioHangService;
import com.example.assignment_sof3011_caondph20015.service.IChiTietSPService;
import com.example.assignment_sof3011_caondph20015.service.IGioHangService;
import com.example.assignment_sof3011_caondph20015.service.IMauSacService;
import com.example.assignment_sof3011_caondph20015.service.INhaSanXuatService;
import com.example.assignment_sof3011_caondph20015.service.ISanPhamService;
import com.example.assignment_sof3011_caondph20015.service.impl.ChiTietGioHangServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.ChiTietSanPhamServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.GioHangServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.MauSacServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.NhaSanXuatServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.SanPhamServiceImpl;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLChiTietSanPhamErrorMessage;
import com.example.assignment_sof3011_caondph20015.viewmodel.response.ChiTietSPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@MultipartConfig
@WebServlet(name = "TrangChuServlet", value = {
        "/trang-chu",
        "/san-pham",
        "/admin/add",
        "/admin/delete",
        "/admin/quan-ly",
        "/category",
        "/chi-tiet",
        "/admin/detail",
        "/admin/update"
})
public class TrangChuController extends HttpServlet {

    private IChiTietSPService chiTietSPService = new ChiTietSanPhamServiceImpl();
    private INhaSanXuatService nhaSanXuatService = new NhaSanXuatServiceImpl();
    private IMauSacService mauSacService = new MauSacServiceImpl();
    private ISanPhamService sanPhamService = new SanPhamServiceImpl();

    private IChiTietGioHangService chiTietGioHangService = new ChiTietGioHangServiceImpl();
    private IGioHangService gioHangService = new GioHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("trang-chu")) {
            HttpSession session = request.getSession();
            GioHang gioHang = (GioHang) session.getAttribute("gh");
            gioHangService.addCart(session, gioHang);
            request.setAttribute("chiTietSanPhams", chiTietSPService.getTop5());
            request.getRequestDispatcher("/view/trangchu.jsp").forward(request, response);
        } else if (uri.contains("san-pham")) {
            List<ChiTietSanPham> chiTietSPS = chiTietSPService.getAll();
            request.setAttribute("sp", chiTietSPS.size() != 0 ? chiTietSPS.get(0) : new ChiTietSanPham());
            request.setAttribute("chiTietSanPhams", chiTietSPS);
            request.setAttribute("nsx", nhaSanXuatService.getAll());
            request.getRequestDispatcher("/view/sanpham.jsp").forward(request, response);

        } else if (uri.contains("chi-tiet")) {
            String id = request.getParameter("id");
            chiTietSanPham(id, request, response);
        } else if (uri.contains("category")) {
            String ma = request.getParameter("ma");
            showProductByNSX(ma, request, response);
        } else if (uri.contains("admin/quan-ly")) {
            quanLySP(request, response);
        } else if (uri.contains("admin/delete")) {
            String id = request.getParameter("id");
            delete(id, request, response);
        } else if (uri.contains("admin/detail")) {
            detailProdct(request, response);
        }
    }


    private void detailProdct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSP = request.getParameter("id");
        ChiTietSanPham chiTietSP = chiTietSPService.getOne(idSP);
        ChiTietSPResponse chiTietSPResponse = new ChiTietSPResponse();
        chiTietSPResponse.setId(chiTietSP.getId());
        chiTietSPResponse.setTenSP(chiTietSP.getTenSP());
        chiTietSPResponse.setNhaSanXuat(chiTietSP.getNhaSanXuat().getMa());
        chiTietSPResponse.setMauSac(chiTietSP.getMauSac().getMa());
        chiTietSPResponse.setGiaBan(chiTietSP.getGiaBan());
        chiTietSPResponse.setImg(chiTietSP.getImg());
        chiTietSPResponse.setMoTa(chiTietSP.getMoTa());
        chiTietSPResponse.setSoLuongTon(chiTietSP.getSoLuongTon());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(chiTietSPResponse);
        // Thiết lập header và gửi dữ liệu về cho client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void chiTietSanPham(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
        request.setAttribute("ctsp", chiTietSP);
        request.getRequestDispatcher("/view/detail_product.jsp").forward(request, response);
    }

    public void showProductByNSX(String ma, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChiTietSanPham> chiTietSPS = chiTietSPService.getSanPhamByMaNSX(ma);
        request.setAttribute("sp", chiTietSPS.get(0));
        request.setAttribute("chiTietSanPhams", chiTietSPS);
        request.setAttribute("nsx", nhaSanXuatService.getAll());
        request.getRequestDispatcher("/view/sanpham.jsp").forward(request, response);
    }

    public void quanLySP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChiTietSanPham> chiTietSPS = chiTietSPService.getAll();
        request.setAttribute("sanPhams", sanPhamService.getAll());
        request.setAttribute("mauSacs", mauSacService.getAll());
        request.setAttribute("nsx", nhaSanXuatService.getAll());
        request.setAttribute("chiTietSanPhams", chiTietSPS);
        request.getRequestDispatcher("/view/quanlysanpham.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("admin/add")) {
            add(request, response);
        } else if (uri.contains("admin/update")) {
            update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("img"); // Lấy đối tượng Part của file đã tải lên
        String fileName = filePart.getSubmittedFileName(); // Lấy tên file
        String tenSP = request.getParameter("tenSP");
        String giaBan = request.getParameter("giaBan");
        String moTa = request.getParameter("moTa");
        String soLuong = request.getParameter("soLuong");
        String mauSac = request.getParameter("mauSac");
        String nsx = request.getParameter("nsx");
        String id = request.getParameter("id");

        ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
        String oldImg = chiTietSP.getImg();
        chiTietSP.setImg(fileName);
        chiTietSP.setTenSP(tenSP);
        chiTietSP.setGiaBan(BigDecimal.valueOf(Double.valueOf(giaBan)));
        chiTietSP.setMoTa(moTa);
        chiTietSP.setSoLuongTon(Integer.valueOf(soLuong));
        chiTietSP.setMauSac(mauSacService.getOne(mauSac));
        chiTietSP.setNhaSanXuat(nhaSanXuatService.getOne(nsx));
        String appPath = getServletContext().getRealPath("/img") + "\\" + fileName;
        String oldPath = getServletContext().getRealPath("/img") + "\\" + oldImg;
        File imageFile = new File(oldPath);
        if (chiTietSPService.update(chiTietSP)) {
            filePart.write(appPath);
            imageFile.delete();
            // Thiết lập header và gửi dữ liệu về cho client
            response.setStatus(HttpServletResponse.SC_OK); // Trả về mã trạng thái 200
            response.setContentType("text/plain");
            request.setCharacterEncoding("UTF-8");
            response.getWriter().write("Update data successfully!");
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("image"); // Lấy đối tượng Part của file đã tải lên
            String fileName = filePart.getSubmittedFileName(); // Lấy tên file

            String appPath = getServletContext().getRealPath("/img") + "\\" + fileName;
            QLChiTietSanPham qlChiTietSanPham = new QLChiTietSanPham();
            qlChiTietSanPham.setImg(fileName);
            BeanUtils.populate(qlChiTietSanPham, request.getParameterMap());
            QLChiTietSanPhamErrorMessage errorMessage = new QLChiTietSanPhamErrorMessage();
            boolean check = chiTietSPService.save(qlChiTietSanPham, errorMessage);
            if (check == true) {
                filePart.write(appPath);
                response.sendRedirect("/admin/quan-ly");
            } else {
                List<ChiTietSanPham> chiTietSPS = chiTietSPService.getAll();
                request.setAttribute("sanPhams", sanPhamService.getAll());
                request.setAttribute("mauSacs", mauSacService.getAll());
                request.setAttribute("nsx", nhaSanXuatService.getAll());
                request.setAttribute("chiTietSanPhams", chiTietSPS);
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/view/quanlysanpham.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void delete(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChiTietSanPham chiTietSP = chiTietSPService.getOne(id);
        String path = getServletContext().getRealPath("/img") + "\\" + chiTietSP.getImg();
        File imageFile = new File(path);
        if (chiTietSPService.delete(id)) {
            if (imageFile.delete()) {
                System.out.println("Tệp tin ảnh đã bị xóa thành công.");
            } else {
                System.out.println("Không thể xóa tệp tin ảnh.");
            }
            response.sendRedirect("/admin/quan-ly");
        }
    }
}
