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


/**
 * @author caodinh
 */
@Entity
@Table(name = "CuaHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuaHang {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "Ma", unique = true, length = 20)
    private String ma;

    @Column(name = "Ten", length = 50, columnDefinition = "nvarchar(Max)")
    private String ten;

    @Column(name = "DiaChi", length = 100, columnDefinition = "nvarchar(Max)")
    private String diaChi;

    @Column(name = "ThanhPho", length = 50, columnDefinition = "nvarchar(Max)")
    private String thanhPho;

    @Column(name = "QuocGia", length = 50, columnDefinition = "nvarchar(Max)")
    private String quocGia;

}