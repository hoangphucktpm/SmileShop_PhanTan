package server;

import DAOTest.*;
import DAOTest.impl.*;
import Entities.KhachHang;
import Entities.NhanVien;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public Server() {
    }

    private static final String URL = "rmi://HOANGPHUC:6541/";

    public static void main(String[] args) throws IOException, NamingException {
        try {
            Context context = new InitialContext();
            LocateRegistry.createRegistry(6541);

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

            context.rebind(URL + "KhachHangDao", khachHangDao);
            context.rebind(URL + "KhuyenMaiDao", khuyenMaiDao);
            context.rebind(URL + "LapHoaDonDao", lapHoaDonDao);
            context.rebind(URL + "NhaCungCapDao", nhaCungCapDao);
            context.rebind(URL + "NhanVienDao", nhanVienDao);
            context.rebind(URL + "SanPhamDao", sanPhamDao);
            context.rebind(URL + "ThongKeDoanhThuDao", thongKeDoanhThuDao);
            context.rebind(URL + "ThongKeHoaDonDao", thongKeHoaDonDao);
            context.rebind(URL + "ThongTinCaNhanDao", thongTinCaNhanDao);
            context.rebind(URL + "TinhTrangSanPhamDao", tinhTrangSanPhamDao);
            context.rebind(URL + "XemHoaDonDao", xemHoaDonDao);


            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
