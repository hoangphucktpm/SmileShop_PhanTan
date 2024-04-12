package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Database.ConnectDatabase;
import Entity.ChatLieu;
import Entity.KhuyenMai;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.ThongKeSanPham;
import Entity.sanPham;

public class TinhTrangSanPham_Dao {
	public TinhTrangSanPham_Dao() {
		// TODO Auto-generated constructor stub
		 ConnectDatabase.getInstance().connect();
	}
//	láº¥y dá»¯ liá»‡u sáº£n pháº©m Ä‘áº£ háº¿t hÃ ng
	public List<ThongKeSanPham> getSPOUT(){
		List<ThongKeSanPham> list= new ArrayList<>();
		sanPham chkEnum = new sanPham();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size\r\n"
					+ "from SanPham "
					+ "where Soluong = 0";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String loai = rs.getString(3);
				int soLuong = rs.getInt(4);
				Date ngayNhap = rs.getDate(5);
				Double giaNhap = rs.getDouble(6);
				Double giaBan = rs.getDouble(7);
				String mauSac = rs.getString(8);
				String kichThuoc = rs.getString(9);
				String loaiSP = loaiSP(loai);
				ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, ngayNhap, giaNhap, giaBan, mauSac, kichThuoc);
				
				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
//	Lấy tên loại
	public String loaiSP(String ma)
	{
		String loai = "";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select TenLoaiSP\r\n"
					+ "from LoaiSanPham where MaLoaiSP like '%"+ ma +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				loai = rs.getString(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loai;
	}
	public int vat(String ma)
	{
		int thue = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select VAT from [dbo].[SanPham] where maSp like '%"+ ma +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				thue =rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thue;
	}
//	Láº¥y ra sáº£n pháº©m cÃ²n trong kho
	public List<ThongKeSanPham> getSPRE(){
		List<ThongKeSanPham> list= new ArrayList<>();
		sanPham chkEnum = new sanPham();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size\r\n"
					+ "from SanPham "
					+ "where Soluong > 0";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String loai = rs.getString(3);
				int soLuong = rs.getInt(4);
				Date ngayNhap = rs.getDate(5);
				Double giaNhap = rs.getDouble(6);
				Double giaBan = rs.getDouble(7);
				String mauSac = rs.getString(8);
				String kichThuoc = rs.getString(9);
				String loaiSP = loaiSP(loai);
				ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, ngayNhap, giaNhap, giaBan, mauSac, kichThuoc);
				
				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	Láº¥y ra sáº£n pháº©m sáº¯p háº¿t
	public List<ThongKeSanPham> getAlMOUT(){
		List<ThongKeSanPham> list= new ArrayList<>();
		sanPham chkEnum = new sanPham();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size\r\n"
					+ "from SanPham "
					+ "where Soluong <= 10 and Soluong > 0";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String loai = rs.getString(3);
				int soLuong = rs.getInt(4);
				Date ngayNhap = rs.getDate(5);
				Double giaNhap = rs.getDouble(6);
				Double giaBan = rs.getDouble(7);
				String mauSac = rs.getString(8);
				String kichThuoc = rs.getString(9);
				String loaiSP = loaiSP(loai);
				ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, ngayNhap, giaNhap, giaBan, mauSac, kichThuoc);
				
				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	Láº¥y ra sáº£n pháº©m nháº­p theo ngÃ y
	public List<ThongKeSanPham> getNewAdd(java.util.Date ngayNhap) {
	    List<ThongKeSanPham> list = new ArrayList<>();
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        String sql = "select maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size\r\n"
						+ "from SanPham "
						+  "WHERE Ngaynhap = CONVERT(date, ?, 105)";
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        String ngayNhapTXT = sdf.format(ngayNhap);
	        preparedStatement.setString(1, ngayNhapTXT);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String loai = rs.getString(3);
				int soLuong = rs.getInt(4);
				Date dateN = rs.getDate(5);
				Double giaNhap = rs.getDouble(6);
				Double giaBan = rs.getDouble(7);
				String mauSac = rs.getString(8);
				String kichThuoc = rs.getString(9);
				String loaiSP = loaiSP(loai);
				ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, dateN, giaNhap, giaBan, mauSac, kichThuoc);
				
				list.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
//	Láº¥y ra sáº£n pháº©m bÃ¡n khÃ´ng Ä‘Æ°á»£c, nháº­p Ä‘Æ°á»£c hÆ¡n 6 thÃ¡ng
	public List<ThongKeSanPham> getOld(){
		List<ThongKeSanPham> list= new ArrayList<>();
		sanPham chkEnum = new sanPham();
		LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size\r\n"
					+ "from SanPham "
					+ "                     WHERE Soluong > 0 AND (Ngaynhap <= ? OR Ngaynhap IS NULL)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDate(1, java.sql.Date.valueOf(sixMonthsAgo));
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
					String ma = rs.getString(1);
					String ten = rs.getString(2);
					String loai = rs.getString(3);
					int soLuong = rs.getInt(4);
					Date dateN = rs.getDate(5);
					Double giaNhap = rs.getDouble(6);
					Double giaBan = rs.getDouble(7);
					String mauSac = rs.getString(8);
					String kichThuoc = rs.getString(9);
					String loaiSP = loaiSP(loai);
					ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, dateN, giaNhap, giaBan, mauSac, kichThuoc);
					
					list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
//	Lấy ra danh sách sản phẩm đã bán theo ngày
	public List<ThongKeSanPham> getSold(int day, int month, int year){
		List<ThongKeSanPham> list= new ArrayList<>();
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select distinct maSp, Tensp, LoaiSanPham, Soluong, Ngaynhap, Gianhap, GiaBan, MauSac, Size from SanPham s\r\n"
					+ "join CT_HoaDon ct on s.maSp = ct.MaSanPham\r\n"
					+ "join HoaDon h on h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "					where day(NgayLapHoaDon) = ? and MONTH(NgayLapHoaDon) = ? and year(NgayLapHoaDon) = ?" ;
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, day);
			preparedStatement.setInt(2, month);
			preparedStatement.setInt(3, year);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
					String ma = rs.getString(1);
					String ten = rs.getString(2);
					String loai = rs.getString(3);
					int soLuong = rs.getInt(4);
					Date dateN = rs.getDate(5);
					Double giaNhap = rs.getDouble(6);
					Double giaBan = rs.getDouble(7);
					String mauSac = rs.getString(8);
					String kichThuoc = rs.getString(9);
					String loaiSP = loaiSP(loai);
					ThongKeSanPham sp = new ThongKeSanPham(ma, ten, loaiSP, soLuong, dateN, giaNhap, giaBan, mauSac, kichThuoc);

					list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
//	lấy số lượng sản phẩm đã bán
	public int soLuongBan(String ma){
		int s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select sum(SoLuongSP) as soLuong from SanPham s\r\n"
					+ "join CT_HoaDon ct on s.maSp = ct.MaSanPham\r\n"
					+ "join HoaDon h on h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "where MaSanPham like '%" + ma + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				s = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public double tongTienBan(int top)
	{
		double sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="	select top "+ top +" Tensp, sum(TongTien) as Tien from HoaDon h\r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon=ct.MaHoaDon\r\n"
					+ "	join SanPham s on s.maSp = ct.MaSanPham\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by Tensp\r\n"
					+ "	order by Tien DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				sum = rs.getDouble(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	public String sanPhamTop(int top)
	{
		String name = "";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="	select top "+ top +" Tensp, sum(TongTien) as Tien from HoaDon h\r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon=ct.MaHoaDon\r\n"
					+ "	join SanPham s on s.maSp = ct.MaSanPham\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by Tensp\r\n"
					+ "	order by Tien DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
}