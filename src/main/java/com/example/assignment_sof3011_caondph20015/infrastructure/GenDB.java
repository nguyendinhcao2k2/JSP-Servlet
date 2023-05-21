
package com.example.assignment_sof3011_caondph20015.infrastructure;

import com.example.assignment_sof3011_caondph20015.entity.ChiTietSanPham;
import com.example.assignment_sof3011_caondph20015.entity.DongSanPham;
import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.entity.MauSac;
import com.example.assignment_sof3011_caondph20015.entity.NhaSanXuat;
import com.example.assignment_sof3011_caondph20015.entity.SanPham;
import com.example.assignment_sof3011_caondph20015.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
public class GenDB {

    //Tạo DB trong SQL SERVER = SOFT2041_PTPM
    //Sau đó tiến hành chạy đển zen bảng
    public static void main(String[] args) {

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction trans = session.beginTransaction();

            //Gen Nha San Xuat
            NhaSanXuat nsx1 = new NhaSanXuat();
            nsx1.setMa("NSX001");
            nsx1.setTen("Anta");
            session.save(nsx1);

            NhaSanXuat nsx2 = new NhaSanXuat();
            nsx2.setMa("NSX002");
            nsx2.setTen("Addidas");
            session.save(nsx2);

            NhaSanXuat nsx3 = new NhaSanXuat();
            nsx3.setMa("NSX003");
            nsx3.setTen("Li-Ning");
            session.save(nsx3);

            //Gen San Pham
            SanPham sp1 = new SanPham();
            sp1.setMa("SP001");
            sp1.setTen("Giày bóng rổ nam SHOCK THE GAME Anta 812231106-2");
            session.save(sp1);

            SanPham sp2 = new SanPham();
            sp2.setMa("SP002");
            sp2.setTen("Giày bóng rổ nam SHOCK THE GAME Anta 812231106-1");
            session.save(sp2);

            SanPham sp3 = new SanPham();
            sp3.setMa("SP003");
            sp3.setTen("GIÀY CHẠY BỘ EQ21");
            session.save(sp3);

            SanPham sp4 = new SanPham();
            sp4.setMa("SP004");
            sp4.setTen("ULTRABOOST LIGHT SHOES");
            session.save(sp4);

            SanPham sp5 = new SanPham();
            sp5.setMa("SP005");
            sp5.setTen("Giày bóng rổ nam ABFS001-4");
            session.save(sp5);

            SanPham sp6 = new SanPham();
            sp6.setMa("SP006");
            sp6.setTen("Giày bóng rổ nam ABAS057-6");
            session.save(sp6);

            //  Gen Dong SP
            DongSanPham dongSP = new DongSanPham();
            dongSP.setMa("DSP001");
            dongSP.setTen("Giày");
            session.save(dongSP);

            //Gen Màu Sắc
            MauSac ms1 = new MauSac();
            ms1.setMa("MS001");
            ms1.setTen("Red");
            session.save(ms1);

            MauSac ms2 = new MauSac();
            ms2.setMa("MS002");
            ms2.setTen("Green");
            session.save(ms2);

            MauSac ms3 = new MauSac();
            ms3.setMa("MS003");
            ms3.setTen("Black");
            session.save(ms3);

            MauSac ms4 = new MauSac();
            ms4.setMa("MS004");
            ms4.setTen("White");
            session.save(ms4);

            //Gen Chi Tiet SP
            ChiTietSanPham ctsp1 = new ChiTietSanPham();
            ctsp1.setImg("anh_anta_1.webp");
            ctsp1.setGiaBan(BigDecimal.valueOf(100));
            ctsp1.setGiaNhap(BigDecimal.valueOf(80));
            ctsp1.setMoTa("Liên kết đế rỗng từ vật liệu Mars Foam cho phép giày bóng rổ Anta Shock The Game mới nhẹ bẫng như bọt. Phản hồi đôi chân nhạy hơn sau các cú bật nhảy nhờ vô số khoảng trống li ti nhiệm màu. TPU chống lật kéo dài gần full đế, nay tăng cường đai bảo vệ, hạn chế tối đa trật trẹo khớp chân.\n" +
                    "\n" +
                    "Cộng hưởng cùng các chiến binh để lên sân với 200% sức mạnh, Shock The Game - Sàn bê tông càng cứng, chiến thắng càng lẫy lừng!");
            ctsp1.setNamBH(2);
            ctsp1.setSoLuongTon(20);
            ctsp1.setDongSanPham(dongSP);
            ctsp1.setMauSac(ms1);
            ctsp1.setNhaSanXuat(nsx1);
            ctsp1.setTenSP("Giày bóng rổ nam ABFS001-4");
            session.save(ctsp1);

            ChiTietSanPham ctsp2 = new ChiTietSanPham();
            ctsp2.setImg("anh_anta_2.webp");
            ctsp2.setGiaBan(BigDecimal.valueOf(120));
            ctsp2.setGiaNhap(BigDecimal.valueOf(90));
            ctsp2.setMoTa("Giày chạy thể thao nam A-FLOW Anta 812245571-7 sở hữu thiết kế thời trang, năng động, cùng chất liệu bền bỉ hỗ trợ vận động tốt và bảo vệ đôi chân cho người mang. \n" +
                    "\n" +
                    "- Đế cao su mềm, êm ái giúp bạn cảm thấy dễ chịu khi di chuyển trong thời gian dài. \n" +
                    "\n" +
                    "- Đồng hành cùng thiết kế thời trang, giày thể thao Anta mang tính năng thoáng khí, giúp cân bằng nhiệt và độ ẩm trong những điều kiện môi trường khác nhau, đế có các đường rãnh chống trơn trượt. ");
            ctsp2.setNamBH(2);
            ctsp2.setSoLuongTon(30);
            ctsp2.setDongSanPham(dongSP);
            ctsp2.setMauSac(ms3);
            ctsp2.setNhaSanXuat(nsx1);
            ctsp2.setTenSP("Giày thời trang nam A-JELLY Anta 812318881-5");
            session.save(ctsp2);

            ChiTietSanPham ctsp3 = new ChiTietSanPham();
            ctsp3.setImg("addidas1.webp");
            ctsp3.setGiaBan(BigDecimal.valueOf(90));
            ctsp3.setGiaNhap(BigDecimal.valueOf(70));
            ctsp3.setMoTa("Chinh phục các bài tập sức mạnh và tập gym không chút do dự. Đôi giày adidas này giúp bạn nâng tầm cuộc chơi. Đế giữa dropset tạo cảm giác tiếp xúc đất, hỗ trợ bạn tập luyện đúng tư thế và tiếp thêm tự tin trong từng động tác. Hai lớp đệm EVA khác nhau mang tới độ ổn định ở gót chân đồng thời tạo cảm giác thoải mái ở mũi chân.\n ");
            ctsp3.setNamBH(2);
            ctsp3.setSoLuongTon(30);
            ctsp3.setDongSanPham(dongSP);
            ctsp3.setMauSac(ms3);
            ctsp3.setNhaSanXuat(nsx2);
            ctsp3.setTenSP("Giày thời trang nam X-Game Shoes Anta 812318057-2");
            session.save(ctsp3);

            ChiTietSanPham ctsp4 = new ChiTietSanPham();
            ctsp4.setImg("addidas2.webp");
            ctsp4.setGiaBan(BigDecimal.valueOf(89));
            ctsp4.setGiaNhap(BigDecimal.valueOf(45));
            ctsp4.setMoTa("Vẻ đẹp kinh điển. Phong cách vốn dĩ. Đa năng hàng ngày. Suốt hơn 50 năm qua và chưa dừng ở đó, giày adidas Stan Smith luôn giữ vững vị trí " +
                    "là một biểu tượng. Đôi giày này là phiên bản cải biên mới mẻ, là một phần cam kết của adidas hướng tới chỉ sử dụng polyester tái chế bắt đầu từ năm 2024." +
                    " Với thân giày vegan và đế ngoài làm từ cao su phế liệu, đôi giày này vẫn mang phong cách đầy tính biểu tượng, đồng thời thân thiện với môi trường.");
            ctsp4.setNamBH(2);
            ctsp4.setSoLuongTon(20);
            ctsp4.setDongSanPham(dongSP);
            ctsp4.setMauSac(ms4);
            ctsp4.setNhaSanXuat(nsx2);
            ctsp4.setTenSP("GIÀY ULTRABOOST LIGHT");
            session.save(ctsp4);

            ChiTietSanPham ctsp5 = new ChiTietSanPham();
            ctsp5.setImg("lining2.webp");
            ctsp5.setGiaBan(BigDecimal.valueOf(89));
            ctsp5.setGiaNhap(BigDecimal.valueOf(45));
            ctsp5.setMoTa("Công nghệ đế giữa của LIGHT FOAM của Li Ning sử dụng chất đồng trùng hợp olefin hiệu suất cao để thay thế EVA truyền thống, đồng thời được trang bị hệ " +
                    "thống tạo cầu và tạo bọt polymer do Li Ning tự phát triển, có thể làm cho các lỗ tạo bọt mịn và đồng đều hơn, do đó giảm mật độ. Giảm trọng lượng của đế giữa giúp cải thiện đáng kể độ đàn hồi của đáy.");
            ctsp5.setNamBH(2);
            ctsp5.setSoLuongTon(20);
            ctsp5.setDongSanPham(dongSP);
            ctsp5.setMauSac(ms2);
            ctsp5.setNhaSanXuat(nsx3);
            ctsp5.setTenSP("GIÀY SUPERSTAR " + "CLOUD WHITE / CORE BLACK / CLOUD WHITE");
            session.save(ctsp5);

            ChiTietSanPham ctsp6 = new ChiTietSanPham();
            ctsp6.setImg("li_ning1.webp");
            ctsp6.setGiaBan(BigDecimal.valueOf(100));
            ctsp6.setGiaNhap(BigDecimal.valueOf(50));
            ctsp6.setMoTa("Công nghệ LI-NING BOOM là vật liệu đế giữa kiểu mới do Li-Ning tự khai thác phát triển, được điều chế bằng quy trình tạo bọt lỏng siêu giới hạn, so với chất liệu truyền thống thì chất " +
                    "liệu này giảm được đáng kể trọng lượng đế giữa, không chỉ giúp tính năng đàn hồi được phát huy rõ rệt, mà còn kéo dài được tuổi thọ sử dụng, tăng cường thêm tính năng của sản phẩm. Thông qua " +
                    "các điều chỉnh sát với mục tiêu tính năng và chi tiết hơn, " +
                    "để phù với tính chất cạnh tranh của các môn thể thao khác nhau như chạy, bóng rổ, cầu lông, bóng bàn, v.v….nâng cao hiệu suất tốt cho các vận động viên với các mục mục đích sử dụng khác nhau.");
            ctsp6.setNamBH(1);
            ctsp6.setSoLuongTon(100);
            ctsp6.setDongSanPham(dongSP);
            ctsp6.setMauSac(ms1);
            ctsp6.setNhaSanXuat(nsx3);
            ctsp6.setTenSP("GIÀY AVRYN");
            session.save(ctsp6);

            //Gen Khach Hang
            Account kh1 = new Account();
            kh1.setHoTen("Nguyễn Đình Long");
            kh1.setMa("KH001");
            kh1.setMatKhau("123456");
            kh1.setSdt("0947654321");
            kh1.setRole("ADMIN");
            session.save(kh1);

            Account kh2 = new Account();
            kh2.setHoTen("Nguyễn Văn B");
            kh2.setMa("KH002");
            kh2.setMatKhau("123456");
            kh2.setSdt("0943232913");
            kh2.setRole("USER");
            session.save(kh2);

            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
