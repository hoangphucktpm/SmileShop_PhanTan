package DAOTest;

import Entities.NhanVien;
import Entities.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface NhanVienDao extends Remote {
    List<NhanVien> getAllNV() throws RemoteException;

    public boolean them(String maNV, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi) throws RemoteException;

    public boolean sua(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String Hinh, String maNV) throws RemoteException;

    public boolean suakhonganh(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String maNV) throws RemoteException;

    public long soLuongNV() throws RemoteException;

    public long soLuongQL() throws RemoteException;

    public NhanVien getNVTHeoMa(String maNV) throws RemoteException;

    public NhanVien getNVTHeoTen(String tenNV) throws RemoteException;

    public NhanVien getNVTHeoSdt(String sdt) throws RemoteException;

    public List<NhanVien> getNVTHeoCa(int ca) throws RemoteException;

    public List<NhanVien> getNVTHeoChuc(int chuc) throws RemoteException;

    ;

    public TaiKhoan getTK(String nv) throws RemoteException;

    ;

    public boolean addTaiKhoan(String tk) throws RemoteException;

    ;


}
