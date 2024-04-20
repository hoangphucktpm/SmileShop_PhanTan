package DAOTest.impl;

import DAOTest.NhaCungCapDao;
import Entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class NhaCungCapImpl implements NhaCungCapDao {
    private EntityManager em;
    public NhaCungCapImpl(){
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }
    @Override
    public List<NhaCungCap> getNhaCungCaps() {
        return em.createNamedQuery("NhaCungCap.getNhaCungCaps", NhaCungCap.class)
                .getResultList();
    }

    @Override
    public boolean them(NhaCungCap nhaCungCap) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(nhaCungCap);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sua(NhaCungCap nhaCungCap) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(nhaCungCap);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
    }
        return false;
    }


    @Override
    public List<NhaCungCap> getTen(String tenNCC) {
        return em.createNamedQuery("NhaCungCap.getTen", NhaCungCap.class)
                .setParameter("tenNCC", tenNCC)
                .getResultList();
    }

    @Override
    public List<NhaCungCap> getMa(String MaNCC) {
        return em.createNamedQuery("NhaCungCap.getMa", NhaCungCap.class)
                .setParameter("MaNCC", MaNCC)
                .getResultList();
    }



    @Override
    public List<NhaCungCap> getSDT(String SDT) {
        return em.createNamedQuery("NhaCungCap.getSDT", NhaCungCap.class)
                .setParameter("SDT", SDT)
                .getResultList();
    }

    @Override
    public List<NhaCungCap> getEmail(String email) {
        return em. createNamedQuery("NhaCungCap.getEmail", NhaCungCap.class)
                .setParameter("em", email)
                .getResultList();

    }

    @Override
    public int soLuongNCC() {
        return em.createNamedQuery("NhaCungCap.soLuongNCC", Long.class)
                .getSingleResult().intValue();
    }


}
