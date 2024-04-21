package DAOTest.impl;

import DAOTest.KhachHangDao;
import Entities.KhachHang;
import Entities.LoaiKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class KhachHangImpl extends UnicastRemoteObject implements KhachHangDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public KhachHangImpl() throws RemoteException {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }

    @Override
    public List<KhachHang> getAllKH() throws RemoteException {
        return em.createNamedQuery("KhachHang.findAll", KhachHang.class).getResultList();
    }

    @Override
    public boolean addKhachHang(KhachHang khachHang) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(khachHang);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public KhachHang getKhachHang(String maKH) throws RemoteException {
        return em.find(KhachHang.class, maKH);
    }

    @Override
    public Boolean updateKhachHang(KhachHang khachHang) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(khachHang);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LoaiKhachHang> getAllLoaiKH() throws RemoteException {
        return em.createNamedQuery("LoaiKhachHang.findAll", LoaiKhachHang.class).getResultList();
    }

    @Override
    public List<KhachHang> getKH5KPoint() throws RemoteException {
        return em.createNamedQuery("KhachHang.find5kPoint", KhachHang.class).getResultList();
    }

    @Override
    public String getLoaiKH(String maKH) throws RemoteException {
        return em.find(KhachHang.class, maKH).getLoaiKH().getTenLoaiKH();
    }

    @Override
    public KhachHang getKHByName(String tenKH) throws RemoteException {
        return em.createNamedQuery("KhachHang.findKHByName", KhachHang.class).setParameter("tenKH", tenKH).getSingleResult();
    }

    @Override
    public List<KhachHang> getByGender(int gioiTinh) throws RemoteException {
        return em.createNamedQuery("KhachHang.findByGender", KhachHang.class).setParameter("gioiTinh", gioiTinh).getResultList();
    }

    @Override
    public List<KhachHang> getKHByLoaiKH(String tenLoaiKH) throws RemoteException {
        return em.createNamedQuery("KhachHang.findByLoaiKH", KhachHang.class).setParameter("tenLoaiKH", tenLoaiKH).getResultList();
    }


}
