package Application;

import DAOTest.KhachHangDao;
import DAOTest.impl.KhachHangImpl;
import Entities.KhachHang;
import Entities.LoaiKhachHang;

import java.sql.Date;

public class application {
    public static void main(String[] args) {
        KhachHangDao khachHangDao = new KhachHangImpl();
        KhachHang khachHang = new KhachHang("017", "Nguyen Van A", "0123456789", "021354267854", null, "Ha Noi", 1, null, "k@gmail.com", 0);
//        khachHangDao.addKhachHang(khachHang);
    }
}
