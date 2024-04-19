package DAOTest.impl;

import DAOTest.LapHoaDonDao;
import Entities.HoaDon;
import Entities.KhachHang;
import Entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class LapHoaDonImpl implements LapHoaDonDao {

    private EntityManager em;

    public LapHoaDonImpl() {
        em = Persistence
                .createEntityManagerFactory("SQLdb")
                .createEntityManager();
    }


    @Override
    public List<HoaDon> getAllLapHoaDon() {
        return em.createNamedQuery("HoaDon.findAll", HoaDon.class).getResultList();
    }


    @Override
    public String maHoaDon(String maNhanVien) {
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
    public List<KhachHang> timKhachHangBySDT(String Sdt) {
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
    public float getDiem(String Sdt) {
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
    public int soLuongHD() {
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
    public int soLuongSPDaBan(String masp) {
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
    public int soLuongNhap(String masp) {
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

    //	public boolean addCT_HoaDon(String maHoaDon,String maSP,int soLuong) {
    //		Connection con = ConnectDatabase.getInstance().getConnection();
    //		PreparedStatement stmt = null;
    //		int n =0;
    //		try {
    //			stmt = con.prepareStatement("insert into dbo.CT_HoaDon(MaHoaDon, MaSanPham, SoLuongSP) values (?,?,?)");
    //			stmt.setString(1,maHoaDon);
    //			stmt.setString(2, maSP);
    //
    //			stmt.setInt(3, soLuong);
    //			n= stmt.executeUpdate();
    //		} catch (Exception e3) {
    //			e3.printStackTrace();
    //		}
    //		return n>0;
    //	}

    @Override
    public boolean addCT_HoaDon(String maHoaDon, String maSP, int soLuong) {
        try {
            Query query = em.createNativeQuery("INSERT INTO dbo.CT_HoaDon(MaHoaDon, MaSanPham, SoLuongSP) VALUES (?, ?, ?)");
            query.setParameter(1, maHoaDon);
            query.setParameter(2, maSP);
            query.setParameter(3, soLuong);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getTenNV(String ma) {
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

    //	public sanPham laySP(String ten, String mau, String kt) {
    //	    sanPham sp = new sanPham();
    //
    //	    try {
    //	        Connection con = ConnectDatabase.getInstance().getConnection();
    //	        PreparedStatement stmt = null;
    //	        String sql = "SELECT  maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP, VAT \r\n"
    //	        		+ "from SanPham s join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap \r\n"
    //	        		+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP \r\n"
    //	        		+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu\r\n"
    //	        		+ "where Tensp like ? and MauSac = ? AND Size = ?";
    //	        stmt = con.prepareStatement(sql);
    //
    //	        stmt.setString(1, ten); // Sử dụng LIKE để tìm kiếm Tensp chứa chuỗi ten
    //	        stmt.setString(2, mau);
    //	        stmt.setString(3, kt);
    ////
    //
    //			ResultSet rs = stmt.executeQuery();
    //				while (rs.next()) {
    //					String maSP= rs.getString(1);
    //					String tenSP=rs.getString(2);
    //					String NhaCC= rs.getString(3);
    //					String khuyenMai= rs.getString(4);
    //					Double giaNhap= rs.getDouble(5);
    //					int soLuong = rs.getInt(6);
    //					Date dateNhap = rs.getDate(7);
    //					String hinhAnh = rs.getString(8);
    //					String MauSac = rs.getString(9);
    //					String size = rs.getString(10);
    //					String chatLieu = rs.getString(11);
    //					int tinhTrang = rs.getInt(12);
    //					String donViTinh = rs.getString(13);
    //					String loaiSP = rs.getString(14);
    //					Double giaban = rs.getDouble(15);
    //					int vat = rs.getInt(16);
    //					Boolean sta = true;
    //					if (tinhTrang == 1)
    //					{
    //						sta = true;
    //					}
    //					else
    //						sta = false;
    //					sanPham.MauSac mauSac = null;
    //					sanPham.Size kichThuoc = null;
    //					for (sanPham.MauSac colr: sanPham.MauSac.values())
    //					{
    //						if(MauSac.equalsIgnoreCase(colr.nCo))
    //						{
    //							mauSac = colr;
    //						}
    //					}
    //					for (sanPham.Size sizeC: sanPham.Size.values())
    //					{
    //						if(size.equalsIgnoreCase(sizeC.nSiz))
    //						{
    //							kichThuoc = sizeC;
    //						}
    //					}
    //					LoaiSanPham loai = new LoaiSanPham(loaiSP);
    //					ChatLieu cl = new ChatLieu(chatLieu);
    //					NhaCungCap ncc = new NhaCungCap(NhaCC);
    //					KhuyenMai km = new KhuyenMai(khuyenMai);
    //					sanPham sp1 = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta, donViTinh, loai, ncc, km, giaban, vat);
    //					sp = sp1;
    //				}
    //
    //		} catch (SQLException e) {
    //			// TODO: handle exception
    //			e.printStackTrace();
    //		}
    //		return sp;
    //	}

    @Override
    public SanPham laySP(String ten, String mau, String kt) {
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
    public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc, String nhanvien, String khachhang, double tongtien) {
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
    public boolean updateDiem(double diem, String sdt) {
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


    //	public int getKMTheoPhanTram(String ma) {
    //		int phanTram = 0;
    //
    //		try {
    //			Connection con = ConnectDatabase.getInstance().getConnection();
    //			String sql = "select PhanTramKhuyenMai from KhuyenMai where TenKhuyenMai like '" + ma + "'";
    //			Statement statement = con.createStatement();
    //			ResultSet rs = statement.executeQuery(sql);
    //
    //			while (rs.next()) {
    //				phanTram = rs.getInt(1);
    //			}
    //		} catch (SQLException e) {
    //			e.printStackTrace();
    //		}
    //
    //		return phanTram;
    //	}
    //

    // Can kiem tra lai

    @Override
    public int getKMTheoPhanTram(String ma) {
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

}
