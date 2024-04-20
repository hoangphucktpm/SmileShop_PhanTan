package DAOTest.impl;

import DAOTest.ThongTinCaNhanDao;
import Entities.NhanVien;
import Entities.TaiKhoan;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ThongTinCaNhanImpl implements ThongTinCaNhanDao {

    private EntityManager em;
    public ThongTinCaNhanImpl(){
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }
    @Override
    public List<NhanVien> loadNhanVien(String ma) {
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

    // public taiKhoan loadTaiKhoan(String ma) {
    //       taiKhoan tk = new taiKhoan();
    //
    //        try {
    //            Connection con = ConnectDatabase.getInstance().getConnection();
    //            String sql = "select * from TaiKhoan where TenTaiKhoan like '%" + ma + "%'";
    //            Statement statement = con.createStatement();
    //            ResultSet rs = statement.executeQuery(sql);
    //
    //            while (rs.next()) {
    //                String tenTK = rs.getString(1);
    //                String matKhau = rs.getString(2);
    //
    //               tk = new taiKhoan(tenTK, matKhau);
    //
    //            }
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //
    //        return tk;
    //    }

    @Override
    public TaiKhoan loadTaiKhoan(String ma) {
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
    public String tenNV(String manv) {
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
    public boolean sua( String tenNV,String sdt,  String Email, String MaNhanVien, String hinhAnh) {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE NhanVien n SET n.tenNhanVien = :tenNV, n.sdt = :sdt, n.email = :Email, n.hinhAnh = :hinhAnh WHERE n.maNhanvien = :MaNhanVien");
            query.setParameter("tenNV", tenNV);
            query.setParameter("sdt", sdt);
            query.setParameter("Email", Email);
            query.setParameter("hinhAnh", hinhAnh);
            query.setParameter("MaNhanVien", MaNhanVien);
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean suaMK(String matkhau, String MaNhanVien) {
        try {
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
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }



    @Override
    public String mailNhanVien(String manv) {
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
