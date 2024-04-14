package Application;

import DAOTest.KhachHangDao;
import DAOTest.LapHoaDonDao;
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.LapHoaDonImpl;
import Entities.KhachHang;
import Entities.LoaiKhachHang;

import java.sql.Date;

public class application {
    public static void main(String[] args) {

//        KhachHangDao khachHangDao = new KhachHangImpl();
//        KhachHang khachHang = new KhachHang("020", "Nguyen Van D", "0123456789", "123456789", Date.valueOf("2000-01-01"), "Ha Noi", 1, new LoaiKhachHang("002"), "Hoangphuc@gmail.com", 1000);
//        khachHangDao.addKhachHang(khachHang);
//        System.out.println(khachHangDao.getAllKH());


        LapHoaDonDao lapHoaDonDao = new LapHoaDonImpl();
        System.out.println(lapHoaDonDao.getAllLapHoaDon());


    }
}
