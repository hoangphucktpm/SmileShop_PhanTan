package DAOTest.impl;

import DAOTest.LapHoaDonDao;
import Entities.HoaDon;
import Entities.KhachHang;
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
    public float getDiem(String Sdt){
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

    //
    //	public int soLuongHD(){
    //		int sum = 0;
    //		try {
    //			Connection con = ConnectDatabase.getInstance().getConnection();
    //			String sql="  SELECT COUNT(*) FROM [dbo].[HoaDon] WHERE CAST(NgayLapHoaDon AS DATE) = CAST(GETDATE() AS DATE);";
    //			Statement statement = con.createStatement();
    //			ResultSet rs = statement.executeQuery(sql);
    //			while (rs.next()) {
    //				sum = rs.getInt(1);
    //			}
    //		} catch (SQLException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		}
    //		return sum;
    //	}


    @Override
        public int soLuongHD(){
            int sum = 0;
            try {
                Query query = em.createNativeQuery("SELECT COUNT(*) FROM [dbo].[HoaDon] WHERE CAST(NgayLapHoaDon AS DATE) = CAST(GETDATE() AS DATE);");
                sum = (int) query.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sum;
        }
        //
    //	public static int soLuongSPDaBan(String masp) {
    //		int soLuongSP =0;
    //		try {
    //			Connection con = ConnectDatabase.getInstance().getConnection();
    //			PreparedStatement stmt = null;
    //			String sql = "select soLuongSPDaBan = SUM(SoLuongSP)\r\n" +
    //					"from dbo.CT_HoaDon\r\n" +
    //					"where MaSanPham = ?\r\n" +
    //					"group by MaSanPham";
    //			stmt = con.prepareStatement(sql);
    //			stmt.setString(1,masp);
    //			ResultSet rs = stmt.executeQuery();
    //			while(rs.next()) {
    //				soLuongSP = rs.getInt(1);
    //			}
    //		} catch (Exception e) {
    //			// TODO: handle exception
    //		}
    //		return soLuongSP;
    //	}
    //	public int soLuongNhap(String masp) {
    //		int soLuongSP =0;
    //		try {
    //			Connection con = ConnectDatabase.getInstance().getConnection();
    //			PreparedStatement stmt = null;
    //			String sql = "select Soluong\r\n" +
    //					"from dbo.SanPham\r\n" +
    //					"where maSp = ?";
    //			stmt = con.prepareStatement(sql);
    //			stmt.setString(1,masp);
    //			ResultSet rs = stmt.executeQuery();
    //			while(rs.next()) {
    //				soLuongSP = rs.getInt(1);
    //			}
    //		} catch (Exception e) {
    //			// TODO: handle exception
    //		}
    //		return soLuongSP;
    //	}
    //
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
    //	public String getTenNV(String ma) {
    //		String tenDN1 = null;
    //		try {
    //			Connection con = ConnectDatabase.getInstance().getConnection();
    //			PreparedStatement stmt = null;
    //			String sql = "select TenNhanVien\r\n" +
    //					"from dbo.TaiKhoan t join dbo.NhanVien n on t.TenTaiKhoan = n.MaNhanvien\r\n" +
    //					"where t.TenTaiKhoan =?";
    //			stmt = con.prepareStatement(sql);
    //			stmt.setString(1,ma);
    //			ResultSet rs = stmt.executeQuery();
    //			while(rs.next()) {
    //				tenDN1 = rs.getString(1).toString();
    //			}
    //		} catch (Exception e) {
    //			// TODO: handle exception
    //		}
    //		return tenDN1;
    //	}
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
    //
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
    //	public boolean updateDiem(double diem, String sdt)
    //	{
    //		Connection con = ConnectDatabase.getInstance().getConnection();
    //		String sql = "update KhachHang "
    //				+ "set diemTichLuy =? where Sdt like ?\r\n";
    //		PreparedStatement stmt = null;
    //
    //		int n =0;
    //		try {
    //			stmt = con.prepareStatement(sql);
    //			stmt.setDouble(1, diem);
    //			stmt.setString(2, sdt);
    //			n= stmt.executeUpdate();
    //		} catch (Exception e3) {
    //			// TODO: handle exception
    //		}
    //		return n>0;
    //	}
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
    //}








}
