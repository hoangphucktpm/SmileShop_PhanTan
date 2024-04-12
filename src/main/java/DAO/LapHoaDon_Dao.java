package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;
import Entity.KhachHang;
import Entity.KhuyenMai;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.NhanVien;
import Entity.sanPham;
import Entity.ChatLieu;
import Entity.ChiTietHoaDon;
import Entity.HoaDon;

public class LapHoaDon_Dao {
	

	private static final ConnectDatabase ConnectDatabase = null;

	public LapHoaDon_Dao() {
		ConnectDatabase.getInstance().connect();
	}
	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> list= new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="Select MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc \r\n"
					+ "from HoaDon hd join KhachHang kh on hd.KhachHang = kh.MaKH join NhanVien nv on hd.NhanVien = nv.MaNhanvien\r\n"

					;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy");
				String mahd = rs.getString(1);
				Double tienKD = rs.getDouble(2);
				Date ngayLap = rs.getDate(6);
				String nhanVien = rs.getString(4);
				String khachHang = rs.getString(3);
				double tongTien =rs.getDouble(5);
				float diemTich = rs.getFloat(7);
				
				NhanVien nv = new NhanVien(nhanVien);
				KhachHang kh = new KhachHang(khachHang);
				HoaDon hd= new HoaDon(mahd, ngayLap, tienKD, diemTich, nv, kh, tongTien);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	// mã hóa đơn
	public String maHoaDon(String maNhanVien) {
		String maHoaDon = null;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select 'HD'+convert(nvarchar,MAX(RIGHT(LEFT(MaHoaDon,7),5))+'NV'+ ? +CONVERT(nvarchar,GETDATE(),112))\r\n" + 
					"					from dbo.HoaDon";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,maNhanVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				maHoaDon = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maHoaDon;
	}

	public List<KhachHang> timKhachHangBySDT(String Sdt) {
	    List<KhachHang> list = new ArrayList<KhachHang>();
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        PreparedStatement stmt = null;
	        String sql = "SELECT MaKH, TenKH FROM dbo.KhachHang WHERE Sdt = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, Sdt);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maKH = rs.getString(1);
	            String tenKH = rs.getString(2);
	            KhachHang khachHang = new KhachHang();
	            khachHang.setMaKH(maKH);
	            khachHang.setTenKH(tenKH);

	            list.add(khachHang);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	
	public float getDiem(String Sdt){
		float result = 0;
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select diemTichLuy from KhachHang where Sdt like '%" + Sdt +"%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int soLuongHD(){
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="  SELECT COUNT(*) FROM [dbo].[HoaDon] WHERE CAST(NgayLapHoaDon AS DATE) = CAST(GETDATE() AS DATE);";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	public static int soLuongSPDaBan(String masp) {
		int soLuongSP =0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select soLuongSPDaBan = SUM(SoLuongSP)\r\n" + 
					"from dbo.CT_HoaDon\r\n" + 
					"where MaSanPham = ?\r\n" + 
					"group by MaSanPham";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,masp);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				soLuongSP = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soLuongSP;
	}
	public int soLuongNhap(String masp) {
		int soLuongSP =0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select Soluong\r\n" + 
					"from dbo.SanPham\r\n" + 
					"where maSp = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,masp);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				soLuongSP = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soLuongSP;
	}
	
	public boolean addCT_HoaDon(String maHoaDon,String maSP,int soLuong) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into dbo.CT_HoaDon(MaHoaDon, MaSanPham, SoLuongSP) values (?,?,?)");
			stmt.setString(1,maHoaDon);
			stmt.setString(2, maSP);
		
			stmt.setInt(3, soLuong);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return n>0;
	}
	public String getTenNV(String ma) {
		String tenDN1 = null;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select TenNhanVien\r\n" + 
					"from dbo.TaiKhoan t join dbo.NhanVien n on t.TenTaiKhoan = n.MaNhanvien\r\n" + 
					"where t.TenTaiKhoan =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,ma);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tenDN1 = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tenDN1;
	}
	public sanPham laySP(String ten, String mau, String kt) {
	    sanPham sp = new sanPham();
	    
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        PreparedStatement stmt = null;
	        String sql = "SELECT  maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP, VAT \r\n"
	        		+ "from SanPham s join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap \r\n"
	        		+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP \r\n"
	        		+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu\r\n"
	        		+ "where Tensp like ? and MauSac = ? AND Size = ?";
	        stmt = con.prepareStatement(sql);
	        
	        stmt.setString(1, ten); // Sử dụng LIKE để tìm kiếm Tensp chứa chuỗi ten
	        stmt.setString(2, mau);
	        stmt.setString(3, kt);
//	        
	        
			ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maSP= rs.getString(1);
					String tenSP=rs.getString(2);
					String NhaCC= rs.getString(3);
					String khuyenMai= rs.getString(4);
					Double giaNhap= rs.getDouble(5);
					int soLuong = rs.getInt(6);
					Date dateNhap = rs.getDate(7);
					String hinhAnh = rs.getString(8);
					String MauSac = rs.getString(9);
					String size = rs.getString(10);
					String chatLieu = rs.getString(11);
					int tinhTrang = rs.getInt(12);
					String donViTinh = rs.getString(13);
					String loaiSP = rs.getString(14);
					Double giaban = rs.getDouble(15);
					int vat = rs.getInt(16);
					Boolean sta = true;
					if (tinhTrang == 1)
					{
						sta = true;
					}
					else
						sta = false;
					sanPham.MauSac mauSac = null;
					sanPham.Size kichThuoc = null;
					for (sanPham.MauSac colr: sanPham.MauSac.values())
					{
						if(MauSac.equalsIgnoreCase(colr.nCo))
						{
							mauSac = colr;
						}
					}
					for (sanPham.Size sizeC: sanPham.Size.values())
					{
						if(size.equalsIgnoreCase(sizeC.nSiz))
						{
							kichThuoc = sizeC;
						}
					}
					LoaiSanPham loai = new LoaiSanPham(loaiSP);
					ChatLieu cl = new ChatLieu(chatLieu);
					NhaCungCap ncc = new NhaCungCap(NhaCC);
					KhuyenMai km = new KhuyenMai(khuyenMai);
					sanPham sp1 = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta, donViTinh, loai, ncc, km, giaban, vat);
					sp = sp1;
				}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sp;
	}
	
	public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc,  String nhanvien, String khachhang, double tongtien ) {
		
		Connection con = ConnectDatabase.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "insert into [dbo].[HoaDon] ([MaHoaDon], [TienKhachDua],[KhachHang], [NhanVien],[TongTien], [NgayLapHoaDon], [DiemTichDuoc]) VALUES (?, ?, ?, ?, ?, GETDATE(),?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, mahd);
            stmt.setDouble(2, tienkd);
            stmt.setString(3, khachhang);
            stmt.setString(4, nhanvien);
            stmt.setDouble(5, tongtien);

            stmt.setFloat(6, diemtichduoc);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
	public boolean updateDiem(double diem, String sdt)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		String sql = "update KhachHang "
				+ "set diemTichLuy =? where Sdt like ?\r\n";
		PreparedStatement stmt = null;
		
		int n =0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, diem);
			stmt.setString(2, sdt);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public int getKMTheoPhanTram(String ma) {
		int phanTram = 0;

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select PhanTramKhuyenMai from KhuyenMai where TenKhuyenMai like '" + ma + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				phanTram = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return phanTram;
	}
	
}