package DAOTest;

import Entities.ThongKeSanPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface TinhTrangSanPhamDao extends Remote {
    public List<ThongKeSanPham> getSPOUT() throws RemoteException;

    ;

    public List<ThongKeSanPham> getSPRE() throws RemoteException;

    ;

    public List<ThongKeSanPham> getALMOUT() throws RemoteException;

    ;

    public List<ThongKeSanPham> getNewAdd(Date date) throws RemoteException;

    ;

    public List<ThongKeSanPham> getOld() throws RemoteException;

    ;

    public List<ThongKeSanPham> getSold(int day, int month, int year) throws RemoteException;

    ;

    public int soLuongBan(String ma, int day, int month, int year) throws RemoteException;

    ;

    public Double tongTienBan(int top) throws RemoteException;

    ;

    public String sanPhamTop(int top) throws RemoteException;

    ;
}
