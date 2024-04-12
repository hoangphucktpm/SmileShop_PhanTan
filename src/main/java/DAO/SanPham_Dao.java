package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Database.ConnectDatabase;
import Entity.ChatLieu;
import Entity.KhachHang;
import Entity.KhuyenMai;
import Entity.LoaiKhachHang;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.sanPham;

public class SanPham_Dao {
	public SanPham_Dao() {
		// TODO Auto-generated constructor stub
		ConnectDatabase.getInstance().connect();
	}

//	lấy dữ liệu sản phẩm
	public List<sanPham> getAllSP() {
		List<sanPham> list = new ArrayList<>();
		sanPham chkEnum = new sanPham();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, ChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP ,s.VAT,s.giaBan\r\n"
					+ "				from SanPham s join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap \r\n"
					+ "				join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP \r\n"
					+ "				join ChatLieu c on c.MaChatLieu=s.ChatLieu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);

				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> timTheoGia(double gia1, double gia2) {
		List<sanPham> list = new ArrayList<>();
		sanPham chkEnum = new sanPham();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP ,s.VAT,s.giaBan\r\n"
					+ "from SanPham s join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap \r\n"
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP \r\n"
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where s.giaBan >= " + gia1 + " and s.giaBan <= "
					+ gia2;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);

				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Thêm sản phẩm vào cơ sở dữ liệu
	 */
	public boolean them(String maSP, String tenSP, String nhaCungCap, String km, double giaBan, int soluong,
			Date ngayNhap, String hinhAnh, String color, String size, String chatLieu, int tinhTrang, String dvt,
			String loaiSP, int VAT, double giaBanRa) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			if (km.equalsIgnoreCase("none") || km.equalsIgnoreCase(""))
			{
				
				stmt = con.prepareStatement(
						"INSERT INTO SanPham (maSp, Tensp, NhaCungCap,  Gianhap, Soluong, Ngaynhap, MauSac, Size, ChatLieu, TinhTrang, DonViTinh, LoaiSanPham, VAT, giaBan, Hinhanh)\r\n"
						+ "							VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, maSP);
				stmt.setString(2, tenSP);
				stmt.setString(3, nhaCungCap);;
				stmt.setDouble(4, giaBan);
				stmt.setInt(5, soluong);
				stmt.setDate(6, ngayNhap);
				stmt.setString(7, color);
				stmt.setString(8, size);
				stmt.setString(9, chatLieu);
				stmt.setInt(10, tinhTrang);
				stmt.setString(11, dvt);
				stmt.setString(12, loaiSP);
				stmt.setInt(13, VAT);
				stmt.setDouble(14, giaBanRa);
				stmt.setString(15, hinhAnh);
				n = stmt.executeUpdate();
			}
			else
			{
				
				stmt = con.prepareStatement(
						"INSERT INTO SanPham (maSp, Tensp, NhaCungCap,  KhuyenMai ,  Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, ChatLieu, TinhTrang, DonViTinh, LoaiSanPham, VAT, giaBan)\r\n"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, maSP);
				stmt.setString(2, tenSP);
				stmt.setString(3, nhaCungCap);
				stmt.setString(4, km);
				stmt.setDouble(5, giaBan);
				stmt.setInt(6, soluong);
				stmt.setDate(7, ngayNhap);
				stmt.setString(8, hinhAnh);
				stmt.setString(9, color);
				stmt.setString(10, size);
				stmt.setString(11, chatLieu);
				stmt.setInt(12, tinhTrang);
				stmt.setString(13, dvt);
				stmt.setString(14, loaiSP);
				stmt.setInt(15, VAT);
				stmt.setDouble(16, giaBanRa);
				n = stmt.executeUpdate();
			}
				
		} catch (SQLException e3) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e3);
		}
		return n > 0;
	}

	/**
	 * Sua sản phẩm trong cơ sở dữ liệu
	 */
	public boolean sua(String tenSP, String nhaCungCap, String km, double giaBan, int soluong, Date ngayNhap,
			String hinhAnh, String color, String size, String chatLieu, int tinhTrang, String dvt, String loaiSP,
			int VAT, double giaBanRa, String maSP) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			if (km.equalsIgnoreCase("none") || km.equalsIgnoreCase(""))
			{
				
				String sql = "UPDATE SanPham SET Tensp = ?, NhaCungCap = ?, Gianhap = ?, Soluong = ?, Ngaynhap = ?, KhuyenMai = null, Hinhanh = ?, MauSac = ?, Size = ?, ChatLieu = ?, "
						+ "TinhTrang = ?, DonViTinh = ?, LoaiSanPham = ?,VAT = ?, giaBan = ? WHERE maSp = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tenSP);
				stmt.setString(2, nhaCungCap);
				stmt.setDouble(3, giaBan);
				stmt.setInt(4, soluong);
				stmt.setDate(5, ngayNhap);
				stmt.setString(6, hinhAnh);
				stmt.setString(7, color);
				stmt.setString(8, size);
				stmt.setString(9, chatLieu);
				stmt.setInt(10, tinhTrang);
				stmt.setString(11, dvt);
				stmt.setString(12, loaiSP);
				stmt.setFloat(13, VAT);
				stmt.setDouble(14, giaBanRa);
				stmt.setString(15, maSP);
				
				n = stmt.executeUpdate();
			}
			else {
				String sql = "UPDATE SanPham SET Tensp = ?, NhaCungCap = ?,KhuyenMai = ?, Gianhap = ?, Soluong = ?, Ngaynhap = ?, Hinhanh = ?, MauSac = ?, Size = ?, ChatLieu = ?, "
						+ "TinhTrang = ?, DonViTinh = ?, LoaiSanPham = ?,VAT = ?, giaBan = ? WHERE maSp = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, maSP);
				stmt.setString(2, tenSP);
				stmt.setString(3, nhaCungCap);;
				stmt.setDouble(4, giaBan);
				stmt.setInt(5, soluong);
				stmt.setDate(6, ngayNhap);
				stmt.setString(7, hinhAnh);
				stmt.setString(8, color);
				stmt.setString(9, size);
				stmt.setString(10, chatLieu);
				stmt.setInt(11, tinhTrang);
				stmt.setString(12, dvt);
				stmt.setString(13, loaiSP);
				stmt.setInt(14, VAT);
				stmt.setDouble(15, giaBanRa);
				n = stmt.executeUpdate();
				
				
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi ra màn hình console để theo dõi và xử lý lỗi.
		}
		return n > 0;
	}

	public boolean suaKhongAnh(String tenSP, String nhaCungCap, String km, double giaBan, int soluong, Date ngayNhap,
			String color, String size, String chatLieu, int tinhTrang, String dvt, String loaiSP, int VAT,
			double giaBanRa, String maSP) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			if (km.equalsIgnoreCase("none") || km.equalsIgnoreCase(""))
			{
				String sql = "UPDATE SanPham SET Tensp = ?, NhaCungCap = ?, Gianhap = ?, Soluong = ?, "
						+ "Ngaynhap = ?, MauSac = ?, Size = ?, ChatLieu = ?, "
						+ "TinhTrang = ?, DonViTinh = ?, LoaiSanPham = ?,VAT = ?, giaBan = ? "
						+ "WHERE maSp = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tenSP);
				stmt.setString(2, nhaCungCap);
				stmt.setDouble(3, giaBan);
				stmt.setInt(4, soluong);
				 if (soluong != 0) {
		                stmt.setDate(5,ngayNhap = new java.sql.Date(System.currentTimeMillis()));
		            } else {
		                stmt.setDate(5, ngayNhap);
		            }
				

				stmt.setString(6, color);
				stmt.setString(7, size);
				stmt.setString(8, chatLieu);
				stmt.setInt(9, tinhTrang);
				stmt.setString(10, dvt);
				stmt.setString(11, loaiSP);
				stmt.setFloat(12, VAT);
				stmt.setDouble(13, giaBanRa);
				stmt.setString(14, maSP);
				
				n = stmt.executeUpdate();
			}else {
				
				String sql = "UPDATE SanPham SET Tensp = ?, NhaCungCap = ?, "
						+ "Gianhap = ?, Soluong = ?, Ngaynhap = ?,  MauSac = ?, Size = ?, "
						+ "ChatLieu = ?, TinhTrang = ?, DonViTinh = ?, LoaiSanPham = ?,"
						+ "VAT = ?, giaBan = ? WHERE maSp = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tenSP);
				stmt.setString(2, nhaCungCap);
//				stmt.setString(3, km);
				stmt.setDouble(3, giaBan);
				stmt.setInt(4, soluong);
				 if (soluong != 0) {
		                stmt.setDate(5,ngayNhap = new java.sql.Date(System.currentTimeMillis()));
		            } else {
		                stmt.setDate(5, ngayNhap);
		            }
				stmt.setString(6, color);
				stmt.setString(7, size);
				stmt.setString(8, chatLieu);
				stmt.setInt(9, tinhTrang);
				stmt.setString(10, dvt);
				stmt.setString(11, loaiSP);
				stmt.setFloat(12, VAT);
				stmt.setDouble(13, giaBanRa);
				stmt.setString(14, maSP);			
				n = stmt.executeUpdate();
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi ra màn hình console để theo dõi và xử lý lỗi.
		}
		return n > 0;
	}


