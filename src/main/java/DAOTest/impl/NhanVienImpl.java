package DAOTest.impl;

import Entities.NhanVien;
import Entities.TaiKhoan;
import jakarta.persistence.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAOTest.NhanVienDao;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public NhanVienImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public List<NhanVien> getAllNV() throws RemoteException {
        return this.em.createNamedQuery("NhanVien.findAll", NhanVien.class).getResultList();
    }

    @Override
    public boolean them(String maNV, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi) throws RemoteException {
        try {
            em.getTransaction().begin();
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanvien(maNV);
            nhanVien.setTenNhanVien(tenNV);
            nhanVien.setNgaySinh(ngaySinh);
            nhanVien.setCccd(CCCD);
            nhanVien.setSdt(sdt);
            nhanVien.setGioiTinh(gend);
            nhanVien.setTrangThai(sta);
            nhanVien.setCaLamViec(ca);
            nhanVien.setChucVu(chuc);
            nhanVien.setHinhAnh(hinh);
            nhanVien.setEmail(Email);
            nhanVien.setDiaChi(DiaChi);
            em.persist(nhanVien);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public boolean sua(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi, String maNV) throws RemoteException {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Find and remove the old NhanVien
            NhanVien nhanVienCu = em.find(NhanVien.class, maBanDau);
            if (nhanVienCu != null) {
                em.remove(nhanVienCu);
            }

            // Create and persist the new NhanVien
            NhanVien nhanVienMoi = new NhanVien();
            nhanVienMoi.setMaNhanvien(maNV);
            nhanVienMoi.setTenNhanVien(tenNV);
            nhanVienMoi.setNgaySinh(ngaySinh);
            nhanVienMoi.setCccd(CCCD);
            nhanVienMoi.setSdt(sdt);
            nhanVienMoi.setGioiTinh(gend);
            nhanVienMoi.setTrangThai(sta);
            nhanVienMoi.setCaLamViec(ca);
            nhanVienMoi.setChucVu(chuc);
            nhanVienMoi.setHinhAnh(hinh);
            nhanVienMoi.setEmail(Email);
            nhanVienMoi.setDiaChi(DiaChi);
            em.persist(nhanVienMoi);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return false;
    }

    @Override
    public boolean suakhonganh(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String maNV) throws RemoteException {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Find and remove the old NhanVien
            NhanVien nhanVienCu = em.find(NhanVien.class, maBanDau);
            if (nhanVienCu != null) {
                em.remove(nhanVienCu);
            }

            // Create and persist the new NhanVien
            NhanVien nhanVienMoi = new NhanVien();
            nhanVienMoi.setMaNhanvien(maNV);
            nhanVienMoi.setTenNhanVien(tenNV);
            nhanVienMoi.setNgaySinh(ngaySinh);
            nhanVienMoi.setCccd(CCCD);
            nhanVienMoi.setSdt(sdt);
            nhanVienMoi.setGioiTinh(gend);
            nhanVienMoi.setTrangThai(sta);
            nhanVienMoi.setCaLamViec(ca);
            nhanVienMoi.setChucVu(chuc);
            nhanVienMoi.setEmail(Email);
            nhanVienMoi.setDiaChi(DiaChi);
            em.persist(nhanVienMoi);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return false;
    }

    @Override
    public long soLuongNV() throws RemoteException {
        try {
            return em.createQuery("SELECT COUNT(n) FROM NhanVien n WHERE n.chucVu = 0 AND n.maNhanvien <> 'admin'", Long.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long soLuongQL() throws RemoteException {
        try {
            return em.createQuery("SELECT COUNT(n) FROM NhanVien n WHERE n.chucVu = 1 AND n.maNhanvien <> 'admin'", Long.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public NhanVien getNVTHeoMa(String maNV) throws RemoteException {
        try {
            NhanVien nhanVien = em.find(NhanVien.class, maNV);
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NhanVien getNVTHeoTen(String tenNV) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT n FROM NhanVien n WHERE n.tenNhanVien LIKE :tenNV", NhanVien.class);
            query.setParameter("tenNV", "%" + tenNV + "%");
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NhanVien getNVTHeoSdt(String sdt) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT n FROM NhanVien n WHERE n.sdt LIKE :sdt", NhanVien.class);
            query.setParameter("sdt", "%" + sdt + "%");
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVien> getNVTHeoCa(int ca) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT n FROM NhanVien n WHERE n.caLamViec = :ca AND n.maNhanvien <> 'admin'", NhanVien.class);
            query.setParameter("ca", ca);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<NhanVien>();
        }
    }

    @Override
    public List<NhanVien> getNVTHeoChuc(int chuc) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT n FROM NhanVien n WHERE n.chucVu = :chuc AND n.maNhanvien <> 'admin'", NhanVien.class);
            query.setParameter("chuc", chuc);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addTaiKhoan(String tk) throws RemoteException {
        try {
            em.getTransaction().begin();
            TaiKhoan taiKhoan = new TaiKhoan();
            NhanVien nhanVien = em.find(NhanVien.class, tk);
            if (nhanVien == null) {
                // handle the case where no NhanVien with the given maNhanvien exists
                // for example, you can create a new NhanVien entity
                nhanVien = new NhanVien();
                nhanVien.setMaNhanvien(tk);
                em.persist(nhanVien);
            }
            taiKhoan.setTenTaiKhoan(nhanVien);
            taiKhoan.setMatKhau("12345678"); // default password
            em.persist(taiKhoan);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TaiKhoan getTK(String nv) throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery("SELECT t FROM TaiKhoan t JOIN t.tenTaiKhoan n WHERE n.maNhanvien LIKE :nv", TaiKhoan.class);
            query.setParameter("nv", nv);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}