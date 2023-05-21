package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.MauSac;

import java.util.List;

/**
 * @author caodinh
 */
public interface IMauSacRepository {
    List<MauSac> findAll();

    MauSac getOne(String ma);

}
