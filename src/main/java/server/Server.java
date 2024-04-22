package server;

import DAOTest.*;
import DAOTest.impl.*;
import Entities.KhachHang;
import Entities.KhuyenMai;
import Entities.NhanVien;
import Entities.TaiKhoan;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;

public class Server {

    private static final String URL = "rmi://192.168.1.16:6541/";

    public static void main(String[] args) throws IOException, NamingException {
        try {


            Hashtable<String, String> env = new Hashtable();
            env.put("java.security.policy", "rmi/policy.policy");


            Context context = new InitialContext();

            KhachHangDao khachHangDao = new KhachHangImpl();
            KhuyenMaiDao khuyenMaiDao = new KhuyenMaiImpl();
            LapHoaDonDao lapHoaDonDao = new LapHoaDonImpl();
            NhaCungCapDao nhaCungCapDao = new NhaCungCapImpl();
            NhanVienDao nhanVienDao = new NhanVienImpl();
            SanPhamDao sanPhamDao = new SanPhamImpl();
            ThongKeDoanhThuDao thongKeDoanhThuDao = new ThongKeDoanhThuImpl();
            ThongKeHoaDonDao thongKeHoaDonDao = new ThongKeHoaDonImpl();
            ThongTinCaNhanDao thongTinCaNhanDao = new ThongTinCaNhanImpl();
            TinhTrangSanPhamDao tinhTrangSanPhamDao = new TinhTrangSanPhamImpl();
            XemHoaDonDao xemHoaDonDao = new XemHoaDonImpl();

            LocateRegistry.createRegistry(6541);


            context.bind(URL + "KhachHangDao", khachHangDao);
            context.bind(URL + "KhuyenMaiDao", khuyenMaiDao);
            context.bind(URL + "LapHoaDonDao", lapHoaDonDao);
            context.bind(URL + "NhaCungCapDao", nhaCungCapDao);
            context.bind(URL + "NhanVienDao", nhanVienDao);
            context.rebind(URL + "SanPhamDao", sanPhamDao);
            context.bind(URL + "ThongKeDoanhThuDao", thongKeDoanhThuDao);
            context.bind(URL + "ThongKeHoaDonDao", thongKeHoaDonDao);
            context.bind(URL + "ThongTinCaNhanDao", thongTinCaNhanDao);
            context.bind(URL + "TinhTrangSanPhamDao", tinhTrangSanPhamDao);
            context.bind(URL + "XemHoaDonDao", xemHoaDonDao);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}