package Application;

import DAOTest.*;
<<<<<<< HEAD
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.KhuyenMaiImpl;
import DAOTest.impl.LapHoaDonImpl;
import DAOTest.impl.SanPhamImpl;
import Entities.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
=======
import DAOTest.impl.*;
import Entities.KhachHang;
import Entities.KhuyenMai;
import Entities.LoaiKhachHang;
>>>>>>> a1d11186b578f60a750fcaa9c80c728250703de4


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class application {
//    public static void main(String[] args) {
//
//
//        ThongTinCaNhanDao thongTinCaNhanDao = new ThongTinCaNhanImpl();
//        System.out.println(thongTinCaNhanDao.mailNhanVien("NV001"));
//
//
//
//    }
public static void main(String[] args) {
    // Create an instance of the NhanVienImpl class
    NhanVienImpl nhanVienImpl = new NhanVienImpl();

    // Prepare test data
    String maNV = "testMaNV";
    String tenNV = "testTenNV";
    Date ngaySinh = Date.valueOf(LocalDate.now());
    String CCCD = "testCCCD";
    String sdt = "testSdt";
    int gend = 1;
    int sta = 1;
    int ca = 1;
    int chuc = 1;
    String hinh = "testHinh";
    String Email = "testEmail@test.com";
    String DiaChi = "testDiaChi";

<<<<<<< HEAD
        SanPhamDao Sp = new SanPhamImpl();
        System.out.println(Sp.getKMTheoTenOne("KM001").getTenKhuyenMai());

=======
    // Call the `them` method with the test data
    boolean result = nhanVienImpl.them(maNV, tenNV, ngaySinh, CCCD, sdt, gend, sta, ca, chuc, hinh, Email, DiaChi);
>>>>>>> a1d11186b578f60a750fcaa9c80c728250703de4

    // Print the result
    System.out.println("Result of `them` method: " + result);
}
}
