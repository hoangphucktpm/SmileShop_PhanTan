package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.ConnectDatabase;
import Entity.NhanVien;
import Entity.taiKhoan;
import GUI.FrmDangNhap;


public class NhanVien_Dao {
	private static final ConnectDatabase ConectDatabase = null;
	Connection con;
	PreparedStatement preStm;
	ResultSet rs;
	private Statement statement;

	public NhanVien_Dao() {
		ConnectDatabase.getInstance().connect();
	}

	/**
	 * add Khachhang
	 * 
	 * @param kh
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	public boolean themNhanVien(NhanVien nv) {
		int n = 0;
		try {
			con = ConnectDatabase.getInstance().getConnection();
			String sql = "insert into NhanVien ([TenNhanVien], NgaySinh, CCCD, Sdt, GioiTinh, TrangThai, CaLamViec, ChucVu, HinhAnh,Email, DiaChi) values(?,?,?,?,?,?,?,?,?,?,?)";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, nv.getTenNV());
			preStm.setDate(2, (java.sql.Date) nv.getNgaySinh());
			preStm.setString(3, nv.getCmnd());
			preStm.setString(4, nv.getSoDienThoai());
			preStm.setInt(5, nv.getGioiTinh());
			preStm.setBoolean(6, nv.isTrangThai());
			preStm.setInt(7, nv.getCaLamViec());
			preStm.setInt(8, nv.getChucVu());
			preStm.setString(9, nv.getHinhAnh());
			preStm.setString(10, nv.getEmail());
			preStm.setString(11, nv.getDiaChi());
			
			
			n = preStm.executeUpdate();
		} catch (Exception e) {
		}
		return n > 0;
	}

	

	/**
	 * get all NhanVien
	 * 
	 * @return List<NhanVien>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	 public List<NhanVien> getAllNV() {
        List<NhanVien> list = new ArrayList<>();

        try {
            Connection con = ConnectDatabase.getInstance().getConnection();
            String sql = "select * from [dbo].[NhanVien] where MaNhanvien <> 'admin'";
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
	public boolean them(String maNV, String tenNV,Date ngaySinh,String CCCD,String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi) {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into [dbo].[NhanVien] (MaNhanVien, [TenNhanVien], NgaySinh, CCCD, Sdt, GioiTinh, TrangThai, CaLamViec, ChucVu, HinhAnh,Email, DiaChi) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, maNV);
			stmt.setString(2, tenNV);
			stmt.setDate(3, ngaySinh);
			stmt.setString(4, CCCD);
			stmt.setString(5, sdt);
			stmt.setInt(6, gend);
			stmt.setInt(7, sta);
			stmt.setInt(8, ca);
			stmt.setInt(9, chuc);
			stmt.setString(10, hinh);
			stmt.setString(11, Email);
			stmt.setString(12, DiaChi);
			
			
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
//	public boolean sua(String maNV, String tenNV,Date ngaySinh,String CCCD,String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi) {
//		Connection con = ConnectDatabase.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n =0;
//		try {
//			stmt = con.prepareStatement("update [dbo].[NhanVien]\r\n"
//					+ "set [TenNhanVien] = ?,[NgaySinh] = ?,[CCCD]=?,[Sdt]=?,[GioiTinh]=?,[TrangThai]=?,[CaLamViec]=?,[ChucVu]=?,[HinhAnh]=?,[Email]=?,[DiaChi]=?\r\n"
//					+ "where MaNhanvien like ?");
//			stmt.setString(1, tenNV);
//			stmt.setString(2, tenNV);
//			stmt.setDate(3, ngaySinh);
//			stmt.setString(4, CCCD);
//			stmt.setString(5, sdt);
//			stmt.setInt(6, gend);
//			stmt.setInt(7, sta);
//			stmt.setInt(8, ca);
//			stmt.setInt(9, chuc);
//			stmt.setString(10, hinh);
//			stmt.setString(11, Email);
//			stmt.setString(12, DiaChi);
//			
//			
//			n= stmt.executeUpdate();
//		} catch (Exception e3) {
//			// TODO: handle exception
//		}
//		return n>0;
//	}
	public boolean sua(String maBanDau, String tenNV,Date ngaySinh,String CCCD,String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi,String Hinh, String maNV)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		java.sql.Date birth = new java.sql.Date(ngaySinh.getTime());
		String sql = "update NhanVien set MaNhanVien= ?, TenNhanVien = ?,NgaySinh = ?,CCCD = ?,Sdt = ?,GioiTinh = ?,TrangThai = ?,CaLamViec = ?,ChucVu = ?,Email = ?,HinhAnh = ?,DiaChi = ? where MaNhanVien = ?";
		
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setString(13, maBanDau);
			stmt.setString(2, tenNV);
			stmt.setDate(3, ngaySinh);
			stmt.setString(4, CCCD);
			stmt.setString(5, sdt);
			stmt.setInt(6, gend);
			stmt.setInt(7, sta);
			stmt.setInt(8, ca);
			stmt.setInt(9, chuc);
			stmt.setString(10, Email);
			stmt.setString(12, DiaChi);
			stmt.setString(1, maNV);
			stmt.setString(11, Hinh);
			
			stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e3);
			return false;
		}
		return true;
	}
	
	
	public boolean suakhonganh(String maBanDau, String tenNV,Date ngaySinh,String CCCD,String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String maNV)
	{
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		java.sql.Date birth = new java.sql.Date(ngaySinh.getTime());
		String sql = "update NhanVien set MaNhanVien = ?, TenNhanVien = ?,NgaySinh = ?,CCCD = ?,Sdt = ?,GioiTinh = ?,TrangThai = ?,CaLamViec = ?,ChucVu = ?,Email = ?, DiaChi = ? where MaNhanVien = ?";
		
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setString(12, maBanDau);
			stmt.setString(2, tenNV);
			stmt.setDate(3, ngaySinh);
			stmt.setString(4, CCCD);
			stmt.setString(5, sdt);
			stmt.setInt(6, gend);
			stmt.setInt(7, sta);
			stmt.setInt(8, ca);
			stmt.setInt(9, chuc);
			stmt.setString(10, Email);
			stmt.setString(11, DiaChi);
			stmt.setString(1, maNV);
//			stmt.setString(12, Hinh);
			
			stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return true;
	}

	public int soLuongNV(){
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="  select count(*) from [dbo].[NhanVien] where ChucVu = 0 and MaNhanvien <> 'admin'";
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
	
	public int soLuongQL(){
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="  select count(*) from [dbo].[NhanVien] where ChucVu = 1 and MaNhanvien <> 'admin'";
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
	

	 public NhanVien getNVTHeoMa(String MaNV) 
	 {
		 	NhanVien list = new NhanVien();

	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[NhanVien] where MaNhanVien like N'%"+ MaNV +"%'";
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
	                list = new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 public NhanVien getNVTHeoTen(String TenNV) 
	 {
		 	NhanVien nv = new NhanVien();

	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[NhanVien] where TenNhanVien like N'"+ TenNV +"'";
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
	                nv = new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return nv;
	    }
	 public NhanVien getNVTHeoSdt(String Sdt) 
	 {
		 	NhanVien list = new NhanVien();

	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[NhanVien] where Sdt like N'%"+ Sdt +"%'";
	            Statement statement = con.createStatement();
	            ResultSet rs = statement.executeQuery(sql);

	            while (rs.next()) {
	                String maNV = rs.getString(1);
	                String tenNV = rs.getString(2);
	                Date birthDate = rs.getDate(3);
	                String CCCD = rs.getString(4);
	                String sdt = rs.getString(5);
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
	                list = new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 public ArrayList<NhanVien> getNVTHeoCa(int Ca) 
	 {
		 ArrayList<NhanVien> list = new ArrayList<NhanVien>();
	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[NhanVien] where [CaLamViec] = " + Ca + " and MaNhanVien <> 'admin'";
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
	                list.add(new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 public ArrayList<NhanVien> getNVTHeoChuc(int Chuc) 
	 {
		 ArrayList<NhanVien> list = new ArrayList<NhanVien>();
	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[NhanVien] where ChucVu = " + Chuc + " and MaNhanVien <> 'admin'";
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
	                list.add(new NhanVien(maNV, ca, tenNV, birthDate, gend, CCCD, email, Sdt, chuc, stat, diaChi, hinh));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	public String getChucVu() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addTaiKhoan(String tk) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into TaiKhoan values\r\n" + "(?,12345678)");
			stmt.setString(1, tk);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
		}
		return n > 0;
	}
	 public taiKhoan getTK(String nv) 
	 {
		 taiKhoan t = new taiKhoan();
	        try {
	        	Connection con = ConnectDatabase.getInstance().getConnection();
		        PreparedStatement stmt = null;
	            String sql = "select * from [dbo].[TaiKhoan] where TenTaiKhoan like ?" ;
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, nv); 
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                String maNV = rs.getString(1);
	                String mk = rs.getString(2);
	                taiKhoan tk = new taiKhoan(maNV, mk);
	                t = tk;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return t;
	    }

	
}
