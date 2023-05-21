package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.MauSac;
import com.example.assignment_sof3011_caondph20015.repository.IMauSacRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author caodinh
 */
public class MauSacRepositoryImpl implements IMauSacRepository {
    @Override
    public List<MauSac> findAll() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM MauSac ");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MauSac getOne(String ma) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From MauSac ms where ms.ma =: ma ";
            Query q = session.createQuery(sql);
            q.setParameter("ma", ma);
            return (MauSac) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
