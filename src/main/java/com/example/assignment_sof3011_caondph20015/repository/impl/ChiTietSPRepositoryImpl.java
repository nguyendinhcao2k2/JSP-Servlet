package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.repository.IChiTietSPRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author caodinh
 */
public class ChiTietSPRepositoryImpl implements IChiTietSPRepository {
    @Override
    public List<ChiTietSanPham> getTop5() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From ChiTietSanPham ct ";
            Query q = session.createQuery(sql).setMaxResults(5);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    @Override
    public List<ChiTietSanPham> getAll() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From ChiTietSanPham ct ";
            Query q = session.createQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public ChiTietSanPham getOne(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From ChiTietSanPham ct where ct.id = :id";
            Query q = session.createQuery(sql);
            q.setParameter("id", id);
            return (ChiTietSanPham) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<ChiTietSanPham> getSanPhamByMaNSX(String ma) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From ChiTietSanPham ct where ct.nhaSanXuat.ma = :ma";
            Query q = session.createQuery(sql);
            q.setParameter("ma", ma);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean save(ChiTietSanPham chiTietSP) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(chiTietSP);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "DELETE FROM ChiTietSanPham WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            session.getTransaction().begin();
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
