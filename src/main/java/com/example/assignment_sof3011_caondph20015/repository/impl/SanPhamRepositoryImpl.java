package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.SanPham;
import com.example.assignment_sof3011_caondph20015.repository.ISanPhamRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author caodinh
 */
public  class SanPhamRepositoryImpl implements ISanPhamRepository {

    private final SessionFactory sessionFactory;

    public SanPhamRepositoryImpl() {
        this.sessionFactory = HibernateConfig.getFACTORY();
    }

    @Override
    public List<SanPham> findAll() {
        List<SanPham> listSanPham;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM SanPham");
            listSanPham = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSanPham;
    }

    @Override
    public boolean save(SanPham sp) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String genMaSanPham() {
        String maSP = "";
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "Select MAX(CONVERT(INT,SUBSTRING(ma,5,100))) from SanPham ";
            NativeQuery query = session.createNativeQuery(hql);
            maSP = query.getSingleResult().toString();
        } catch (Exception e) {

        }
        if (maSP == "") {
            maSP = "1";
            int ma = Integer.valueOf(maSP);
            maSP = "SP00" + ma;
            return maSP;
        }
        int ma = Integer.valueOf(maSP);
        ma++;
        maSP = "SP00" + ma;
        return maSP;
    }

    @Override
    public SanPham getSPBYName(String name) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From SanPham sp where sp.ten = :name";
            Query q = session.createQuery(sql);
            q.setParameter("name",name);
            return (SanPham) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
