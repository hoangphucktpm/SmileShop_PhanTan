package DAOTest.impl;

import DAOTest.NhaCungCapDao;
import Entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NhaCungCapImpl extends UnicastRemoteObject implements NhaCungCapDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public NhaCungCapImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }

    @Override
    public List<NhaCungCap> getNhaCungCaps() throws RemoteException {
        return em.createNamedQuery("NhaCungCap.getNhaCungCaps", NhaCungCap.class)
                .getResultList();
    }

    @Override
    public boolean them(NhaCungCap nhaCungCap) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(nhaCungCap);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sua(NhaCungCap nhaCungCap) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(nhaCungCap);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<NhaCungCap> getTen(String tenNCC) throws RemoteException {
        return em.createNamedQuery("NhaCungCap.getTen", NhaCungCap.class)
                .setParameter("tenNCC", tenNCC)
                .getResultList();
    }

    @Override
    public List<NhaCungCap> getMa(String MaNCC) throws RemoteException {
        return em.createNamedQuery("NhaCungCap.getMa", NhaCungCap.class)
                .setParameter("MaNCC", MaNCC)
                .getResultList();
    }


    @Override
    public List<NhaCungCap> getSDT(String SDT) throws RemoteException {
        return em.createNamedQuery("NhaCungCap.getSDT", NhaCungCap.class)
                .setParameter("SDT", SDT)
                .getResultList();
    }

    @Override
    public List<NhaCungCap> getEmail(String email) throws RemoteException {
        return em.createNamedQuery("NhaCungCap.getEmail", NhaCungCap.class)
                .setParameter("em", email)
                .getResultList();

    }

    @Override
    public int soLuongNCC() throws RemoteException {
        return em.createNamedQuery("NhaCungCap.soLuongNCC", Long.class)
                .getSingleResult().intValue();
    }


}
