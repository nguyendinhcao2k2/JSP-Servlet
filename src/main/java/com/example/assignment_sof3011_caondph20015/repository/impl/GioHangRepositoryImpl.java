package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.repository.IGioHangRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author caodinh
 */
public class GioHangRepositoryImpl implements IGioHangRepository {
    @Override
    public GioHang getGioHangByKhachHangAndTinhTrang(String idKh) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<GioHang> query = session.createQuery("FROM GioHang gh WHERE gh.khachHang.id = :idKH AND gh.tinhTrang = :tinhTrang", GioHang.class);
            query.setParameter("idKH", idKh);
            query.setParameter("tinhTrang", 0);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Không tìm thấy bản ghi phù hợp
            return null;
        } catch (Exception e) {
            // Xử lý lỗi
            throw new RuntimeException("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean save(GioHang gioHang) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(gioHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String genMaGioHang() {
        String maGH = "";
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "Select MAX(CONVERT(INT,SUBSTRING(ma,5,100))) from GioHang ";
            NativeQuery query = session.createNativeQuery(hql);
            maGH = query.getSingleResult().toString();
        } catch (Exception e) {

        }
        if (maGH == "") {
            maGH = "1";
            int ma = Integer.valueOf(maGH);
            maGH = "GH00" + ma;
            return maGH;
        }
        int ma = Integer.valueOf(maGH);
        ma++;
        maGH = "GH00" + ma;
        return maGH;
    }

    @Override
    public List<GioHang> getAllByTrangThai1() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<GioHang> query = session.createQuery("FROM GioHang gh WHERE gh.tinhTrang = 1", GioHang.class);
            return query.getResultList();
        } catch (NoResultException e) {
            // Không tìm thấy bản ghi phù hợp
            return null;
        } catch (Exception e) {
            // Xử lý lỗi
            throw new RuntimeException("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "DELETE FROM GioHang gh WHERE gh.id = :id";
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

    @Override
    public GioHang getOneById(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<GioHang> query = session.createQuery("FROM GioHang gh WHERE gh.id = :id ", GioHang.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Không tìm thấy bản ghi phù hợp
            return null;
        } catch (Exception e) {
            // Xử lý lỗi
            throw new RuntimeException("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }

}
