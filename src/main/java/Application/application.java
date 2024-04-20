package Application;

import DAOTest.*;
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.KhuyenMaiImpl;
import DAOTest.impl.LapHoaDonImpl;
import DAOTest.impl.ThongTinCaNhanImpl;
import Entities.KhachHang;
import Entities.KhuyenMai;
import Entities.LoaiKhachHang;


import java.sql.Date;
import java.time.LocalDate;

public class application {
    public static void main(String[] args) {


        ThongTinCaNhanDao thongTinCaNhanDao = new ThongTinCaNhanImpl();
        System.out.println(thongTinCaNhanDao.mailNhanVien("NV001"));



    }
}
