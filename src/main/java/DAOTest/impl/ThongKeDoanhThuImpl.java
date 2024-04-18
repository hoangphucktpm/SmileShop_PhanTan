package DAOTest.impl;

import DAOTest.ThongKeDoanhThuDao;
import Entity.ThongKeDoanhThu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDoanhThuImpl implements ThongKeDoanhThuDao {
    private EntityManager em;

    public ThongKeDoanhThuImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();    }
    @Override
    public List<Entity.ThongKeDoanhThu> getDTQuy(int quy, int ca){
        int startHour, endHour;
        if (ca == 0) {
            startHour = 0;
            endHour = 23;
        } else if (ca == 1) {
            startHour = 8;
            endHour = 15;
        } else { // ca == 2
            startHour = 15;
            endHour = 22;
        }
        //        Lấy ra năm hiện tại
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        List<Object[]> results = em.createQuery("SELECT s.maSp, s.tensp, s.mauSac, s.size, s.khuyenMai.maKhuyenMai, s.gianhap, s.soluong, SUM(ct.soLuongSP), s.giaBan " +
                        "FROM SanPham s " +
                        "JOIN CtHoadon ct ON s.maSp = ct.id.maSanPham " +
                        "JOIN HoaDon h ON h.maHoaDon = ct.id.maHoaDon " +
                        "JOIN NhanVien n ON n.maNhanvien = h.nhanVien " +
                        "WHERE QUARTER(h.ngayLapHoaDon) = :quy AND HOUR(h.ngayLapHoaDon) >= :startHour  and HOUR(h.ngayLapHoaDon) < :endHour and year(h.ngayLapHoaDon) = :year " +
                        "GROUP BY s.maSp, s.tensp, s.mauSac, s.size, s.khuyenMai, s.gianhap, s.soluong, s.giaBan")
                .setParameter("quy", quy)
                .setParameter("startHour", startHour)
                .setParameter("endHour", endHour)
                .setParameter("year", year)
                .getResultList();

        List<ThongKeDoanhThu> resultList = new ArrayList<>();
        for (Object[] result : results) {
            ThongKeDoanhThu tk = new ThongKeDoanhThu();
            tk.setMaSP((String) result[0]);
            tk.setTenSP((String) result[1]);
            tk.setMauSac((String) result[2]);
            tk.setSize((String) result[3]);
            tk.setKhuyenMai(getKhuyemMai((String) result[4]));
            tk.setGiaNhap((Double) result[5]);
            tk.setSoLuong((Integer) result[6]);
            tk.setSoLuongBan(((Long) result[7]).intValue());
            tk.setGiaBan((Double) result[8]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public int getKhuyemMai(String maKM) {
        return em.createNamedQuery("KhuyenMai.getPercent", Integer.class)
                .setParameter("maKhuyenMai", maKM)
                .getSingleResult();
    }

    @Override
    public double tongDoanhThuQuy(int quy, int ca) {
        int startHour, endHour;
        if (ca == 0) {
            startHour = 0;
            endHour = 23;
        } else if (ca == 1) {
            startHour = 8;
            endHour = 15;
        } else { // ca == 2
            startHour = 15;
            endHour = 22;
        }
        // Lấy ra năm hiện tại
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        Double result = em.createQuery("SELECT SUM(h.tongTien) " +
                                "FROM SanPham s " +
                                "JOIN CtHoadon ct ON s.maSp = ct.id.maSanPham " +
                                "JOIN HoaDon h ON h.maHoaDon = ct.id.maHoaDon " +
                                "JOIN NhanVien n ON n.maNhanvien = h.nhanVien " +
                                "WHERE QUARTER(h.ngayLapHoaDon) = :quy AND HOUR(h.ngayLapHoaDon) >= :startHour  and HOUR(h.ngayLapHoaDon) < :endHour and year(h.ngayLapHoaDon) = :year",
                        Double.class)
                .setParameter("quy", quy)
                .setParameter("startHour", startHour)
                .setParameter("endHour", endHour)
                .setParameter("year", year)
                .getSingleResult();
        return (result != null) ? result : 0.0;
    }
}
