package DAOTest.impl;

import DAOTest.ThongTinCaNhanDao;
import Entities.NhanVien;
import Entities.TaiKhoan;
import jakarta.persistence.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ThongTinCaNhanImpl extends UnicastRemoteObject implements ThongTinCaNhanDao {
    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public ThongTinCaNhanImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }

    @Override
    public List<NhanVien> loadNhanVien(String ma) throws RemoteException {
        try {
            Query query = em.createQuery("SELECT n FROM NhanVien n WHERE n.maNhanvien LIKE :ma", NhanVien.class);
            query.setParameter("ma", "%" + ma + "%");
            List<NhanVien> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }


    @Override
    public TaiKhoan loadTaiKhoan(String ma) throws RemoteException {
        try {
            Query query = em.createQuery("SELECT t FROM TaiKhoan t WHERE t.tenTaiKhoan.maNhanvien LIKE :ma", TaiKhoan.class);
            query.setParameter("ma", "%" + ma + "%");
            TaiKhoan tk = (TaiKhoan) query.getSingleResult();
            return tk;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }


    @Override
    public String tenNV(String manv) throws RemoteException {
        try {
            Query query = em.createQuery("SELECT n.tenNhanVien FROM NhanVien n WHERE n.maNhanvien = :ma", NhanVien.class);
            query.setParameter("ma", manv);
            String tenNV = (String) query.getSingleResult();
            return tenNV;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();

        }
        return "";
    }


    @Override
    public boolean sua(String tenNV, String sdt, String Email, String MaNhanVien, String hinhAnh) throws RemoteException {
        EntityManager em = null;
        try {
            em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
            em.getTransaction().begin();
            // your code here
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    @Override
    public boolean suaMK(String matkhau, String MaNhanVien) throws RemoteException {
        EntityManager em = null;
        try {
            // Open a new EntityManager
            em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();

            em.getTransaction().begin();
            NhanVien nhanVien = em.find(NhanVien.class, MaNhanVien);
            if (nhanVien != null) {
                Query query = em.createQuery("UPDATE TaiKhoan t SET t.matKhau = :matkhau WHERE t.tenTaiKhoan = :nhanVien");
                query.setParameter("matkhau", matkhau);
                query.setParameter("nhanVien", nhanVien);
                query.executeUpdate();
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }


    @Override
    public String mailNhanVien(String manv) throws RemoteException {
        try {
            Query query = em.createQuery("SELECT n.email FROM NhanVien n WHERE n.maNhanvien = :ma", NhanVien.class);
            query.setParameter("ma", manv);
            String mail = (String) query.getSingleResult();
            return mail;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return "";
    }


}
