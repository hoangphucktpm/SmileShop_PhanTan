package DAOTest;

import Entities.NhaCungCap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NhaCungCapDao extends Remote {
    public List<NhaCungCap> getNhaCungCaps() throws RemoteException;

    public boolean them(NhaCungCap nhaCungCap) throws RemoteException;

    public boolean sua(NhaCungCap nhaCungCap) throws RemoteException;

    public List<NhaCungCap> getTen(String tenNCC) throws RemoteException;

    public List<NhaCungCap> getMa(String MaNCC) throws RemoteException;

    public List<NhaCungCap> getSDT(String SDT) throws RemoteException;

    public List<NhaCungCap> getEmail(String email) throws RemoteException;

    public int soLuongNCC() throws RemoteException;

    ;
}
