package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.MauSac;

import java.util.List;

/**
 * @author caodinh
 */
public interface IMauSacService {

    List<MauSac> getAll();

    MauSac getOne(String ma);

}
