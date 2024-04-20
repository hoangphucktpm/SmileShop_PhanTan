package DAOTest.impl;

import DAOTest.SanPhamDao;
import Entities.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

public class SanPhamImpl implements SanPhamDao {
    private EntityManager em;

    public SanPhamImpl() {
        em = Persistence.createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<SanPham> getAllSP() {
        List<SanPham> list = em.createNamedQuery("SanPham.getAllProducts", SanPham.class).getResultList();
        return list;
    }

    @Override
    public List<SanPham> timTheoGia(double gia1, double gia2) {
        List<SanPham> list = em.createNamedQuery("SanPham.timTheoGia", SanPham.class)
                .setParameter("gia1", gia1)
                .setParameter("gia2", gia2)
                .getResultList();
        return list;
    }

    @Override
    public boolean them(String maSP, String tenSP, String nhaCungCap, String km, double giaBan, int soluong, Date ngayNhap,
                        String color, String size, String img, String chatLieu, int tinhTrang, String dvt, String loaiSP, int VAT,
                        double giaBanRa) {
        EntityTransaction tx = em.getTransaction();
        NhaCungCap ncc = getMaOne(nhaCungCap);
        KhuyenMai km1 = em.find(KhuyenMai.class, km);
        ChatLieu cl = em.find(ChatLieu.class, chatLieu);
        LoaiSanPham lsp = em.find(LoaiSanPham.class, loaiSP);
        boolean tt = false;
        if (tinhTrang == 1) {
            tt = true;
        } else {
            tt = false;
        }
        try {
            tx.begin();
            SanPham sp = new SanPham();
            sp.setMaSp(maSP);
            sp.setTensp(tenSP);
            sp.setNhaCungCap(ncc);
            sp.setKhuyenMai(km1);
            sp.setGianhap(giaBan);
            sp.setSoluong(soluong);
            sp.setNgaynhap(ngayNhap);
            sp.setMauSac(MauSac.valueOf(color));
            sp.setSize(Size.valueOf(size.toLowerCase()));
            if (img == null) {
                img = "null";
            }
            sp.setHinhanh(img);
            sp.setChatLieu(cl);
            sp.setTinhTrang(tt);
            sp.setDonViTinh(dvt);
            sp.setLoaiSanPham(lsp);
            sp.setVat(VAT);
            sp.setGiaBan(giaBanRa);
            em.persist(sp);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public NhaCungCap getMaOne(String MaNCC) {
        return em.createNamedQuery("NhaCungCap.getMa", NhaCungCap.class)
                .setParameter("MaNCC", MaNCC)
                .getSingleResult();
    }

    @Override
    public boolean sua(SanPham sanPham) {
        // Check if chatLieu is null and handle it
        if (sanPham.getChatLieu() == null) {
            // Skip saving the SanPham entity if chatLieu is null
            return false;
        }

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(sanPham);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Entities.LoaiSanPham> getLoaiSP() {
        List<Entities.LoaiSanPham> list = em.createNamedQuery("SanPham.getLoaiSP", Entities.LoaiSanPham.class).getResultList();
        return list;
    }

    @Override
    public List<Entities.ChatLieu> getChatLieu() {
        List<Entities.ChatLieu> list = em.createNamedQuery("SanPham.getChatLieu", Entities.ChatLieu.class).getResultList();
        return list;
    }

    @Override
    public ChatLieu getChatLieuOne(String ma) {
        return em.createNamedQuery("SanPham.getChatLieuOne", ChatLieu.class)
                .setParameter("ma", ma)
                .getSingleResult();
    }

    @Override
    public LoaiSanPham getLoaiSPOne(String ma) {
        try {
            TypedQuery<LoaiSanPham> query = em.createQuery("SELECT l FROM LoaiSanPham l WHERE l.maLoaiSP = :ma", LoaiSanPham.class);
            query.setParameter("ma", ma);
            List<LoaiSanPham> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Entities.NhaCungCap> getTenNCC() {
        List<Entities.NhaCungCap> list = em.createNamedQuery("SanPham.getTen", Entities.NhaCungCap.class).getResultList();
        return list;
    }

    @Override
    public List<Entities.KhuyenMai> getKMTheoTen() {
        List<Entities.KhuyenMai> list = em.createNamedQuery("SanPham.getKMTheoTen", Entities.KhuyenMai.class).getResultList();
        return list;
    }

    @Override
    public KhuyenMai getKMTheoTenOne(String ma) {
        try {
            Query query = em.createQuery("SELECT km FROM KhuyenMai km WHERE km.maKhuyenMai = :ma", KhuyenMai.class);
            query.setParameter("ma", ma);
            List<KhuyenMai> results = query.getResultList();
            if (!results.isEmpty()) {
                // ignores multiple results
                return results.get(0);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ChatLieu getCLTheoTenOne(String ma) {
        try {
            TypedQuery<ChatLieu> query = em.createQuery("SELECT c FROM ChatLieu c WHERE c.maChatLieu = :ma", ChatLieu.class);
            query.setParameter("ma", ma);
            List<ChatLieu> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LoaiSanPham getLSPTheoTenOne(String ma) {
        LoaiSanPham lsp = em.createNamedQuery("SanPham.getLSPTheoTenOne", LoaiSanPham.class)
                .setParameter("ma", ma)
                .getSingleResult();
        return lsp;
    }

    @Override
    public int getKMTheoPhanTram(String maSp) {
        SanPham sp = em.find(SanPham.class, maSp);
        if (sp != null && sp.getKhuyenMai() != null) {
            return sp.getKhuyenMai().getPhanTramKhuyenMai();
        } else {
            // Return a default value or throw an exception
            return 0; // or throw new NoResultException("No SanPham found with the provided maSp or the SanPham does not have a KhuyenMai associated with it.");
        }
    }

    @Override
    public int soLuong() {
        Long result = (Long) em.createNamedQuery("SanPham.soLuong").getSingleResult();
        return result.intValue(); // Chuyển đổi từ Long sang int
    }


    @Override
    public int vat(String ma) {
        Number vat = (Number) em.createNamedQuery("SanPham.vat")
                .setParameter("ma", ma)
                .getSingleResult();
        return vat.intValue();
    }

    @Override
    public boolean themLoaiSP(Entities.LoaiSanPham loaiSanPham) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(loaiSanPham);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public int soLuongLSP() {
        int sl = (int) em.createNamedQuery("SanPham.soLuongLSP").getSingleResult();
        return sl;
    }

    @Override
    public boolean themChatLieu(Entities.ChatLieu chatLieu) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(chatLieu);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int soLuongChatLieu() {
        int sl = (int) em.createNamedQuery("SanPham.soLuongChatLieu").getSingleResult();
        return sl;
    }

    @Override
    public String getTenLoaiSP(String maSP) {
        String ten = (String) em.createNamedQuery("SanPham.getTenLoaiSP")
                .setParameter("maSP", maSP)
                .getSingleResult();
        return ten;
    }

    @Override
    public String getTenNhaCC(String maSP) {
        String ten = (String) em.createNamedQuery("SanPham.getTenNhaCC")
                .setParameter("maSP", maSP)
                .getSingleResult();
        return ten;
    }

    @Override
    public String getTenCL(String maSP) {
        String ten = (String) em.createNamedQuery("SanPham.getTenCL")
                .setParameter("maSP", maSP)
                .getSingleResult();
        return ten;
    }

    @Override
    public SanPham getMa(String Ma) {
        SanPham sp = em.createNamedQuery("SanPham.getMa", SanPham.class)
                .setParameter("Ma", Ma)
                .getSingleResult();
        return sp;
    }

    @Override
    public SanPham getTenSP(String Ten) {
        SanPham sp = em.createNamedQuery("SanPham.getTenSP", SanPham.class)
                .setParameter("Ten", Ten)
                .getSingleResult();
        return sp;
    }

    @Override
    public List<SanPham> getlistTenLoaiSP(String lsp) {
        List<SanPham> list = em.createNamedQuery("SanPham.getlistTenLoaiSP", SanPham.class)
                .setParameter("lsp", lsp)
                .getResultList();
        return list;
    }

    @Override
    public List<SanPham> getlistTenNCC(String nccap) {
        List<SanPham> list = em.createNamedQuery("SanPham.getlistTenNCC", SanPham.class)
                .setParameter("nccap", nccap)
                .getResultList();
        return list;
    }

    @Override
    public List<SanPham> getlistTenCL(String ChatLieu) {
        List<SanPham> list = em.createNamedQuery("SanPham.getlistTenCL", SanPham.class)
                .setParameter("ChatLieu", ChatLieu)
                .getResultList();
        return list;
    }

    @Override
    public List<SanPham> getlistTenMauSac(String MS) {
        List<SanPham> list = em.createNamedQuery("SanPham.getlistTenMauSac", SanPham.class)
                .setParameter("MS", MS)
                .getResultList();
        return list;
    }

    @Override
    public List<SanPham> getlistSize(String kthuoc) {
        List<SanPham> list = em.createNamedQuery("SanPham.getlistSize", SanPham.class)
                .setParameter("kthuoc", kthuoc)
                .getResultList();
        return list;
    }

    @Override
    public String getMoTaChatLieu(String ma) {
        String mota = (String) em.createNamedQuery("SanPham.getMoTaChatLieu")
                .setParameter("ma", ma)
                .getSingleResult();
        return mota;
    }

    @Override
    public String getTenChatLieu(String ma) {
        String ten = (String) em.createNamedQuery("SanPham.getTenChatLieu")
                .setParameter("ma", ma)
                .getSingleResult();
        return ten;
    }

    @Override
    public String getMaChatLieu(String ten, String moTa) {
        String ma = (String) em.createNamedQuery("SanPham.getMaChatLieu")
                .setParameter("ten", ten)
                .setParameter("moTa", moTa)
                .getSingleResult();
        return ma;
    }


}
