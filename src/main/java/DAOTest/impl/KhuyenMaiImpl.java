package DAOTest.impl;

import DAOTest.KhachHangDao;
import DAOTest.KhuyenMaiDao;
import Entities.KhuyenMai;
import Entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class KhuyenMaiImpl implements KhuyenMaiDao {
    private EntityManager em;

    public KhuyenMaiImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<KhuyenMai> getAllKhuyenMai() {
        return em.createNamedQuery("KhuyenMai.findAll", KhuyenMai.class).getResultList();
    }

    @Override
    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        em.getTransaction().begin();
        em.persist(khuyenMai);
        em.getTransaction().commit();
        return true;
    }


    @Override
    public boolean updateKhuyenMai(KhuyenMai khuyenMai) {
        em.getTransaction().begin();
        em.merge(khuyenMai);
        em.getTransaction().commit();
        return true;
    }


    @Override
    public Long soLuongCTKM() {
        Query query = em.createQuery("SELECT COUNT(km) FROM KhuyenMai km");
        return (Long) query.getSingleResult();
    }

    @Override
    public KhuyenMai getKMTHeoMa(String MaKM) {
        return em.find(KhuyenMai.class, MaKM);
    }


    @Override
    public KhuyenMai getKMTheoTen(String TenKM) {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.tenKhuyenMai = :tenKhuyenMai", KhuyenMai.class);
        query.setParameter("tenKhuyenMai", TenKM);
        return (KhuyenMai) query.getSingleResult();
    }


    @Override
    public List<KhuyenMai> getKMTheoPhanTram(int PhanTramKM) {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.phanTramKhuyenMai = :phanTramKhuyenMai", KhuyenMai.class);
        query.setParameter("phanTramKhuyenMai", PhanTramKM);
        return query.getResultList();
    }

    @Override
    public List<KhuyenMai> getKMTheoTrangThai(String TrangThaiKM) {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.trangThai = :trangThai", KhuyenMai.class);
        query.setParameter("trangThai", TrangThaiKM);
        return query.getResultList();
    }

    @Override
    public boolean hetHan() {
        Query query = em.createQuery("UPDATE KhuyenMai km SET km.trangThai = 0 WHERE km.ngayKetThuc < CURRENT_DATE");
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<KhuyenMai> getTheoThoiGian(LocalDate begin, LocalDate end) {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.ngayBatDau >= :begin AND km.ngayKetThuc <= :end", KhuyenMai.class);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        return query.getResultList();
    }

    @Override
    public boolean adDSPKM(String maSP, String maKM) {
        KhuyenMai khuyenMai = em.find(KhuyenMai.class, maKM);
        if (khuyenMai != null) {
            Query query = em.createQuery("UPDATE SanPham sp SET sp.khuyenMai = :khuyenMai WHERE sp.maSp = :maSP");
            query.setParameter("maSP", maSP);
            query.setParameter("khuyenMai", khuyenMai);
            em.getTransaction().begin();
            int updatedRows = query.executeUpdate();
            em.getTransaction().commit();
            return updatedRows > 0;
        } else {
            return false;
        }
    }

    //			 public void capNhatNull(String maKM)
    //			 {
    //				 Connection con = ConnectDatabase.getInstance().getConnection();
    //			        PreparedStatement stmt = null;
    //
    //			        try {
    //			            String sql = "update SanPham set KhuyenMai = null where KhuyenMai = '" + maKM +"'";
    //			            stmt = con.prepareStatement(sql);
    //			            stmt.executeUpdate();
    //			        } catch (SQLException e) {
    //			            e.printStackTrace();
    //
    //			        }
    //
    //			 }
    @Override
    public void capNhatNull(String maKM) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT sp FROM SanPham sp WHERE sp.khuyenMai.maKhuyenMai = :maKM", SanPham.class);
        query.setParameter("maKM", maKM);
        List<SanPham> sanPhams = query.getResultList();
        for (SanPham sp : sanPhams) {
            sp.setKhuyenMai(null);
            em.merge(sp);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<String> dsMaSPKM(String ma) {
        Query query = em.createQuery("SELECT sp.maSp FROM SanPham sp WHERE sp.khuyenMai.maKhuyenMai = :ma", String.class);
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    @Override
    public String layKhuyenMaiTuSanPham(String ma) {
        Query query = em.createQuery("SELECT sp.khuyenMai.maKhuyenMai FROM SanPham sp WHERE sp.maSp = :ma", String.class);
        query.setParameter("ma", ma);
        return (String) query.getSingleResult();
    }

}
