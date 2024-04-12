package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;
import Entity.ChatLieu;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.KhuyenMai;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.NhanVien;
import Entity.ThongKeDoanhThu;
import Entity.sanPham;

public class ThongKeDoanhThu_Dao {
	public ThongKeDoanhThu_Dao()
	{
		ConnectDatabase.getInstance().connect();
	}

//	Lấy doanh thu theo quy
	public List<ThongKeDoanhThu> getDTQuy(int quy, int  ca){
		List<ThongKeDoanhThu> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="SELECT s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, SUM(ct.SoLuongSP) as TongSoLuong, s.GiaBan\r\n"
					+ "FROM SanPham s \r\n"
					+ "JOIN CT_HoaDon ct ON s.maSp = ct.MaSanPham \r\n"
					+ "JOIN HoaDon h ON h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "JOIN NhanVien n ON n.MaNhanvien = h.NhanVien\r\n"
					+ "WHERE DATEPART(QUARTER, NgayLapHoaDon) = "+ quy +" AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? and year(NgayLapHoaDon) = YEAR(GETDATE())\r\n"
					+ "GROUP BY s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, s.GiaBan";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			if (ca == 0)
			{
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(1, 8);
				preparedStatement.setInt(2, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(1, 15);
				preparedStatement.setInt(2, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String mau = rs.getString(3);
				String size = rs.getString(4);
				String  khuyenMai = rs.getString(5);
				Double giaNhap = rs.getDouble(6);
				int soLuong = rs.getInt(7);
				int soLuongBan = rs.getInt(8);
				Double giaBan = rs.getDouble(9);
				int km = khuyenMai(khuyenMai);
				ThongKeDoanhThu tk = new ThongKeDoanhThu(ma, ten, mau, size, km, giaNhap, soLuong, soLuongBan, giaBan);
				list.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public double tongDoanhThuTheoQuy (int quy, int  ca)
	{
		double s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="SELECT sum (h.TongTien)\r\n"
						+ "from HoaDon h\r\n"
						+ "join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
						+ "WHERE DATEPART(QUARTER, NgayLapHoaDon) = "+ quy +" AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? and year(NgayLapHoaDon) = YEAR(GETDATE())";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				if (ca == 0)
				{
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, 23);
				}
				else if (ca == 1)
				{
					preparedStatement.setInt(1, 8);
					preparedStatement.setInt(2, 15);
				}
				else if (ca == 2)
				{
					preparedStatement.setInt(1, 15);
					preparedStatement.setInt(2, 22);
				}
				ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				s = rs.getDouble(1);
			}
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
//	Lấy ra khuyến mãi
	public int khuyenMai (String ma)
	{
		int s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			if (ma == null)
			{
				s = 0;
			}
			else
			{
				String sql="select PhanTramKhuyenMai from KhuyenMai where MaKhuyenMai like '%" +ma + "%'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				s = rs.getInt(1);
			}

			}
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
//	Lấy doanh thu theo tháng
	public List<ThongKeDoanhThu> getDTThang(int month, int  ca){
		List<ThongKeDoanhThu> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="SELECT s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, SUM(ct.SoLuongSP) as TongSoLuong, s.GiaBan\r\n"
					+ "FROM SanPham s \r\n"
					+ "JOIN CT_HoaDon ct ON s.maSp = ct.MaSanPham \r\n"
					+ "JOIN HoaDon h ON h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "JOIN NhanVien n ON n.MaNhanvien = h.NhanVien\r\n"
					+ "where MONTH(NgayLapHoaDon) = " + month +" AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? and year(NgayLapHoaDon) = YEAR(GETDATE())\r\n"
					+ "GROUP BY s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, s.GiaBan";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			if (ca == 0)
			{
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(1, 8);
				preparedStatement.setInt(2, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(1, 15);
				preparedStatement.setInt(2, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String mau = rs.getString(3);
				String size = rs.getString(4);
				String  khuyenMai = rs.getString(5);
				Double giaNhap = rs.getDouble(6);
				int soLuong = rs.getInt(7);
				int soLuongBan = rs.getInt(8);
				Double tongTien = rs.getDouble(9);
				int km = khuyenMai(khuyenMai);
				ThongKeDoanhThu tk = new ThongKeDoanhThu(ma, ten, mau, size, km, giaNhap, soLuong, soLuongBan, tongTien);
				list.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public double tongDoanhThuTheoThang(int month, int  ca)
	{
		double s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="SELECT sum (h.TongTien)\r\n"
						+ "from HoaDon h\r\n"
						+ "join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
						+ "					WHERE\r\n"
						+ "				MONTH(NgayLapHoaDon) = " + month +  " AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? and year(NgayLapHoaDon) = YEAR(GETDATE())";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				if (ca == 0)
				{
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, 23);
				}
				else if (ca == 1)
				{
					preparedStatement.setInt(1, 8);
					preparedStatement.setInt(2, 15);
				}
				else if (ca == 2)
				{
					preparedStatement.setInt(1, 15);
					preparedStatement.setInt(2, 22);
				}
				ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				s = rs.getDouble(1);
			}
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
//	Lấy doanh thu theo năm
	public List<ThongKeDoanhThu> getDTNam(int year, int ca){
		List<ThongKeDoanhThu> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="SELECT s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, SUM(ct.SoLuongSP) as TongSoLuong, s.GiaBan\r\n"
					+ "					FROM SanPham s\r\n"
					+ "					JOIN CT_HoaDon ct ON s.maSp = ct.MaSanPham\r\n"
					+ "					JOIN HoaDon h ON h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "					JOIN NhanVien n ON n.MaNhanvien = h.NhanVien\r\n"
					+ "					where Year(NgayLapHoaDon) = "+ year +" AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? \r\n"
					+ "					GROUP BY s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, s.GiaBan";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			if (ca == 0)
			{
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(1, 8);
				preparedStatement.setInt(2, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(1, 15);
				preparedStatement.setInt(2, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String mau = rs.getString(3);
				String size = rs.getString(4);
				String  khuyenMai = rs.getString(5);
				Double giaNhap = rs.getDouble(6);
				int soLuong = rs.getInt(7);
				int soLuongBan = rs.getInt(8);
				Double tongTien = rs.getDouble(9);
				int km = khuyenMai(khuyenMai);
				ThongKeDoanhThu tk = new ThongKeDoanhThu(ma, ten, mau, size, km, giaNhap, soLuong, soLuongBan, tongTien);
				list.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public double tongDoanhThuTheoNam(int year, int  ca)
	{
		double s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="SELECT sum (h.TongTien)\r\n"
						+ "from HoaDon h\r\n"
						+ "join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
						+ "					WHERE\r\n"
						+ "				year(NgayLapHoaDon) = " + year +  " AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				if (ca == 0)
				{
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, 23);
				}
				else if (ca == 1)
				{
					preparedStatement.setInt(1, 8);
					preparedStatement.setInt(2, 15);
				}
				else if (ca == 2)
				{
					preparedStatement.setInt(1, 15);
					preparedStatement.setInt(2, 22);
				}
				ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				s = rs.getDouble(1);
			}
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
//	Lấy doanh thu theo tháng và năm
	public List<ThongKeDoanhThu> getDTBoth(int month ,int year, int ca){
		List<ThongKeDoanhThu> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="SELECT s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, SUM(ct.SoLuongSP) as TongSoLuong, s.GiaBan\r\n"
					+ "					FROM SanPham s\r\n"
					+ "					JOIN CT_HoaDon ct ON s.maSp = ct.MaSanPham\r\n"
					+ "					JOIN HoaDon h ON h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "					JOIN NhanVien n ON n.MaNhanvien = h.NhanVien\r\n"
					+ "					where Year(NgayLapHoaDon) = " + year +" and MONTH(NgayLapHoaDon) = " + month +" AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ? \r\n"
					+ "					GROUP BY s.maSp, s.Tensp, s.MauSac, s.Size, s.KhuyenMai, s.Gianhap, s.Soluong, s.GiaBan" ;
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			if (ca == 0)
			{
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(1, 8);
				preparedStatement.setInt(2, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(1, 15);
				preparedStatement.setInt(2, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String mau = rs.getString(3);
				String size = rs.getString(4);
				String  khuyenMai = rs.getString(5);
				Double giaNhap = rs.getDouble(6);
				int soLuong = rs.getInt(7);
				int soLuongBan = rs.getInt(8);
				Double tongTien = rs.getDouble(9);
				int km = khuyenMai(khuyenMai);
				ThongKeDoanhThu tk = new ThongKeDoanhThu(ma, ten, mau, size, km, giaNhap, soLuong, soLuongBan, tongTien);
				list.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public double tongDoanhThuBoth(int month, int year, int  ca)
	{
		double s = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="SELECT sum (h.TongTien)\r\n"
						+ "from HoaDon h\r\n"
						+ "join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
						+ "					where Year(NgayLapHoaDon) = " + year +" and MONTH(NgayLapHoaDon) = " + month +"  AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				if (ca == 0)
				{
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, 23);
				}
				else if (ca == 1)
				{
					preparedStatement.setInt(1, 8);
					preparedStatement.setInt(2, 15);
				}
				else if (ca == 2)
				{
					preparedStatement.setInt(1, 15);
					preparedStatement.setInt(2, 22);
				}
				ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				s = rs.getDouble(1);
			}
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public double tongDoanhThuCuaKhachHang(int top)
	{
		double sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select top "+ top +" k.Sdt, sum (tongTien) as tienMua from HoaDon h \r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "	join KhachHang k on k.MaKH = h.KhachHang\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by k.Sdt\r\n"
					+ "	order by tienMua DESC";
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
	public String khachHangTop(int top)
	{
		String name = "";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select top "+ top +" k.Sdt, sum (tongTien) as tienMua from HoaDon h \r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon = ct.MaHoaDon\r\n"
					+ "	join KhachHang k on k.MaKH = h.KhachHang\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by k.Sdt\r\n"
					+ "	order by tienMua DESC";
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
