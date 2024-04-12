package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;
import Entity.ChatLieu;
import Entity.ChiTietHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.KhuyenMai;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.NhanVien;
import Entity.sanPham;


public class XemHoaDon_Dao {


	public XemHoaDon_Dao() {
		ConnectDatabase.getInstance().connect();
	}
	
	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> list= new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="Select MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc \r\n"
					+ "from HoaDon hd join KhachHang kh on hd.KhachHang = kh.MaKH join NhanVien nv on hd.NhanVien = nv.MaNhanvien\r\n"
					+ "order by NgayLapHoaDon"
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
	public List<ChiTietHoaDon> getCT_HoaDon(String mahd) {
	    List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        String sql = "SELECT SanPham.Tensp, SanPham.Size, SanPham.MauSac, ChatLieu.TenChatLieu, \r\n"
	        		+ "	            SanPham.GiaBan, SanPham.Gianhap, CT_HoaDon.SoLuongSP, ISNULL(SanPham.VAT, 0) as VAT, \r\n"
	        		+ "				ISNULL(KhuyenMai.PhanTramKhuyenMai, 0) as KhuyenMai, (SanPham.GiaBan * CT_HoaDon.SoLuongSP) as ThanhTien\r\n"
	        		+ "	            FROM CT_HoaDon\r\n"
	        		+ "	            LEFT JOIN SanPham ON CT_HoaDon.MaSanPham = SanPham.maSp \r\n"
	        		+ "	            LEFT JOIN KhuyenMai ON KhuyenMai.MaKhuyenMai = SanPham.KhuyenMai\r\n"
	        		+ "	            LEFT JOIN ChatLieu ON SanPham.ChatLieu = ChatLieu.MaChatLieu \r\n"
	        		+ "" +
	        		"WHERE MaHoaDon = ?";
	        PreparedStatement preStm = con.prepareStatement(sql);
	        preStm.setString(1, mahd);
	        ResultSet rs = preStm.executeQuery();
	        while (rs.next()) {
	        	  
	            String tensp = rs.getString(1);
	            String size = rs.getString(2);
	            String mauSac = rs.getString(3);
	            String chatLieu = rs.getString(4);
	            double giaBan = rs.getDouble(5);
	            double giaNhap = rs.getDouble(6);
	            int soLuong = rs.getInt(7);
	            int vat = rs.getInt(8);
	            int khuyenMai = rs.getInt(9);
	            double thanhTien = rs.getDouble(10);

	            // Create ChiTietHoaDon and sanPham objects
	            ChiTietHoaDon cthd = new ChiTietHoaDon();
	            sanPham sp = new sanPham();
	            
	            // Populate sanPham object
	            sp.setTenSP(tensp);
	            sanPham.MauSac ms = null;
	            sanPham.Size kichThuoc = null;
	            for (sanPham.MauSac mau : sanPham.MauSac.values()) {
	                if (mauSac.equalsIgnoreCase(mau.nCo)) {
	                    ms = mau;
	                }
	            }
	            for (sanPham.Size kt : sanPham.Size.values()) {
	                if (size.equalsIgnoreCase(kt.nSiz)) {
	                    kichThuoc = kt;
	                }
	            }
	            sp.setMauSac(ms);
	            sp.setSize(kichThuoc);
	            sp.setSoLuong(soLuong);
	            ChatLieu cl = new ChatLieu();
	            cl.setTenChatLieu(chatLieu);
	            KhuyenMai km = new KhuyenMai();
	            km.setPhanTram(khuyenMai);
	            sp.setKhuyenMai(km);
	            sp.setChatLieu(cl);
	            sp.setGiaBan(giaBan);
	            sp.setVAT(vat);
	            sp.setGiaNhap(giaNhap);
	            cthd.setSoLuongSP(soLuong); 

	         
	            cthd.setSanPham(sp); 
	       

	            list.add(cthd);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	/**
	 * Lấy tên nhân viên khi biết mã nhân vien
	 * @param ma
	 * @return tên nhân viên
	 */
	public String getTenNV(String manv){
		String ten="";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select [TenNhanVien]\r\n" + 
					"from [dbo].[NhanVien]\r\n" 
					+ "  where MaNhanVien like '%" + manv + "%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
			while(rs.next()) {
				ten = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ten;
	}
	
	/**
	 * Lấy tên khách hàng khi biết mã khách hàng 
	 * @param khachHang
	 * @return tên khách hàng
	 */
	public String getTenKH(String makh){
		String ten="";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select [TenKH]\r\n" + 
					"from [dbo].[KhachHang]\r\n" + 
					"where MaKH like N'%" + makh + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String t= rs.getString(1);
				ten=t;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ten;
	}
	
//	Lấy  ra hóa đơn theo mã

	 public HoaDon getHDTHeoMa(String MaHD) 
	 {
		 HoaDon list = new HoaDon();
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="Select MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc from HoaDon hd join KhachHang kh on hd.KhachHang = kh.MaKH join NhanVien nv on hd.NhanVien = nv.MaNhanvien where [MaHoaDon] like '"+MaHD+"' ";
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
					list = new HoaDon(mahd, ngayLap, tienKD, diemTich, nv, kh, tongTien);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
	 
//		Lấy  ra hóa đơn theo tên nv

		 public List<HoaDon> getHDTheoTenNV(String tennv) 
		 {
			 List<HoaDon> list = new ArrayList<HoaDon>();
				try {
					Connection con = ConnectDatabase.getInstance().getConnection();
					String sql="Select MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc from HoaDon hd join KhachHang kh on hd.KhachHang = kh.MaKH join NhanVien nv on hd.NhanVien = nv.MaNhanvien where [TenNhanVien] like N'"+tennv+"' ";
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
						HoaDon hd = new HoaDon(mahd, ngayLap, tienKD, diemTich, nv, kh, tongTien);
						list.add(hd);

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return list;
			}
		 
		 
//			Lấy  ra hóa đơn theo tên kh

		 public List<HoaDon> getHDTheoTenKH(String tenkh) 
		 {
			 List<HoaDon> list = new ArrayList<HoaDon>();
				try {
					Connection con = ConnectDatabase.getInstance().getConnection();
					String sql="Select MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc from HoaDon hd join KhachHang kh on hd.KhachHang = kh.MaKH join NhanVien nv on hd.NhanVien = nv.MaNhanvien where [TenKH] like N'"+tenkh+"' ";
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
						HoaDon hd = new HoaDon(mahd, ngayLap, tienKD, diemTich, nv, kh, tongTien);
						list.add(hd);

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return list;
			}
		 
//			Lấy  ra hóa đơn theo ngày

		 public List<HoaDon> getHDTheoNgayLap(int ngay,int thang,int nam) 
		 {
			 List<HoaDon> list= new ArrayList<HoaDon>();	
				try {
					Connection con = ConnectDatabase.getInstance().getConnection();
					String sql= "  SELECT MaHoaDon, TienKhachDua, kh.TenKH, nv.TenNhanVien, TongTien, NgayLapHoaDon, DiemTichDuoc\r\n"
							+ "							FROM HoaDon hd JOIN KhachHang kh ON hd.KhachHang = kh.MaKH JOIN NhanVien nv ON hd.NhanVien = nv.MaNhanvien\r\n"
							+ "							WHERE DAY(NgayLapHoaDon) = ? AND MONTH(NgayLapHoaDon) = ? AND YEAR(NgayLapHoaDon) = ?";
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					preparedStatement.setInt(1, ngay);
					preparedStatement.setInt(2, thang);
					preparedStatement.setInt(3, nam);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()) {
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
	 }

