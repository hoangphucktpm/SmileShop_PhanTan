package DAOTest;

import Entities.KhachHang;
import Entities.LoaiKhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public interface KhachHangDao extends Remote {
    public List<KhachHang> getAllKH() throws RemoteException;

    public boolean addKhachHang(KhachHang khachHang) throws RemoteException;

    public KhachHang getKhachHang(String maKH) throws RemoteException;

    public Boolean updateKhachHang(KhachHang khachHang) throws RemoteException;

    public List<LoaiKhachHang> getAllLoaiKH() throws RemoteException;

    public List<KhachHang> getKH5KPoint() throws RemoteException;

    public String getLoaiKH(String maKH) throws RemoteException;

    public KhachHang getKHByName(String tenKH) throws RemoteException;

    public List<KhachHang> getByGender(int gioiTinh) throws RemoteException;

    public List<KhachHang> getKHByLoaiKH(String tenLoaiKH) throws RemoteException;

    ;


}
