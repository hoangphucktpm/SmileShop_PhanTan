package DAOTest.impl;

import DAOTest.TinhTrangSanPhamDao;
import Entities.ThongKeSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TinhTrangSanPhamImpl implements TinhTrangSanPhamDao {
    private EntityManager em;

    public TinhTrangSanPhamImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }
    @Override
    public List<ThongKeSanPham> getSPOUT() {
        Query query = em.createQuery("select maSp, tensp, s.loaiSanPham.tenLoaiSP, soluong, ngaynhap, gianhap, giaBan, mauSac, s.size " +
                "from SanPham s where s.soluong = 0");
        List<Object[]> result = query.getResultList();
        List<ThongKeSanPham> resultList = new java.util.ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((java.util.Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;

    }

    @Override
    public List<ThongKeSanPham> getSPRE() {
        Query query = em.createQuery("select maSp, tensp, s.loaiSanPham.tenLoaiSP, soluong, ngaynhap, gianhap, giaBan, mauSac, s.size " +
                "from SanPham s where s.soluong > 0");
        List<Object[]> result = query.getResultList();
        List<ThongKeSanPham> resultList = new java.util.ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((java.util.Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public List<ThongKeSanPham> getALMOUT() {
        Query query = em.createQuery("select maSp, tensp, s.loaiSanPham.tenLoaiSP, soluong, ngaynhap, gianhap, giaBan, mauSac, s.size " +
                "from SanPham s where s.soluong <= 10 and  soluong > 0");
        List<Object[]> result = query.getResultList();
        List<ThongKeSanPham> resultList = new java.util.ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((java.util.Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;
    }


    @Override
    public List<ThongKeSanPham> getNewAdd(Date date) {
        Query query = em.createQuery("select maSp, tensp, s.loaiSanPham.tenLoaiSP, soluong, ngaynhap, gianhap, giaBan, mauSac, s.size " +
                "from SanPham s where ngaynhap = :date")
                .setParameter("date", date);
        List<Object[]> result = query.getResultList();
        List<ThongKeSanPham> resultList = new java.util.ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((java.util.Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public List<ThongKeSanPham> getOld() {
        LocalDateTime specificDateTime = LocalDateTime.now().minusMonths(1);

        Date specificDate = Date.from(specificDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Query recentQuery = em.createQuery("SELECT s.maSp FROM CtHoadon ct " +
                        "INNER JOIN ct.maSanPham s  " +
                        "INNER JOIN ct.maHoaDon h " +
                        "WHERE h.ngayLapHoaDon >= :date " +
                        "ORDER BY s.maSp ASC")
                .setParameter("date", specificDate);
        List<String> recentProducts = recentQuery.getResultList();
        Set<String> recentProductIds = new HashSet<>(recentProducts);
        Query newQuery = em.createQuery("SELECT s.maSp, s.tensp, s.loaiSanPham.tenLoaiSP, s.soluong, s.ngaynhap, s.gianhap, s.giaBan, s.mauSac, s.size " +
                "FROM SanPham s " +
                "LEFT JOIN CtHoadon ct ON s.maSp = ct.maSanPham " +
                "LEFT JOIN HoaDon h ON h.maHoaDon = ct.maHoaDon " +
                "WHERE (h.ngayLapHoaDon < :date OR h.maHoaDon IS NULL) AND s.maSp NOT IN :recentIds " +
                "ORDER BY s.maSp ASC")// Sorting by maSp
                .setParameter("date", specificDate)
                .setParameter("recentIds", recentProductIds);
        List<Object[]> result = newQuery.getResultList();
        List<ThongKeSanPham> resultList = new ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public List<ThongKeSanPham> getSold(int day, int month, int year) {
        Query query = em.createQuery("select s.maSp, s.tensp, s.loaiSanPham.tenLoaiSP, s.soluong, s.ngaynhap, s.gianhap, s.giaBan, s.mauSac, s.size " +
                        "from CtHoadon ct INNER JOIN ct.maSanPham s INNER JOIN ct.maHoaDon h " +
                        "where DAY(h.ngayLapHoaDon) = :day and MONTH(h.ngayLapHoaDon) = :month and YEAR(h.ngayLapHoaDon) = :year " +
                        "ORDER BY s.maSp ASC")
                .setParameter("day", day)
                .setParameter("month", month)
                .setParameter("year", year);
        List<Object[]> result = query.getResultList();
        List<ThongKeSanPham> resultList = new java.util.ArrayList<>();
        for (Object[] obj : result) {
            ThongKeSanPham tk = new ThongKeSanPham();
            tk.setMaSP((String) obj[0]);
            tk.setTenSP((String) obj[1]);
            tk.setLoai((String) obj[2]);
            tk.setSoLuongNhap((int) obj[3]);
            tk.setNgayNhap((java.util.Date) obj[4]);
            tk.setGiaNhap((double) obj[5]);
            tk.setGiaBan((double) obj[6]);
            tk.setMauSac((String) obj[7]);
            tk.setKichThuoc((String) obj[8]);
            resultList.add(tk);
        }
        return resultList;
    }

    @Override
    public int soLuongBan(String ma, int day, int month, int year) {
        Query query = em.createQuery("select sum(ct.soLuongSP) from CtHoadon ct INNER JOIN ct.maSanPham s INNER JOIN ct.maHoaDon h " +
                        "where s.maSp = :ma and DAY(h.ngayLapHoaDon) = :day and MONTH(h.ngayLapHoaDon) = :month and YEAR(h.ngayLapHoaDon) = :year")
                .setParameter("ma", ma)
                .setParameter("day", day)
                .setParameter("month", month)
                .setParameter("year", year);
        Long result = (Long) query.getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    @Override
    public Double tongTienBan(int top) {
        LocalDate date = LocalDate.now();
        int ngay = date.getDayOfMonth();
        int thang = date.getMonthValue();
        int nam = date.getYear();
        Query query = em.createQuery("select sum(h.tongTien) from CtHoadon ct INNER JOIN ct.maSanPham s INNER JOIN ct.maHoaDon h " +
                        "where MONTH(h.ngayLapHoaDon) = :thang and YEAR(h.ngayLapHoaDon) = :nam " +
                        "group by ct.maSanPham.giaBan " +
                        "order by sum(h.tongTien) desc")
                .setParameter("thang", thang)
                .setParameter("nam", nam);
        List<Double> result = query.getResultList();
        if (top <= result.size()) {
            return result.get(top - 1);
        }
        return 0.0;
    }

    @Override
    public String sanPhamTop(int top) {
        LocalDate date = LocalDate.now();
        int ngay = date.getDayOfMonth();
        int thang = date.getMonthValue();
        int nam = date.getYear();
        Query query = em.createQuery("select s.tensp from CtHoadon ct INNER JOIN ct.maSanPham s INNER JOIN ct.maHoaDon h " +
                        "where MONTH(h.ngayLapHoaDon) = :thang and YEAR(h.ngayLapHoaDon) = :nam " +
                        "group by s.tensp, ct.maSanPham.giaBan " +
                        "order by sum(h.tongTien) desc")
                .setParameter("thang", thang)
                .setParameter("nam", nam);
        List<Object> result = query.getResultList();
        if (top <= result.size()) {
            return (String) result.get(top - 1);
        }
        return "";
    }

}
