package DAOTest;

import Entities.ThongKeHoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThongKeHoaDonDao extends Remote {
    public List<ThongKeHoaDon> getHoaDonTheoNgayVaCa(int day, int month, int year, int ca) throws RemoteException;

    ;

    public List<ThongKeHoaDon> getHoaDonTheoNV(int day, int month, int year, String maNhanVien, int ca) throws RemoteException;

    ;

    public int tongHoaDon(int top) throws RemoteException;

    ;

    public String nhanVienTop(int top) throws RemoteException;

    ;


}
