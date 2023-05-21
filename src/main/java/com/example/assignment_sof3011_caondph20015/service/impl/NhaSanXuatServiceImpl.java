package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;
import com.example.assignment_sof3011_caondph20015.repository.INhaSanXuatRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.NhaSanXuatRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.INhaSanXuatService;

import java.util.List;

/**
 * @author caodinh
 */
public class NhaSanXuatServiceImpl implements INhaSanXuatService {
    private INhaSanXuatRepository nhaSanXuatService = new NhaSanXuatRepositoryImpl();

    @Override
    public List<NhaSanXuat> getAll() {
        return nhaSanXuatService.getAll();
    }

    @Override
    public NhaSanXuat getOne(String ma) {
        return nhaSanXuatService.getOneByMa(ma);
    }

}
