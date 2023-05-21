package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;

import java.util.List;

/**
 * @author caodinh
 */
public interface INhaSanXuatService {

    List<NhaSanXuat> getAll();

    NhaSanXuat getOne(String ma);

}
