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
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.taiKhoan;

public class ThongTinCaNhan_Dao {
	private int sta;

	public ThongTinCaNhan_Dao () {
		ConnectDatabase.getInstance().connect();
	}
	
	/**
	 * get all NhanVien
	 * 
	 * @return List<NhanVien>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	 public List<NhanVien> loadNhanVien(String ma) {
        List<NhanVien> list = new ArrayList<>();

        try {
            Connection con = ConnectDatabase.getInstance().getConnection();
            String sql = "select * from [dbo].[NhanVien] where MaNhanvien like" + ma;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Date birthDate = rs.getDate(3);
                String CCCD = rs.getString(4);
                String Sdt = rs.getString(5);
                int gend = rs.getInt(6);
                int sta = rs.getInt(7);
                boolean stat = true;
                if (sta == 1)
                {
                	stat = true;
                }else
                	stat = false;
                int ca = rs.getInt(8);
                int chuc = rs.getInt(9);
                String hinh = rs.getString(10);
                String email = rs.getString(11);
                String diaChi = rs.getString(12);
                NhanVien nv = new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh);
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
	
	public taiKhoan loadTaiKhoan(String ma) {
       taiKhoan tk = new taiKhoan();

        try {
            Connection con = ConnectDatabase.getInstance().getConnection();
            String sql = "select * from TaiKhoan where TenTaiKhoan like '%" + ma + "%'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String tenTK = rs.getString(1);
                String matKhau = rs.getString(2);
                
               tk = new taiKhoan(tenTK, matKhau);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tk;
    }
	
	public String tenNV(String manv) {
		 String tenNV ="";
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        PreparedStatement stmt = null;
	        String sql = "SELECT MaNhanVien FROM NhanVien WHERE MaNhanVien = '"+ manv +"'";
	        stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	       
	        while (rs.next()) {
	           
	            tenNV = rs.getString(1);

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tenNV;
	}
	public boolean sua( String tenNV,String sdt,  String Email, String MaNhanVien, String hinhAnh)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update NhanVien set TenNhanVien = ?,Sdt = ?,Email = ?, HinhAnh = ? where MaNhanVien= ?";
		
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenNV);
			stmt.setString(2, sdt);
			stmt.setString(3, Email);
			stmt.setString(5, MaNhanVien);
			stmt.setString(4, hinhAnh);
			stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return true;
	}
	
	public void suaMK(String matkhau, String MaNhanVien) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update TaiKhoan set MatKhau = ? where TenTaiKhoan= ?";
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, matkhau);
			stmt.setString(2, MaNhanVien);
			stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
	}
	public String mailNhanVien(String manv) {
		 String mail ="";
	    try {
	        Connection con = ConnectDatabase.getInstance().getConnection();
	        PreparedStatement stmt = null;
	        String sql = "select Email from NhanVien where MaNhanvien  = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, manv);
	        ResultSet rs = stmt.executeQuery();
	       
	        while (rs.next()) {
	           
	        	mail = rs.getString(1);

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return mail;
	}
}