package Application;

import DAOTest.KhachHangDao;
import DAOTest.LapHoaDonDao;
import DAOTest.NhanVienDao;
import DAOTest.XemHoaDonDao;
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.LapHoaDonImpl;
import DAOTest.impl.NhanVienImpl;
import Entities.KhachHang;
import Entities.LoaiKhachHang;
import Entities.NhanVien;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

//public class application {
//    public static void main(String[] args) {
//
//
//
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//        nhanVienDao.them("NV011", "Nguyen Van A", Date.valueOf("2000-01-01"), "123456789", "0123456789", 1, 1, 1, 1, "Hinh", "Email", "DiaChi");
//
//
//
//
//    }
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        // Existing NhanVien details
//        String maBanDau = "NV011";
//
//        // New NhanVien details
//        String maNV = "NV011";
//        String tenNV = "Nguyen Van B";
//        Date ngaySinh = Date.valueOf("2001-01-01");
//        String CCCD = "987654321";
//        String sdt = "9876543210";
//        int gend = 1;
//        int sta = 1;
//        int ca = 1;
//        int chuc = 1;
//        String hinh = "NewHinh";
//        String Email = "NewEmail";
//        String DiaChi = "NewDiaChi";
//
//        boolean result = nhanVienDao.sua(maBanDau, tenNV, ngaySinh, CCCD, sdt, gend, sta, ca, chuc, hinh, Email, DiaChi, maNV);
//        if (result) {
//            System.out.println("Update successful");
//        } else {
//            System.out.println("Update failed");
//        }
//    }
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        // Existing NhanVien details
//        String maBanDau = "NV011";
//
//        // New NhanVien details
//        String maNV = "NV011";
//        String tenNV = "Nguyen Van a";
//        Date ngaySinh = Date.valueOf("2001-01-01");
//        String CCCD = "987654321";
//        String sdt = "9876543210";
//        int gend = 1;
//        int sta = 1;
//        int ca = 1;
//        int chuc = 1;
//        String Email = "NewEmail";
//        String DiaChi = "NewDiaChi";
//
//        boolean result = nhanVienDao.suakhonganh(maBanDau, tenNV, ngaySinh, CCCD, sdt, gend, sta, ca, chuc, Email, DiaChi, maNV);
//        if (result) {
//            System.out.println("Update successful");
//        } else {
//            System.out.println("Update failed");
//        }
//    }
//}
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        long count = nhanVienDao.soLuongQL();
//        System.out.println("Number of employees: " + count);
//    }
//}
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        String maNV = "NV001"; // replace with the actual MaNV
//        NhanVien nhanVien = nhanVienDao.getNVTHeoMa(maNV);
//        if (nhanVien != null) {
//            System.out.println("NhanVien details: " + nhanVien.toString());
//        } else {
//            System.out.println("No NhanVien found with MaNV: " + maNV);
//        }
//    }
//}
//
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        String sdt = "0123654987"; // replace with the actual Sdt
//        NhanVien nhanVien = nhanVienDao.getNVTHeoSdt(sdt);
//        if (nhanVien != null) {
//            System.out.println("NhanVien details: " + nhanVien.toString());
//        } else {
//            System.out.println("No NhanVien found with Sdt: " + sdt);
//        }
//    }
//}
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        int ca = 1; // replace with the actual Ca
//        List<NhanVien> nhanViens = nhanVienDao.getNVTHeoCa(ca);
//        if (!nhanViens.isEmpty()) {
//            System.out.println("NhanVien details: ");
//            for (NhanVien nhanVien : nhanViens) {
//                System.out.println(nhanVien.toString());
//            }
//        } else {
//            System.out.println("No NhanVien found with Ca: " + ca);
//        }
//    }
//}
//public class application {
//    public static void main(String[] args) {
//        NhanVienDao nhanVienDao = new NhanVienImpl();
//
//        int chuc = 1; // replace with the actual Chuc
//        List<NhanVien> nhanViens = nhanVienDao.getNVTHeoChuc(chuc);
//        if (!nhanViens.isEmpty()) {
//            System.out.println("NhanVien details: ");
//            for (NhanVien nhanVien : nhanViens) {
//                System.out.println(nhanVien.toString());
//            }
//        } else {
//            System.out.println("No NhanVien found with Chuc: " + chuc);
//        }
//    }
//}
public class application {
    public static void main(String[] args) {
        NhanVienDao nhanVienDao = new NhanVienImpl();
        System.out.println(nhanVienDao.addTaiKhoan("NV011"));
    }
}
//}
//}
//        KhachHangDao khachHangDao = new KhachHangImpl();
//        KhachHang khachHang = new KhachHang("020", "Nguyen Van D", "0123456789", "123456789", Date.valueOf("2000-01-01"), "Ha Noi", 1, new LoaiKhachHang("002"), "Hoangphuc@gmail.com", 1000);
//        khachHangDao.addKhachHang(khachHang);
//        System.out.println(khachHangDao.getAllKH());


//        XemHoaDonDao xemHoaDonDao = new DAOTest.impl.XemHoaDonImpl();
//        System.out.println(xemHoaDonDao.getHDTheoNgayLap(9, 12, 2023));