package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;
import com.example.assignment_sof3011_caondph20015.repository.INhaSanXuatRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author caodinh
 */
public class NhaSanXuatRepositoryImpl implements INhaSanXuatRepository {
    @Override
    public List<NhaSanXuat> getAll() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From NhaSanXuat nsx ";
            Query q = session.createQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public NhaSanXuat getOneByMa(String ma) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From NhaSanXuat nsx where nsx.ma =: ma ";
            Query q = session.createQuery(sql);
            q.setParameter("ma",ma);
            return (NhaSanXuat) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
