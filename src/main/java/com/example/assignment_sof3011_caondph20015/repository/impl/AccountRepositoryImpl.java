package com.example.assignment_sof3011_caondph20015.repository.impl;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.repository.IAccountRepository;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;


/**
 * @author caodinh
 */
public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public boolean saveAccountByLoginGoogle(Account account) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public Account getOneByEmail(String email) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<Account> query = session.createQuery("FROM Account acc WHERE acc.email = :email", Account.class);
            query.setParameter("email", email);
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
    public Account getOneBySDTAndPassword(String sdt, String password) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<Account> query = session.createQuery("FROM Account acc WHERE acc.sdt = :sdt AND acc.matKhau = :password", Account.class);
            query.setParameter("sdt", sdt);
            query.setParameter("password", password);
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
    public String genMaAccount() {
        String maAC = "";
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String hql = "Select MAX(CONVERT(INT,SUBSTRING(ma,5,100))) from Account ";
            NativeQuery query = session.createNativeQuery(hql);
            maAC = query.getSingleResult().toString();
        } catch (Exception e) {

        }
        if (maAC == "") {
            maAC = "1";
            int ma = Integer.valueOf(maAC);
            maAC = "AC00" + ma;
            return maAC;
        }
        int ma = Integer.valueOf(maAC);
        ma++;
        maAC = "AC00" + ma;
        return maAC;
    }

    @Override
    public boolean saveOrUpdate(Account account) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(account);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getOneBySDT(String sdt) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            String sql = "From Account acc where acc.sdt = :sdt";
            Query q = session.createQuery(sql);
            q.setParameter("sdt", sdt);
            return (Account) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}