//	Lấy danh sách loại sản phẩm
	public List<LoaiSanPham> getLoaiSP() {
		List<LoaiSanPham> list = new ArrayList<LoaiSanPham>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select *\r\n" + "from [dbo].[LoaiSanPham]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoai = rs.getString(1);
				String tenLoai = rs.getString(2);
				LoaiSanPham l = new LoaiSanPham(maLoai, tenLoai);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

//	Lấy danh sách chất liệu
	public List<ChatLieu> getChatLieu() {
		List<ChatLieu> list = new ArrayList<ChatLieu>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select *\r\n" + "from [dbo].[ChatLieu]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String moTa = rs.getString(3);
				ChatLieu cl = new ChatLieu(ma, ten, moTa);
				list.add(cl);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

//	Lấy danh sách nhà cung cấp
	public List<NhaCungCap> getTenNCC() {
		List<NhaCungCap> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from [dbo].[NhaCungCap] ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String diaChi = rs.getString(5);
				int tinhTrang = rs.getInt(6);
				NhaCungCap ncc = new NhaCungCap(ma, ten, sdt, email, diaChi, tinhTrang);
				list.add(ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

//	Lấy danh sách khuyen mai
	public List<KhuyenMai> getKMTheoTen() {

		List<KhuyenMai> list = new ArrayList<>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select * from KhuyenMai ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maKhuyenMai = rs.getString(1);
				String tenKhuyenMai = rs.getString(2);
				int phanTram = rs.getInt(3);
				Date ngayBatDau = rs.getDate(4);
				Date ngayKetThuc = rs.getDate(5);
				boolean trangThai = rs.getBoolean(6);
				int soLuongSanPhamKM = rs.getInt(7);
				KhuyenMai km = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai,
						soLuongSanPhamKM);
				list.add(km);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getKMTheoTen(String TenKM) {
		String ten = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenKhuyenMai from [dbo].[KhuyenMai] where MaKhuyenMai like N'%" + TenKM + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ten;
	}
	public String getKMTheoMaKM() {
		String ma = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select km.MaKhuyenMai from SanPham s join KhuyenMai km on s.KhuyenMai = km.MaKhuyenMai";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ma = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ma;
	}
	public int getKMTheoPhanTramKM() {
		int pt = 0;

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select km.PhanTramKhuyenMai from SanPham s join KhuyenMai km on s.KhuyenMai = km.MaKhuyenMai";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				pt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pt;
	}
	public int getKMTheoPhanTram(String ma) {
		int phanTram = 0;

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = " select PhanTramKhuyenMai from [dbo].[KhuyenMai] km join SanPham s on s.KhuyenMai=km.MaKhuyenMai where maSp like N'%" + ma + "%'";
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
	

//	lấy số lượng sản phẩm
	public int soLuong() {
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select count(*) from [dbo].[SanPham]";
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

//	Lấy ra tình trạng thuế
	public int vat(String ma) {
		int thue = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select VAT from [dbo].[SanPham] where maSp like '%" + ma + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				thue = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thue;
	}

	/**
	 * Thêm loại sản phẩm vào cơ sở dữ liệu
	 */
	public boolean themLoaiSP(String maSP, String tenSP) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO LoaiSanPham (MaLoaiSP, TenLoaiSP)\r\n" + "VALUES (?, ?);");
			stmt.setString(1, maSP);
			stmt.setString(2, tenSP);

			n = stmt.executeUpdate();
		} catch (SQLException e3) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e3);
		}
		return n > 0;
	}

//	lấy số lượng loại sản phẩm
	public int soLuongLSP() {
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select count(*) from [dbo].[LoaiSanPham]";
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

	/**
	 * Thêm chất liệu vào cơ sở dữ liệu
	 */
	public boolean themChatLieu(String maSP, String tenSP, String moTa) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con
					.prepareStatement("INSERT INTO ChatLieu (MaChatLieu, TenChatLieu, MoTa)\r\n" + "VALUES (?, ?, ?);");
			stmt.setString(1, maSP);
			stmt.setString(2, tenSP);
			stmt.setString(3, moTa);

			n = stmt.executeUpdate();
		} catch (SQLException e3) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e3);
		}
		return n > 0;
	}

