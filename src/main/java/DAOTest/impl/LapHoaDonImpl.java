package DAOTest.impl;

import DAOTest.LapHoaDonDao;
import Entities.HoaDon;
import Entities.KhachHang;
import Entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LapHoaDonImpl extends UnicastRemoteObject implements LapHoaDonDao {
    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public LapHoaDonImpl() throws RemoteException {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<HoaDon> getAllLapHoaDon() throws RemoteException {
        return em.createNamedQuery("HoaDon.findAll", HoaDon.class).getResultList();
    }


    @Override
    public String maHoaDon(String maNhanVien) throws RemoteException {
        String maHoaDon = null;
        try {
            Query query = em.createNativeQuery("select 'HD'+convert(nvarchar,MAX(RIGHT(LEFT(MaHoaDon,7),5))+'NV'+ ? +CONVERT(nvarchar,GETDATE(),112)) from dbo.HoaDon");
            query.setParameter(1, maNhanVien);

            maHoaDon = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDon;
    }

    @Override
    public List<KhachHang> timKhachHangBySDT(String Sdt) throws RemoteException {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            Query query = em.createNativeQuery("SELECT * FROM dbo.KhachHang WHERE Sdt = ?", KhachHang.class);
            query.setParameter(1, Sdt);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public float getDiem(String Sdt) throws RemoteException {
        float result = 0;

        try {
            Query query = em.createNativeQuery("select diemTichLuy from dbo.KhachHang where Sdt = ?");
            query.setParameter(1, Sdt);
            Double queryResult = (Double) query.getSingleResult();
            result = queryResult.floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int soLuongHD() throws RemoteException {
        int sum = 0;
        try {
            Query query = em.createNativeQuery("SELECT COUNT(*) FROM [dbo].[HoaDon] WHERE CAST(NgayLapHoaDon AS DATE) = CAST(GETDATE() AS DATE);");
            sum = (int) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public int soLuongSPDaBan(String masp) throws RemoteException {
        int soLuongSP = 0;
        try {
            Query query = em.createNativeQuery("SELECT SUM(SoLuongSP) FROM dbo.CT_HoaDon WHERE MaSanPham = ?");
            query.setParameter(1, masp);
            soLuongSP = ((Number) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongSP;
    }


    @Override
    public int soLuongNhap(String masp) throws RemoteException {
        int soLuongSP = 0;
        try {
            Query query = em.createNativeQuery("SELECT SoLuong FROM dbo.SanPham WHERE MaSp = ?");
            query.setParameter(1, masp);
            soLuongSP = ((Number) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongSP;
    }


    @Override
    public String getKMTheoTen(String TenKM) throws RemoteException {
        String ten = "";
        try {
            Query query = em.createNativeQuery("SELECT TenKhuyenMai FROM dbo.KhuyenMai WHERE MaKhuyenMai = ?");
            query.setParameter(1, TenKM);
            ten = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ten;
    }

    @Override
    public HoaDon getHoaDon(String mahd) throws RemoteException {
        return em.find(HoaDon.class, mahd);
    }

    @Override
    public SanPham getSanPham(String masp) throws RemoteException {
        return em.find(SanPham.class, masp);
    }

    @Override
    public boolean addCT_HoaDon(String maHoaDon, String maSP, int soLuong) throws RemoteException {
        try {
            em.getTransaction().begin(); // Start the transaction

            Query query = em.createNativeQuery("INSERT INTO dbo.CT_HoaDon(MaHoaDon, MaSanPham, SoLuongSP) VALUES (?, ?, ?)");
            query.setParameter(1, maHoaDon);
            query.setParameter(2, maSP);
            query.setParameter(3, soLuong);
            query.executeUpdate();

            em.getTransaction().commit(); // Commit the transaction
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback the transaction in case of an error
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getTenNV(String ma) throws RemoteException {
        String tenDN1 = null;
        try {
            Query query = em.createNativeQuery("SELECT TenNhanVien FROM dbo.NhanVien WHERE MaNhanvien = ?");
            query.setParameter(1, ma);
            tenDN1 = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenDN1;
    }


    @Override
    public SanPham laySP(String ten, String mau, String kt) throws RemoteException {
        SanPham sp = null;
        try {
            Query query = em.createQuery("SELECT s FROM SanPham s " +
                    "JOIN s.nhaCungCap ncc " +
                    "JOIN s.loaiSanPham lsp " +
                    "JOIN s.chatLieu c " +
                    "WHERE s.tensp = :ten AND s.mauSac = :mau AND s.size = :kt", SanPham.class);
            query.setParameter("ten", ten);
            query.setParameter("mau", mau);
            query.setParameter("kt", kt);
            sp = (SanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }


    //	public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc,  String nhanvien, String khachhang, double tongtien ) {
    //
    //		Connection con = ConnectDatabase.getInstance().getConnection();
    //        PreparedStatement stmt = null;
    //
    //        try {
    //            String sql = "insert into [dbo].[HoaDon] ([MaHoaDon], [TienKhachDua],[KhachHang], [NhanVien],[TongTien], [NgayLapHoaDon], [DiemTichDuoc]) VALUES (?, ?, ?, ?, ?, GETDATE(),?)";
    //            stmt = con.prepareStatement(sql);
    //            stmt.setString(1, mahd);
    //            stmt.setDouble(2, tienkd);
    //            stmt.setString(3, khachhang);
    //            stmt.setString(4, nhanvien);
    //            stmt.setDouble(5, tongtien);
    //
    //            stmt.setFloat(6, diemtichduoc);
    //            stmt.executeUpdate();
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        } finally {
    //            if (stmt != null) {
    //                try {
    //                    stmt.close();
    //                } catch (SQLException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        }
    //
    //        return true;
    //    }

    //    /{Còn lỗi}/
    @Override
    public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc, String nhanvien, String khachhang, double tongtien) throws RemoteException {
        try {
            em.getTransaction().begin(); // Bắt đầu giao dịch

            Query query = em.createNativeQuery("INSERT INTO dbo.HoaDon (MaHoaDon, TienKhachDua, KhachHang, NhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc) VALUES (?, ?, ?, ?, ?, GETDATE(), ?)");
            query.setParameter(1, mahd);
            query.setParameter(2, tienkd);
            query.setParameter(3, khachhang);
            query.setParameter(4, nhanvien);
            query.setParameter(5, tongtien);
            query.setParameter(6, diemtichduoc);
            query.executeUpdate();

            em.getTransaction().commit(); // Cam kết giao dịch
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDiem(double diem, String sdt) throws RemoteException {
        try {
            em.getTransaction().begin(); // Bắt đầu giao dịch

            Query query = em.createNativeQuery("UPDATE dbo.KhachHang SET DiemTichLuy = ? WHERE Sdt = ?");
            query.setParameter(1, diem);
            query.setParameter(2, sdt);
            query.executeUpdate();

            em.getTransaction().commit(); // Cam kết giao dịch
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public int getKMTheoPhanTram(String ma) throws RemoteException {
        int phanTram = 0;
        try {
            Query query = em.createNativeQuery("SELECT PhanTramKhuyenMai FROM dbo.KhuyenMai WHERE MaKhuyenMai = ?");
            query.setParameter(1, ma);
            List<Integer> results = query.getResultList();
            System.out.println("Results: " + results); // In ra kết quả
            if (!results.isEmpty()) {
                phanTram = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTram;
    }

    @Override
    public int layPhanTramKMTheoMa(String ma) throws RemoteException {
        Query query = em.createNativeQuery("SELECT PhanTramKhuyenMai FROM dbo.KhuyenMai WHERE MaKhuyenMai = ?");
        query.setParameter(1, ma);
        return (int) query.getSingleResult();
    }


}
