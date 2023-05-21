package com.example.assignment_sof3011_caondph20015.util;

import java.util.Properties;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.entity.ChucVu;
import com.example.assignment_sof3011_caondph20015.entity.CuaHang;
import com.example.assignment_sof3011_caondph20015.entity.DongSanPham;
import com.example.assignment_sof3011_caondph20015.entity.GioHang;
import com.example.assignment_sof3011_caondph20015.entity.GioHangChiTiet;
import com.example.assignment_sof3011_caondph20015.entity.HoaDon;
import com.example.assignment_sof3011_caondph20015.entity.HoaDonChiTiet;
import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.entity.MauSac;
import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;
import com.example.assignment_sof3011_caondph20015.entity.NhanVien;
import com.example.assignment_sof3011_caondph20015.entity.SanPham;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * @author caodinh
 */
public class HibernateConfig {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=Assigment_Java4");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "12332123asd");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "create");
        properties.put(Environment.HBM2DDL_AUTO, "none");

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(DongSanPham.class);
        conf.addAnnotatedClass(GioHang.class);
        conf.addAnnotatedClass(GioHangChiTiet.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(Account.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(NhaSanXuat.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
