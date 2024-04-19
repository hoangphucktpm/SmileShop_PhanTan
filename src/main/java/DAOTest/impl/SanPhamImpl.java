package DAOTest.impl;

import DAOTest.SanPhamDao;
import Entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class SanPhamImpl implements SanPhamDao {
    private EntityManager em;
    public SanPhamImpl(){
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<SanPham> getAllSP() {
        List<SanPham> list = em.createNamedQuery("SanPham.findAllSP", SanPham.class).getResultList();
        return list;

    }
}
