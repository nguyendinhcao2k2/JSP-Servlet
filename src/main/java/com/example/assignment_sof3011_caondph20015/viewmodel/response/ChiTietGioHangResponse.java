package com.example.assignment_sof3011_caondph20015.viewmodel.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caodinh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietGioHangResponse {

    private String img;

    private String tenSP;

    private double totalPrice;

    private int soLuong;

}
