package com.example.assignment_sof3011_caondph20015.repository.impl;
import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;
import com.example.assignment_sof3011_caondph20015.repository.IChiTietGioHangRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

/**
 * @author caodinh
 */
public class ChiTietGioHangRepositoryImpl implements IChiTietGioHangRepository {

    @Override
    public boolean save(GioHangChiTiet gioHangChiTiet) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(gioHangChiTiet);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public List<GioHangChiTiet> getAllByIDGioHang(String idGh) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From GioHangChiTiet ghct where ghct.gioHang.id = :id and  ghct.gioHang.tinhTrang = 0 ";
            Query q = session.createQuery(sql);
            q.setParameter("id", idGh);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<GioHangChiTiet> getAllByIDGioHang1(String idGh) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From GioHangChiTiet ghct where ghct.gioHang.id = :id and  ghct.gioHang.tinhTrang = 1 ";
            Query q = session.createQuery(sql);
            q.setParameter("id", idGh);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public GioHangChiTiet getOneByIDSanPham(String idSP) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<GioHangChiTiet> query = session.createQuery("FROM GioHangChiTiet ghct WHERE ghct.chiTietSP.id = :id", GioHangChiTiet.class);
            query.setParameter("id", idSP);
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
    public GioHangChiTiet getOneById(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<GioHangChiTiet> query = session.createQuery("FROM GioHangChiTiet ghct WHERE ghct.id = :id ", GioHangChiTiet.class);
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

    @Override
    public boolean delete(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "DELETE FROM GioHangChiTiet WHERE id = :id";
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
