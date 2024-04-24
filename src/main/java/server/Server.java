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

    private static final String URL = "rmi://172.20.10.5:6541/";

    public static void main(String[] args) throws IOException, NamingException {
        try {
            Date ngaySinh = new Date("2003/05/29");
            String TenDangNhap = "NV111";
            NhanVien nv = new NhanVien("NV111", "Hoàng Phúc", ngaySinh, "CCCD001", "0123456789",
                    1, 1, 1, 1, "hinhAnh.png", "email@example.com", "Dia Chi"
            );
            TaiKhoan taiKhoan = new TaiKhoan(nv, "123456");
            Hashtable<String, String> env = new Hashtable();
            env.put("java.security.policy", "rmi/policy.policy");
            LocateRegistry.createRegistry(6541);
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
            context.bind(URL + "KhachHangDao", khachHangDao);
            context.bind(URL + "KhuyenMaiDao", khuyenMaiDao);
            context.bind(URL + "LapHoaDonDao", lapHoaDonDao);
            context.bind(URL + "NhaCungCapDao", nhaCungCapDao);
            context.bind(URL + "NhanVienDao", nhanVienDao);
            context.bind(URL + "SanPhamDao", sanPhamDao);
            context.bind(URL + "ThongKeDoanhThuDao", thongKeDoanhThuDao);
            context.bind(URL + "ThongKeHoaDonDao", thongKeHoaDonDao);
            context.bind(URL + "ThongTinCaNhanDao", thongTinCaNhanDao);
            context.bind(URL + "TinhTrangSanPhamDao", tinhTrangSanPhamDao);
            context.bind(URL + "XemHoaDonDao", xemHoaDonDao);
            if (nhanVienDao.getAllNV().size() == 0) {
                nhanVienDao.them("NV111", "Hoàng Phúc", ngaySinh, "CCCD001", "0123456789",
                        1, 1, 1, 1, "hinhAnh.png", "email@example.com", "Dia Chi");
                nhanVienDao.addTaiKhoan(TenDangNhap);
            }

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}