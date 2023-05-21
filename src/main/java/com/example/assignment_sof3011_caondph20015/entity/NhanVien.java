package com.example.assignment_sof3011_caondph20015.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author caodinh
 */
@Entity
@Table(name = "NhanVien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhanVien {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "Ma", unique = true, length = 20)
    private String ma;

    @Column(name = "Ten", length = 30, columnDefinition = "nvarchar(Max)")
    private String ten;

    @Column(name = "HoTen", length = 30, columnDefinition = "nvarchar(Max)")
    private String hoTen;

    @Column(name = "GioiTinh", length = 10, columnDefinition = "nvarchar(Max)")
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi", length = 100, columnDefinition = "nvarchar(Max)")
    private String diaChi;

    @Column(name = "Sdt", length = 30)
    private String sdt;

    @Column(name = "MatKhau", length = 65535, columnDefinition = "TEXT")
    private String matKhau;

    @Column(name = "IdGuiBC")
    private UUID idGuiBC;

    @Column(name = "TrangThai")
    private int trangThai;

}