package DAOTest;

import Entities.KhachHang;
import Entities.LoaiKhachHang;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public interface KhachHangDao {
    public List<KhachHang> getAllKH();
    public boolean addKhachHang(KhachHang khachHang);
    public KhachHang getKhachHang(String maKH);
    public Boolean updateKhachHang(KhachHang khachHang);
    public List<LoaiKhachHang> getAllLoaiKH();
    public List<KhachHang> getKH5KPoint();
    public String getLoaiKH(String maKH);
    public KhachHang getKHByName(String tenKH);
    public List<KhachHang> getByGender(int gioiTinh);
    public List<KhachHang> getKHByLoaiKH(String tenLoaiKH);


}
