package DAOTest.impl;

import DAOTest.KhachHangDao;
import DAOTest.KhuyenMaiDao;
import Entities.KhuyenMai;
import Entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class KhuyenMaiImpl extends UnicastRemoteObject implements KhuyenMaiDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public KhuyenMaiImpl() throws RemoteException {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<KhuyenMai> getAllKhuyenMai() throws RemoteException {
        return em.createNamedQuery("KhuyenMai.findAll", KhuyenMai.class).getResultList();
    }

    @Override
    public boolean themKhuyenMai(KhuyenMai khuyenMai) throws RemoteException {
        em.getTransaction().begin();
        em.persist(khuyenMai);
        em.getTransaction().commit();
        return true;
    }


    @Override
    public boolean updateKhuyenMai(KhuyenMai khuyenMai) throws RemoteException {
        em.getTransaction().begin();
        em.merge(khuyenMai);
        em.getTransaction().commit();
        return true;
    }


    @Override
    public Long soLuongCTKM() throws RemoteException {
        Query query = em.createQuery("SELECT COUNT(km) FROM KhuyenMai km");
        return (Long) query.getSingleResult();
    }

    @Override
    public KhuyenMai getKMTHeoMa(String MaKM) throws RemoteException {
        return em.find(KhuyenMai.class, MaKM);
    }


    @Override
    public KhuyenMai getKMTheoTen(String TenKM) throws RemoteException {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.tenKhuyenMai = :tenKhuyenMai", KhuyenMai.class);
        query.setParameter("tenKhuyenMai", TenKM);
        return (KhuyenMai) query.getSingleResult();
    }


    @Override
    public List<KhuyenMai> getKMTheoPhanTram(int PhanTramKM) throws RemoteException {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.phanTramKhuyenMai = :phanTramKhuyenMai", KhuyenMai.class);
        query.setParameter("phanTramKhuyenMai", PhanTramKM);
        return query.getResultList();
    }

    @Override
    public List<KhuyenMai> getKMTheoTrangThai(String TrangThaiKM) throws RemoteException {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.trangThai = :trangThai", KhuyenMai.class);
        query.setParameter("trangThai", TrangThaiKM);
        return query.getResultList();
    }

    @Override
    public boolean hetHan() throws RemoteException {
        Query query = em.createQuery("UPDATE KhuyenMai km SET km.trangThai = 0 WHERE km.ngayKetThuc < CURRENT_DATE");
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<KhuyenMai> getTheoThoiGian(LocalDate begin, LocalDate end) throws RemoteException {
        Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE (km.ngayBatDau >= :begin AND km.ngayBatDau <= :end) OR (km.ngayKetThuc >= :begin AND km.ngayKetThuc <= :end)", KhuyenMai.class);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        return query.getResultList();
    }

    public boolean adDSPKM(String maSP, String maKM) throws RemoteException {
        KhuyenMai khuyenMai = (KhuyenMai)this.em.find(KhuyenMai.class, maKM);
        EntityTransaction tx = this.em.getTransaction();

        try {
            tx.begin();
            SanPham sanPham = (SanPham)this.em.find(SanPham.class, maSP);
            sanPham.setKhuyenMai(khuyenMai);
            this.em.merge(sanPham);
            tx.commit();
            return true;
        } catch (Exception var6) {
            Exception e = var6;
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void capNhatNull(String maKM) throws RemoteException {
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
    public List<String> dsMaSPKM(String ma) throws RemoteException {
        Query query = em.createQuery("SELECT sp.maSp FROM SanPham sp WHERE sp.khuyenMai.maKhuyenMai = :ma", String.class);
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    @Override
    public String layKhuyenMaiTuSanPham(String ma) throws RemoteException {
        Query query = em.createQuery("SELECT sp.khuyenMai.maKhuyenMai FROM SanPham sp WHERE sp.maSp = :ma", String.class);
        query.setParameter("ma", ma);
        return (String) query.getSingleResult();
    }

    @Override
    public String layTenKMTheoMa(String ma) throws RemoteException {
        Query query = em.createQuery("SELECT km.tenKhuyenMai FROM KhuyenMai km WHERE km.maKhuyenMai = :ma", String.class);
        query.setParameter("ma", ma);
        return (String) query.getSingleResult();
    }

}
