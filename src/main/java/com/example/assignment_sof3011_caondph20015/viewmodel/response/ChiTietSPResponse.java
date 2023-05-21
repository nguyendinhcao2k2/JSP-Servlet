package com.example.assignment_sof3011_caondph20015.viewmodel.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSPResponse {

    private String id;

    private String tenSP;

    private String nhaSanXuat;

    private String mauSac;

    private String moTa;

    private String Img;

    private Integer soLuongTon;

    private BigDecimal giaBan;
}
