package com.example.assignment_sof3011_caondph20015.viewmodel;

import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
@NoArgsConstructor
@Getter
@Setter
public class QLChiTietSanPham {
    private String img;
    private String ten;
    private String moTa;
    private String giaBan;
    private String soLuongTon;
    private String mauSac;
    private String nsx;

    @Override
    public String toString() {
        return "QLChiTietSanPham{" +
                "img='" + img + '\'' +
                ", ten='" + ten + '\'' +
                ", moTa='" + moTa + '\'' +
                ", giaBan=" + giaBan +
                ", soLuongTon=" + soLuongTon +
                ", mauSac='" + mauSac + '\'' +
                ", nsx='" + nsx + '\'' +
                '}';
    }
}
