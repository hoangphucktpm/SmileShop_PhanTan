package Application;

import DAOTest.KhachHangDao;
import DAOTest.KhuyenMaiDao;
import DAOTest.LapHoaDonDao;
import DAOTest.XemHoaDonDao;
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.KhuyenMaiImpl;
import DAOTest.impl.LapHoaDonImpl;
import Entities.KhachHang;
import Entities.KhuyenMai;
import Entities.LoaiKhachHang;


import java.sql.Date;
import java.time.LocalDate;

public class application {
    public static void main(String[] args) {


        KhuyenMaiDao khuyenMaiDao = new KhuyenMaiImpl();
        System.out.println(khuyenMaiDao.layKhuyenMaiTuSanPham("SP001"));


    }
}
