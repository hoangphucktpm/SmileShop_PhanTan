package DAOTest;

import Entities.CtHoadon;
import Entities.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface XemHoaDonDao extends Remote {
    public List<HoaDon> getAllHoaDon() throws RemoteException;

    ;

    public List<CtHoadon> getCT_HoaDon(String mahd) throws RemoteException;

    ;

    public String getTenNV(String manv) throws RemoteException;

    ;

    public String getTenKH(String makh) throws RemoteException;

    ;

    public HoaDon getHDTHeoMa(String MaHD) throws RemoteException;

    ;

    public List<HoaDon> getHDTheoTenNV(String tennv) throws RemoteException;

    ;

    public List<HoaDon> getHDTheoTenKH(String tenkh) throws RemoteException;

    ;

    public List<HoaDon> getHDTheoNgayLap(int ngay, int thang, int nam) throws RemoteException;

    ;

}
