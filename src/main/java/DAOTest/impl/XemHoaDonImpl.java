package DAOTest.impl;

import DAOTest.ThongKeHoaDonDao;
import DAOTest.XemHoaDonDao;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class XemHoaDonImpl implements XemHoaDonDao {
    private EntityManager em;

    public XemHoaDonImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<HoaDon> getAllHoaDon() {
        Query query = em.createQuery("SELECT hd.maHoaDon, hd.tienKhachDua, hd.khachHang.maKH, hd.nhanVien.maNhanvien, " +
                "hd.tongTien, hd.ngayLapHoaDon, hd.diemTichDuoc FROM HoaDon hd ORDER BY hd.ngayLapHoaDon", Object[].class);
        List<Object[]> results = query.getResultList();
        List<HoaDon> hoaDons = new ArrayList<>();
        for (Object[] result : results) {
            String mahd = (String) result[0];
            Double tienKD = (Double) result[1];
            Date ngayLap = (Date) result[5];
            String nhanVienId = (String) result[3];
            String khachHangId = (String) result[2];
            double tongTien = (double) result[4];
            Double diemTichDouble = (Double) result[6];
            float diemTich = diemTichDouble.floatValue();

            NhanVien nv = em.find(NhanVien.class, nhanVienId);
            KhachHang kh = em.find(KhachHang.class, khachHangId);
            Timestamp timestamp = new Timestamp(ngayLap.getTime());
            HoaDon hd = new HoaDon(mahd, tienKD, kh, nv, tongTien, timestamp, diemTichDouble);
            hoaDons.add(hd);
        }
        return hoaDons;
    }


    @Override
    public List<CtHoadon> getCT_HoaDon(String mahd) {
        Query query = em.createQuery("SELECT cthd FROM CtHoadon cthd JOIN FETCH cthd.maSanPham sp " +
                "LEFT JOIN FETCH sp.khuyenMai km LEFT JOIN FETCH sp.chatLieu cl WHERE cthd.id.maHoaDon = :mahd", CtHoadon.class);
        query.setParameter("mahd", mahd);
        List<CtHoadon> chiTietHoaDons = query.getResultList();
        return chiTietHoaDons;
    }


    @Override
    public String getTenNV(String manv) {
        NhanVien nv = em.find(NhanVien.class, manv);
        return nv.getTenNhanVien();
    }

    @Override
    public String getTenKH(String makh) {
        KhachHang kh = em.find(KhachHang.class, makh);
        return kh.getTenKH();
    }


    @Override
    public HoaDon getHDTHeoMa(String MaHD) {
        HoaDon hd = em.find(HoaDon.class, MaHD);
        return hd;
    }

    @Override
    public List<HoaDon> getHDTheoTenNV(String tennv) {
        Query query = em.createQuery("SELECT hd.maHoaDon, hd.tienKhachDua, hd.khachHang.maKH, hd.nhanVien.maNhanvien, " +
                "hd.tongTien, hd.ngayLapHoaDon, hd.diemTichDuoc FROM HoaDon hd WHERE hd.nhanVien.tenNhanVien = :tennv ORDER BY hd.ngayLapHoaDon", Object[].class);
        query.setParameter("tennv", tennv);
        List<Object[]> results = query.getResultList();
        List<HoaDon> hoaDons = new ArrayList<>();
        for (Object[] result : results) {
            String mahd = (String) result[0];
            Double tienKD = (Double) result[1];
            Date ngayLap = (Date) result[5];
            String nhanVienId = (String) result[3];
            String khachHangId = (String) result[2];
            double tongTien = (double) result[4];
            Double diemTichDouble = (Double) result[6];
            float diemTich = diemTichDouble.floatValue();

            NhanVien nv = em.find(NhanVien.class, nhanVienId);
            KhachHang kh = em.find(KhachHang.class, khachHangId);
            Timestamp timestamp = new Timestamp(ngayLap.getTime());
            HoaDon hd = new HoaDon(mahd, tienKD, kh, nv, tongTien, timestamp, diemTichDouble);
            hoaDons.add(hd);
        }
        return hoaDons;
    }

    @Override
    public List<HoaDon> getHDTheoTenKH(String tenkh) {
        Query query = em.createQuery("SELECT hd.maHoaDon, hd.tienKhachDua, hd.khachHang.maKH, hd.nhanVien.maNhanvien, " +
                "hd.tongTien, hd.ngayLapHoaDon, hd.diemTichDuoc FROM HoaDon hd WHERE hd.khachHang.tenKH = :tenkh ORDER BY hd.ngayLapHoaDon", Object[].class);
        query.setParameter("tenkh", tenkh);
        List<Object[]> results = query.getResultList();
        List<HoaDon> hoaDons = new ArrayList<>();
        for (Object[] result : results) {
            String mahd = (String) result[0];
            Double tienKD = (Double) result[1];
            Date ngayLap = (Date) result[5];
            String nhanVienId = (String) result[3];
            String khachHangId = (String) result[2];
            double tongTien = (double) result[4];
            Double diemTichDouble = (Double) result[6];
            float diemTich = diemTichDouble.floatValue();

            NhanVien nv = em.find(NhanVien.class, nhanVienId);
            KhachHang kh = em.find(KhachHang.class, khachHangId);
            Timestamp timestamp = new Timestamp(ngayLap.getTime());
            HoaDon hd = new HoaDon(mahd, tienKD, kh, nv, tongTien, timestamp, diemTichDouble);
            hoaDons.add(hd);
        }
        return hoaDons;
    }

    @Override
    public List<HoaDon> getHDTheoNgayLap(int ngay, int thang, int nam) {
        TypedQuery<HoaDon> query = em.createQuery(
                "SELECT hd FROM HoaDon hd " +
                        "JOIN FETCH hd.khachHang kh " +
                        "JOIN FETCH hd.nhanVien nv " +
                        "WHERE DAY(hd.ngayLapHoaDon) = :ngay AND MONTH(hd.ngayLapHoaDon) = :thang AND YEAR(hd.ngayLapHoaDon) = :nam", HoaDon.class);
        query.setParameter("ngay", ngay);
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);

        return query.getResultList();
    }

}
