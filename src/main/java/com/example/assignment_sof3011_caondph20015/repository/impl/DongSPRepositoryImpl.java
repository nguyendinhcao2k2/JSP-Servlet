package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.DongSanPham;
import com.example.assignment_sof3011_caondph20015.repository.IDongSanPhamRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;


/**
 * @author caodinh
 */
public class DongSPRepositoryImpl implements IDongSanPhamRepository {

    @Override
    public DongSanPham getOne() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From DongSanPham dsp";
            Query q = session.createQuery(sql);
            return (DongSanPham) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
