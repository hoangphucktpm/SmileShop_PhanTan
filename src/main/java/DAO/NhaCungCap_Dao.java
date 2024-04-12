package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectDatabase;
import Entity.NhaCungCap;

public class NhaCungCap_Dao {
	public NhaCungCap_Dao() {
		// TODO Auto-generated constructor stub
		 ConnectDatabase.getInstance().connect();
	}
	/**
	 * Lấy dữ liệu nhà cung cấp từ cơ sỡ dữ liệu
	 * @return danh sách nhà cung cấp
	 */
	public List<NhaCungCap> getNhaCungCaps(){
		List<NhaCungCap> list= new ArrayList<>();
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select * from [dbo].[NhaCungCap]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNCC= rs.getString(1);
				String ten=rs.getString(2);
				String soDienThoai= rs.getString(3);
				String gmail= rs.getString(4);
				String dc= rs.getString(5);
				int tinhTrang = rs.getInt(6);
				NhaCungCap ncc= new NhaCungCap(maNCC, ten, soDienThoai, gmail, dc, tinhTrang);
				list.add(ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Thêm nhà cung cấp vào cơ sở dữ liệu
	 * @param ten
	 * @param sdt
	 * @param email
	 * @param maDC
	 * @return true nếu thành công
	 */
	public boolean them(String ten,String sdt,String email,String diaChi, int tinhTrang) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert [dbo].[NhaCungCap] ([TenNhaCungCap], [Sdt], [Email], [DiaChi], [TinhTrang]) values \r\n" + 
					"(?,?,?,?,?)");
			stmt.setString(1, ten);
			stmt.setString(2, sdt);
			stmt.setString(3, email);
			stmt.setString(4, diaChi);
			stmt.setInt(5, tinhTrang);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public boolean sua(String ten,String sdt,String email,String diaChi, int tinhTrang, String maNCC)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		String sql = "update [dbo].[NhaCungCap] set TenNhaCungCap = ?, Sdt = ?, Email =?, DiaChi = ?, TinhTrang = ? where MaNhaCungCap = ?";
		PreparedStatement stmt = null;
		
		int n =0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			stmt.setString(2, sdt);
			stmt.setString(3, email);
			stmt.setString(4, diaChi);
			stmt.setInt(5, tinhTrang);
			stmt.setString(6, maNCC);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
//	Lấy  ra danh sách nhà cung cấp theo tên
	public List<NhaCungCap> getTen (String tenNCC)
	{
		List<NhaCungCap> list= new ArrayList<>();
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select * from [dbo].[NhaCungCap] where TenNhaCungCap like N'%"+ tenNCC +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma =rs.getString(1);
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
//Lấy ra nhà cung cấp theo mã
	public List<NhaCungCap> getMa (String MaNCC)
	{
		List<NhaCungCap> list= new ArrayList<>();
		
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select * from [dbo].[NhaCungCap] where MaNhaCungCap like N'%"+ MaNCC +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma =rs.getString(1);
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

//Lấy ra nhà cung cấp theo số điện thoại
	public List<NhaCungCap> getSDT (String SDT)
	{
		List<NhaCungCap> list= new ArrayList<>();
			
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select * from [dbo].[NhaCungCap] where Sdt like N'%"+ SDT +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma =rs.getString(1);
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
	//Lấy ra nhà cung cấp theo số Email
		public List<NhaCungCap>  getEmail (String em)
		{
			List<NhaCungCap> list= new ArrayList<>();
				
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="select * from [dbo].[NhaCungCap] where Email like N'%"+ em +"%'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String ma =rs.getString(1);
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
//		lấy số lượng nhà cung cấp
		public int soLuongNCC(){
			int s = 0;
			try {
				Connection con = ConnectDatabase.getInstance().getConnection();
				String sql="select count(*) from [dbo].[NhaCungCap]";
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
}
