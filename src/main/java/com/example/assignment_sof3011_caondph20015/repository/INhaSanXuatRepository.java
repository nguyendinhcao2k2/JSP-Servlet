package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;

import java.util.List;

/**
 * @author caodinh
 */
public interface INhaSanXuatRepository {

    List<NhaSanXuat> getAll();

    NhaSanXuat getOneByMa(String ma);

}
