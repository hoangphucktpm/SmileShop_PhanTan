package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;

import Entity.ThongKeHoaDon;

public class ThongKeHoaDon_Dao {
	
	public ThongKeHoaDon_Dao()
	{
		ConnectDatabase.getInstance().connect();
	}
//	Lấy danh sách hóa đơn theo ca và ngày
	public List<ThongKeHoaDon> getHoaDonTheoNgayVaCa(int day, int month, int year, int ca){
		List<ThongKeHoaDon> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="  select h.MaHoaDon, h.NhanVien, n.CaLamViec, sum(SoLuongSP) as soLuong, NgayLapHoaDon, TongTien, TenLoaiKH\r\n"
					+ "					from HoaDon h\r\n"
					+ "					join CT_HoaDon c on c.MaHoaDon = h.MaHoaDon\r\n"
					+ "					join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
					+ "					join KhachHang k on k.MaKH = h.KhachHang\r\n"
					+ "					join loaiKhachHang lkh on lkh.MaLoaiKH = k.LoaiKH\r\n"
					+ "					where day(NgayLapHoaDon) = ? and MONTH(NgayLapHoaDon) = ? and year(NgayLapHoaDon) = ?  AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ?\r\n"
					+ "					group by  h.MaHoaDon, h.NhanVien, n.CaLamViec, NgayLapHoaDon, TongTien, TenLoaiKH";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, day);
			preparedStatement.setInt(2, month);
			preparedStatement.setInt(3, year);
			if (ca == 0)
			{
				preparedStatement.setInt(4, 0);
				preparedStatement.setInt(5, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(4, 8);
				preparedStatement.setInt(5, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(4, 15);
				preparedStatement.setInt(5, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maHD= rs.getString(1);
				String maNV = rs.getString(2);
				int caLC = rs.getInt(3);
				int soLuongSP = rs.getInt(4);
				Date ngayLap = rs.getDate(5);
				String  loaiKH = rs.getString(7);
				Double tongTien = rs.getDouble(6);
				ThongKeHoaDon hd = new ThongKeHoaDon(maHD, maNV, caLC, soLuongSP, ngayLap, loaiKH, tongTien);
				
				list.add(hd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	Lấy danh sách hóa đơn theo ca, ngày và nhân viên lập
	public List<ThongKeHoaDon> getHoaDonTheoNV(int day, int month, int year, String ma,int ca){
		List<ThongKeHoaDon> list= new ArrayList<>();

		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql=  "select h.MaHoaDon, h.NhanVien, n.CaLamViec, sum(SoLuongSP) as soLuong, NgayLapHoaDon, TongTien, TenLoaiKH\r\n"
					+ "					from HoaDon h\r\n"
					+ "					join CT_HoaDon c on c.MaHoaDon = h.MaHoaDon\r\n"
					+ "					join NhanVien n on n.MaNhanvien=h.NhanVien\r\n"
					+ "					join KhachHang k on k.MaKH = h.KhachHang\r\n"
					+ "					join loaiKhachHang lkh on lkh.MaLoaiKH = k.LoaiKH\r\n"
					+ "					where day(NgayLapHoaDon) = ? and MONTH(NgayLapHoaDon) = ? and year(NgayLapHoaDon) = ? and MaNhanvien = ? AND DATEPART(HOUR, NgayLapHoaDon) >= ?  and DATEPART(HOUR, NgayLapHoaDon) < ?\r\n"
					+ "group by  h.MaHoaDon, h.NhanVien, n.CaLamViec, NgayLapHoaDon, TongTien, TenLoaiKH";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, day);
			preparedStatement.setInt(2, month);
			preparedStatement.setInt(3, year);
	        preparedStatement.setString(4, ma);
	        if (ca == 0)
			{
				preparedStatement.setInt(5, 0);
				preparedStatement.setInt(6, 23);
			}
			else if (ca == 1)
			{
				preparedStatement.setInt(5, 8);
				preparedStatement.setInt(6, 15);
			}
			else if (ca == 2)
			{
				preparedStatement.setInt(5, 15);
				preparedStatement.setInt(6, 22);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maHD= rs.getString(1);
				String maNV = rs.getString(2);
				int caLC = rs.getInt(3);
				int soLuongSP = rs.getInt(4);
				Date ngayLap = rs.getDate(5);
				String  loaiKH = rs.getString(7);
				Double tongTien = rs.getDouble(6);
				ThongKeHoaDon hd = new ThongKeHoaDon(maHD, maNV, caLC, soLuongSP, ngayLap, loaiKH, tongTien);
				
				list.add(hd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int tongHoaDon(int top)
	{
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select top " + top +"  n.MaNhanvien ,count(h.MaHoaDon) as HoaDon from HoaDon h\r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon=ct.MaHoaDon\r\n"
					+ "	join NhanVien n on n.MaNhanvien = h.NhanVien\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by n.MaNhanvien\r\n"
					+ "	order by HoaDon DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				sum = rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	public String nhanVienTop(int top)
	{
		String name = "";
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select top " + top +"  n.MaNhanvien ,count(h.MaHoaDon) as HoaDon from HoaDon h\r\n"
					+ "	join CT_HoaDon ct on h.MaHoaDon=ct.MaHoaDon\r\n"
					+ "	join NhanVien n on n.MaNhanvien = h.NhanVien\r\n"
					+ "	where MONTH(NgayLapHoaDon) = MONTH(GETDATE())\r\n"
					+ "	group by n.MaNhanvien\r\n"
					+ "	order by HoaDon DESC";
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
