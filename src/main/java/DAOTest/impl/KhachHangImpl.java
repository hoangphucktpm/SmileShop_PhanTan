package DAOTest.impl;

import DAOTest.KhachHangDao;
import Entities.KhachHang;
import Entities.LoaiKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class KhachHangImpl implements KhachHangDao {
     private EntityManager em;

        public KhachHangImpl(){
            em = Persistence
                    .createEntityManagerFactory("SQLdb")
                    .createEntityManager();
        }

    @Override
    public List<KhachHang> getAllKH() {
        return em.createNamedQuery("KhachHang.findAll", KhachHang.class).getResultList();
    }

    @Override
        public boolean addKhachHang(KhachHang khachHang) {
            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();
                em.merge(khachHang);
                tx.commit();
                return true;
            } catch (Exception e){
                tx.rollback();
                e.printStackTrace();
            }
            return false;
        }

    @Override
    public KhachHang getKhachHang(String maKH) {
        return em.find(KhachHang.class, maKH);
    }

    @Override
    public Boolean updateKhachHang(KhachHang khachHang) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(khachHang);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LoaiKhachHang> getAllLoaiKH() {
        return em.createNamedQuery("LoaiKhachHang.findAll", LoaiKhachHang.class).getResultList();
    }

    @Override
    public List<KhachHang> getKH5KPoint() {
        return em.createNamedQuery("KhachHang.find5kPoint", KhachHang.class).getResultList();
    }

    @Override
    public String getLoaiKH(String maKH) {
        return em.find(KhachHang.class, maKH).getLoaiKH().getTenLoaiKH();
    }

    @Override
    public KhachHang getKHByName(String tenKH) {
        return em.createNamedQuery("KhachHang.findKHByName", KhachHang.class).setParameter("tenKH", tenKH).getSingleResult();
    }

    @Override
    public List<KhachHang> getByGender(int gioiTinh) {
        return em.createNamedQuery("KhachHang.findByGender", KhachHang.class).setParameter("gioiTinh", gioiTinh).getResultList();
    }

    @Override
    public List<KhachHang> getKHByLoaiKH(String tenLoaiKH) {
        return em.createNamedQuery("KhachHang.findByLoaiKH", KhachHang.class).setParameter("tenLoaiKH", tenLoaiKH).getResultList();
    }


}
