package DAOTest;

import Entities.NhanVien;
import Entities.ThongKeDoanhThu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThongKeDoanhThuDao extends Remote {
    public List<ThongKeDoanhThu> getDTQuy(int quy, int ca) throws RemoteException;

    ;

    public int getKhuyemMai(String maKM) throws RemoteException;

    ;

    public double tongDoanhThuQuy(int quy, int ca) throws RemoteException;

    ;

    public List<ThongKeDoanhThu> getDTThang(int thang, int ca) throws RemoteException;

    ;

    public Double tongDoanhThuThang(int thang, int ca) throws RemoteException;

    ;

    public List<ThongKeDoanhThu> getDTNam(int nam, int ca) throws RemoteException;

    ;

    public Double tongDoanhThuNam(int nam, int ca) throws RemoteException;

    ;

    public List<ThongKeDoanhThu> getDTThangNam(int thang, int nam, int ca) throws RemoteException;

    ;

    public Double tongDoanhThuThangNam(int thang, int nam, int ca) throws RemoteException;

    ;

    public Double tongDoanhThuCuaKhachHang(int top) throws RemoteException;

    ;

    public String topKhachHang(int top) throws RemoteException;

    ;
}
