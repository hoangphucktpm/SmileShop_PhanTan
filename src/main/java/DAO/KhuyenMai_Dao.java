package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import Database.ConnectDatabase;
import Entity.KhuyenMai;
import Entity.NhaCungCap;
import Entity.sanPham;

public class KhuyenMai_Dao{


	public KhuyenMai_Dao() {
        ConnectDatabase.getInstance().connect();
    }

    /**
     * Lấy danh sách khuyến mãi từ cơ sở dữ liệu
     * @return danh sách khuyến mãi
     */
    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> list = new ArrayList<>();

        try {
            Connection con = ConnectDatabase.getInstance().getConnection();
            String sql = "SELECT [MaKhuyenMai]\r\n"
            		+ "      ,[TenKhuyenMai]\r\n"
            		+ "      ,[PhanTramKhuyenMai]\r\n"
            		+ "      ,[NgayBatDau]\r\n"
            		+ "      ,[NgayKetThuc]\r\n"
            		+ "      ,[TrangThai]\r\n"
            		+ "	  , (select count(*) from SanPham where SanPham.KhuyenMai = MaKhuyenMai) as SoLuongSanPhamKM\r\n"
            		+ "  FROM [SmileShop].[dbo].[KhuyenMai] \r\n"
            		+ "  ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);


            while (rs.next()) {
                String maKhuyenMai = rs.getString(1);
                String tenKhuyenMai = rs.getString(2);
                int phanTram = rs.getInt(3);
                Date ngayBatDau = rs.getDate(4);
                Date ngayKetThuc = rs.getDate(5);
                int sta = rs.getInt(6);
               
                boolean trangThai = true;
                if (sta == 0)
                	trangThai = false;
                else if (sta == 1)
                	trangThai = true;
                int soLuongSanPhamKM = rs.getInt(7);

                // Kiểm tra và cập nhật trạng thái
//                if (ngayKetThuc != null) {
//                    java.util.Date ngayHienTai = new java.util.Date();
//                    if (ngayKetThuc.before(ngayHienTai)) {
//                        trangThai = false; // Đánh dấu là "Hết hạn"
//                    }
//                }

                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
                list.add(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * Thêm một khuyến mãi mới vào cơ sở dữ liệu
	 * @param tenKhuyenMai tên khuyến mãi
     * @return true nếu thêm thành công
     */
    public boolean themKhuyenMai(String tenKhuyenMai, int phanTram, Date ngaybd, Date ngaykt, int trangThai, int soLuong) {
        Connection con = ConnectDatabase.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "insert into [dbo].[KhuyenMai] (TenKhuyenMai, PhanTramKhuyenMai, NgayBatDau, NgayKetThuc, TrangThai, SoLuongSanPhamKM) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenKhuyenMai);
            stmt.setInt(2, phanTram);
            stmt.setDate(3, ngaybd);
            stmt.setDate(4, ngaykt);
            stmt.setInt(5, trangThai);
            stmt.setInt(6, soLuong);

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
    
    public boolean updateKhuyenMai(String tenKhuyenMai, int phanTram, Date ngaybd, Date ngaykt, int trangThai, int soLuong, String makm) throws ClassNotFoundException, SQLException{
        Connection con = ConnectDatabase.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "Update [dbo].[KhuyenMai] set TenKhuyenMai = ?, PhanTramKhuyenMai = ?, NgayBatDau =?, NgayKetThuc = ?, TrangThai = ?, SoLuongSanPhamKM = ? where MaKhuyenMai = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenKhuyenMai);
            stmt.setInt(2, phanTram);
            stmt.setDate(3, ngaybd);
            stmt.setDate(4, ngaykt);
            stmt.setInt(5, trangThai);
            stmt.setInt(6, soLuong);
            stmt.setString(7, makm);

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


    
    //lấy số lượng ctkm
	public int soLuongCTKM(){
		int sum = 0;
		try {
			Connection con = ConnectDatabase.getInstance().getConnection();
			String sql="select count(*) from [dbo].[KhuyenMai]";
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
//	Lấy  ra danh sách khuyến mãi theo mã

	 public KhuyenMai getKMTHeoMa(String MaKM) 
	 {
	        KhuyenMai list = new KhuyenMai();

	        try {
	            Connection con = ConnectDatabase.getInstance().getConnection();
	            String sql = "select * from [dbo].[KhuyenMai] where MaKhuyenMai like N'%"+ MaKM +"%'";
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

	                list = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
	        
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 
//		Lấy  ra danh sách khuyến mãi theo tên

		 public KhuyenMai getKMTheoTen(String TenKM) 
		 {
		        KhuyenMai list = new KhuyenMai();

		        try {
		            Connection con = ConnectDatabase.getInstance().getConnection();
		            String sql = "select * from [dbo].[KhuyenMai] where TenKhuyenMai like N'%"+ TenKM +"%'";
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

		                list = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
		        
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return list;
		    }
		 
		 
//			Lấy  ra danh sách khuyến mãi theo Phần trăm

		 public List<KhuyenMai> getKMTheoPhanTram(String PhanTramKM) 
		 {
			 	List<KhuyenMai> list= new ArrayList<>();

		        try {
		            Connection con = ConnectDatabase.getInstance().getConnection();
		            String sql = "select * from [dbo].[KhuyenMai] where PhanTramKhuyenMai like N'%"+ PhanTramKM +"%'";
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

		                KhuyenMai km = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
		                list.add(km);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return list;
		    }


			 
			 
//				Lấy  ra danh sách khuyến mãi theo trạng thái

			 public List<KhuyenMai> getKMTheoTrangThai(String TrangThaiKM) 
			 {
				 	List<KhuyenMai> list= new ArrayList<>();

			        try {
			            Connection con = ConnectDatabase.getInstance().getConnection();
			            String sql = "select * from [dbo].[KhuyenMai] where TrangThai like N'%"+ TrangThaiKM +"%'";
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

			                KhuyenMai km = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
			                list.add(km);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }

			        return list;
			    }

			 public boolean hetHan(){
			        Connection con = ConnectDatabase.getInstance().getConnection();
			        PreparedStatement stmt = null;

			        try {
			            String sql = "Update [dbo].[KhuyenMai] set TrangThai = 0 where NgayKetThuc < CAST(GETDATE() AS DATE)";
			            stmt = con.prepareStatement(sql);
			            stmt.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			       
			        }

			        return true;
			    }
			 
			 
			 public List<KhuyenMai> getTheoThoiGian(java.util.Date begin, java.util.Date end) {
				    List<KhuyenMai> list = new ArrayList<>();

				    try {
				        Connection con = ConnectDatabase.getInstance().getConnection();
				        String sql = "SELECT * FROM KhuyenMai WHERE NgayBatDau >= CONVERT(date, ?, 105) AND NgayKetThuc <= CONVERT(date, ?, 105)";
				        PreparedStatement preparedStatement = con.prepareStatement(sql);

				        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				        String ngaybdText = sdf.format(begin);
				        String ngayktText = sdf.format(end);

				        preparedStatement.setString(1, ngaybdText);
				        preparedStatement.setString(2, ngayktText);

				        ResultSet rs = preparedStatement.executeQuery();

				        while (rs.next()) {
				            String maKhuyenMai = rs.getString(1);
				            String tenKhuyenMai = rs.getString(2);
				            int phanTram = rs.getInt(3);
				            Date ngayBatDau = rs.getDate(4);
				            Date ngayKetThuc = rs.getDate(5);
				            boolean trangThai = rs.getBoolean(6);
				            int soLuongSanPhamKM = rs.getInt(7);

				            KhuyenMai km = new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc, trangThai, soLuongSanPhamKM);
				            list.add(km);
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }

				    return list;
				}
			 public boolean adDSPKM(String maSP, String maKM){
			        Connection con = ConnectDatabase.getInstance().getConnection();
			        PreparedStatement stmt = null;

			        try {
			            String sql = "Update [dbo].[SanPham] set [KhuyenMai] = ?  where maSp = ?";
			            stmt = con.prepareStatement(sql);
			            stmt.setString(2, maSP);
			            stmt.setString(1, maKM);
			            stmt.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			       
			        }

			        return true;
			    }
			 public void capNhatNull(String maKM)
			 {
				 Connection con = ConnectDatabase.getInstance().getConnection();
			        PreparedStatement stmt = null;

			        try {
			            String sql = "update SanPham set KhuyenMai = null where KhuyenMai = '" + maKM +"'";
			            stmt = con.prepareStatement(sql);
			            stmt.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			       
			        }

			 }
			 public List<String> dsMaSPKM (String ma)
			 {
				 List<String> list = new ArrayList<>();
				  try {
				        Connection con = ConnectDatabase.getInstance().getConnection();
				        String sql = "select maSp from SanPham where KhuyenMai = '" + ma +"'";
				        PreparedStatement preparedStatement = con.prepareStatement(sql);

				        ResultSet rs = preparedStatement.executeQuery();

				        while (rs.next()) {
				          String maSP = rs.getString(1);
				            list.add(maSP);
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }

				    return list;
			 }
			 public String layKhuyenMaiTuSanPham (String ma)
			 {
				 String km = "";
				  try {
				        Connection con = ConnectDatabase.getInstance().getConnection();
				        String sql = "select KhuyenMai from SanPham where maSp = '" + ma +"'";
				        PreparedStatement preparedStatement = con.prepareStatement(sql);

				        ResultSet rs = preparedStatement.executeQuery();

				        while (rs.next()) {
				          km = rs.getString(1);
				            
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }

				    return km;
			 }
}