//	lấy số lượng loại sản phẩm
	public int soLuongChatLieu() {
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select count(*) from [dbo].[ChatLieu]";
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

	// get tenloaisp
	public String getTenLoaiSP(String maSP) {
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenLoaiSP from SanPham sp join LoaiSanPham lsp on sp.LoaiSanPham = lsp.MaLoaiSP  where maSp like '%"
					+ maSP + "%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get Ten SP
	public String getTen(String maSP) {

		String result = "";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select Tensp from [dbo].SanPham where maSp like  N'%" + maSP + "%'";
			;
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
			while (rs.next()) {

				result = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// get ten nha cung cap
	public String getTenNhaCC(String maSP) {
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenNhaCungCap from SanPham sp join NhaCungCap ncc on sp.NhaCungCap = ncc.MaNhaCungCap  where maSp like '%"
					+ maSP + "%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get ten chat lieu
	public String getTenCL(String maSP) {
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenChatLieu from SanPham sp join ChatLieu cl on sp.ChatLieu = cl.MaChatLieu  where maSp like '%"
					+ maSP + "%'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public sanPham getMa(String Ma) {
		sanPham list = new sanPham();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where maSp like N'%" + Ma + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);
				list = sp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public sanPham getTenSP(String Ten) {
		sanPham list = new sanPham();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where Tensp like N'%" + Ten + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list = sp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> getlistTenLoaiSP(String lsp) {
		List<sanPham> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where TenLoaiSP like N'%" + lsp + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> getlistTenNCC(String nccap) {
		List<sanPham> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where TenNhaCungCap like N'%" + nccap + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> getlistTenCL(String ChatLieu) {
		List<sanPham> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where TenChatLieu like N'%" + ChatLieu + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> getlistTenMauSac(String MS) {
		List<sanPham> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP ,s.VAT,s.giaBan\r\n"
					+ "					from SanPham s \r\n"
					+ "					join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap \r\n"
					+ "					join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP \r\n"
					+ "					join ChatLieu c on c.MaChatLieu=s.ChatLieu where MauSac like N'%" + MS + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);
				list.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<sanPham> getlistSize(String kthuoc) {
		List<sanPham> list = new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "Select maSp, Tensp, ncc.TenNhaCungCap, KhuyenMai, Gianhap, Soluong, Ngaynhap, Hinhanh, MauSac, Size, c.TenChatLieu, s.TinhTrang, DonViTinh, lsp.TenLoaiSP,s.VAT,s.giaBan "
					+ "from SanPham s " + "join NhaCungCap ncc on s.NhaCungCap = ncc.MaNhaCungCap "
					+ "join LoaiSanPham lsp on s.LoaiSanPham = lsp.MaLoaiSP "
					+ "join ChatLieu c on c.MaChatLieu=s.ChatLieu where Size like N'%" + kthuoc + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String NhaCC = rs.getString(3);
				String khuyenMai = rs.getString(4);
				Double giaNhap = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				Date dateNhap = rs.getDate(7);
				String hinhAnh = rs.getString(8);
				String MauSac = rs.getString(9);
				String size = rs.getString(10);
				String chatLieu = rs.getString(11);
				int tinhTrang = rs.getInt(12);
				String donViTinh = rs.getString(13);
				String loaiSP = rs.getString(14);
				Boolean sta = true;
				if (tinhTrang == 1) {
					sta = true;
				} else
					sta = false;
				sanPham.MauSac mauSac = null;
				sanPham.Size kichThuoc = null;
				for (sanPham.MauSac mau : sanPham.MauSac.values()) {
					if (MauSac.equalsIgnoreCase(mau.nCo)) {
						mauSac = mau;
					}
				}
				for (sanPham.Size kt : sanPham.Size.values()) {
					if (size.equalsIgnoreCase(kt.nSiz)) {
						kichThuoc = kt;
					}
				}
				LoaiSanPham loai = new LoaiSanPham(loaiSP);
				ChatLieu cl = new ChatLieu(chatLieu);
				NhaCungCap ncc = new NhaCungCap(NhaCC);
				KhuyenMai km = new KhuyenMai(khuyenMai);
				int VAT = rs.getInt(15);
				Double giaBan = rs.getDouble(16);
				sanPham sp = new sanPham(maSP, ten, giaNhap, soLuong, dateNhap, hinhAnh, mauSac, kichThuoc, cl, sta,
						donViTinh, loai, ncc, km, giaBan,VAT);

				list.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String getMoTaChatLieu (String ma)
	{
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select MoTa from ChatLieu where MaChatLieu = '" + ma + "'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getTenChatLieu (String ma)
	{
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select TenChatLieu from ChatLieu where MaChatLieu like '"+ ma + "'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getMaChatLieu (String ten, String moTa)
	{
		String result = "";

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql = "select MaChatLieu from ChatLieu where TenChatLieu like N'"+ ten + "' and MoTa like N'" + moTa + "'";
			PreparedStatement preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	}

