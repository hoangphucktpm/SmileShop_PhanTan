package DAOTest.impl;

import DAOTest.ThongKeHoaDonDao;
import Entities.ThongKeHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThongKeHoaDonImpl implements ThongKeHoaDonDao {
    private EntityManager em;

    public ThongKeHoaDonImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<ThongKeHoaDon> getHoaDonTheoNgayVaCa(int day, int month, int year, int ca) {
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

        List<Object[]> result = em.createQuery("select h.maHoaDon, h.nhanVien.tenNhanVien, n.caLamViec, sum(c.soLuongSP) as soLuong, h.ngayLapHoaDon, h.tongTien, lkh.tenLoaiKH, n.tenNhanVien, n.maNhanvien " +
                        "from CtHoadon c " +
                        "join c.maHoaDon h " +
                        "join h.nhanVien n " +
                        "join h.khachHang k " +
                        "join k.loaiKH lkh " +
                        "where day(h.ngayLapHoaDon) = :day and MONTH(h.ngayLapHoaDon) = :month and year(h.ngayLapHoaDon) = :year AND HOUR(h.ngayLapHoaDon) >= :startHour  and HOUR(h.ngayLapHoaDon) < :endHour " +
                        "group by  h.maHoaDon, h.nhanVien.tenNhanVien, n.caLamViec, h.ngayLapHoaDon, h.tongTien, lkh.tenLoaiKH, n.tenNhanVien, n.maNhanvien")
                .setParameter("day", day)
                .setParameter("month", month)
                .setParameter("year", year)
                .setParameter("startHour", startHour)
                .setParameter("endHour", endHour)
                .getResultList();
                List<ThongKeHoaDon> resultList = new ArrayList<>();
                for (Object[] obj : result) {
                    ThongKeHoaDon tk = new ThongKeHoaDon();
                    tk.setMaHoaDon((String) obj[0]);
                    tk.setMaNhanVien((String) obj[1]);
                    tk.setCaLamViec(((Short) obj[2]).intValue());
                    tk.setSoLuongSP(((Long) obj[3]).intValue());
                    tk.setNgayLap((Timestamp) obj[4]);
                    tk.setLoaiKH((String) obj[6]);
                    tk.setDoanhThu((double) obj[5]);
                    resultList.add(tk);
                }
                return resultList;
    }

    @Override
    public List<ThongKeHoaDon> getHoaDonTheoNV(int day, int month, int year, String maNhanVien, int ca) {
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

        List<Object[]> result = em.createQuery("select h.maHoaDon, h.nhanVien.tenNhanVien, n.caLamViec, sum(c.soLuongSP) as soLuong, h.ngayLapHoaDon, h.tongTien, lkh.tenLoaiKH, n.tenNhanVien, n.maNhanvien " +
                        "from CtHoadon c " +
                        "join c.maHoaDon h " +
                        "join h.nhanVien n " +
                        "join h.khachHang k " +
                        "join k.loaiKH lkh " +
                        "where day(h.ngayLapHoaDon) = :day and MONTH(h.ngayLapHoaDon) = :month and year(h.ngayLapHoaDon) = :year AND HOUR(h.ngayLapHoaDon) >= :startHour  and HOUR(h.ngayLapHoaDon) < :endHour and n.maNhanvien = :maNhanVien " +
                        "group by  h.maHoaDon, h.nhanVien.tenNhanVien, n.caLamViec, h.ngayLapHoaDon, h.tongTien, lkh.tenLoaiKH, n.tenNhanVien, n.maNhanvien")
                .setParameter("day", day)
                .setParameter("month", month)
                .setParameter("year", year)
                .setParameter("startHour", startHour)
                .setParameter("endHour", endHour)
                .setParameter("maNhanVien", maNhanVien)
                .getResultList();List<ThongKeHoaDon> resultList = new ArrayList<>();
        for (Object[] obj : result) {
            ThongKeHoaDon tk = new ThongKeHoaDon();
            tk.setMaHoaDon((String) obj[0]);
            tk.setMaNhanVien((String) obj[1]);
            tk.setCaLamViec(((Short) obj[2]).intValue());
            tk.setSoLuongSP(((Long) obj[3]).intValue());
            tk.setNgayLap((Timestamp) obj[4]);
            tk.setLoaiKH((String) obj[6]);
            tk.setDoanhThu((double) obj[5]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public int tongHoaDon(int top) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();
        Query query = em.createQuery("select n.maNhanvien, count(h.maHoaDon) as HoaDon from CtHoadon ct " +
                        "join ct.maHoaDon h " +
                        "join h.nhanVien n on n.maNhanvien = h.nhanVien " +
                        "where MONTH(h.ngayLapHoaDon) = :month AND YEAR(h.ngayLapHoaDon) = :year " +
                        "group by n.maNhanvien " +
                        "order by HoaDon desc")
                .setParameter("month", month)
                .setParameter("year", year);
        List<Object[]> result = query.getResultList();
        if(top <= result.size()){
            Object[] topResult = result.get(top - 1);
            return ((Long) topResult[1]).intValue();
        }

        return 0;
    }

    @Override
    public String nhanVienTop(int top) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();
        Query query = em.createQuery("select n.tenNhanVien, count(h.maHoaDon) as HoaDon from CtHoadon ct " +
                        "join ct.maHoaDon h " +
                        "join h.nhanVien n on n.maNhanvien = h.nhanVien " +
                        "where MONTH(h.ngayLapHoaDon) = :month AND YEAR(h.ngayLapHoaDon) = :year " +
                        "group by n.tenNhanVien " +
                        "order by HoaDon desc")
                .setParameter("month", month)
                .setParameter("year", year)
                .setMaxResults(top);
        List<Object[]> results = query.getResultList();
        if (top <= results.size()) {
            Object[] resultAtTop = results.get(top - 1);
            return (String) resultAtTop[0];
        }
        return "";
    }

}
