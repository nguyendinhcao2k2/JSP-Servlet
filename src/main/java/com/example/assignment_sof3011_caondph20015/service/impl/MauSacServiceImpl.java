package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.MauSac;
import com.example.assignment_sof3011_caondph20015.repository.IMauSacRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.MauSacRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.IMauSacService;

import java.util.List;

/**
 * @author caodinh
 */
public class MauSacServiceImpl implements IMauSacService {
    private IMauSacRepository mauSacRepository = new MauSacRepositoryImpl();

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getOne(String ma) {
        return mauSacRepository.getOne(ma);
    }

}
