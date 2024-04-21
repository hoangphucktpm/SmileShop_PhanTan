package DAOTest;

import Entities.NhanVien;
import Entities.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThongTinCaNhanDao extends Remote {
    public List<NhanVien> loadNhanVien(String ma) throws RemoteException;

    ;

    public String tenNV(String manv) throws RemoteException;

    ;

    public boolean sua(String tenNV, String sdt, String Email, String MaNhanVien, String hinhAnh) throws RemoteException;

    ;

    public boolean suaMK(String matkhau, String MaNhanVien) throws RemoteException;

    ;

    public String mailNhanVien(String manv) throws RemoteException;

    ;

    public TaiKhoan loadTaiKhoan(String ma) throws RemoteException;

    ;

}
