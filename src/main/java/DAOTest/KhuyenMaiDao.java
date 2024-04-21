package DAOTest;

import Entities.KhuyenMai;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface KhuyenMaiDao extends Remote {
    public List<KhuyenMai> getAllKhuyenMai() throws RemoteException;

    public boolean themKhuyenMai(KhuyenMai khuyenMai) throws RemoteException;

    public boolean updateKhuyenMai(KhuyenMai khuyenMai) throws RemoteException;

    public Long soLuongCTKM() throws RemoteException;

    public KhuyenMai getKMTHeoMa(String MaKM) throws RemoteException;

    public KhuyenMai getKMTheoTen(String TenKM) throws RemoteException;

    public List<KhuyenMai> getKMTheoPhanTram(int PhanTramKM) throws RemoteException;

    public List<KhuyenMai> getKMTheoTrangThai(String TrangThaiKM) throws RemoteException;

    public boolean hetHan() throws RemoteException;

    public List<KhuyenMai> getTheoThoiGian(LocalDate begin, LocalDate end) throws RemoteException;

    public boolean adDSPKM(String maSP, String maKM) throws RemoteException;

    public void capNhatNull(String maKM) throws RemoteException;

    public List<String> dsMaSPKM(String ma) throws RemoteException;

    public String layKhuyenMaiTuSanPham(String ma) throws RemoteException;

    ;
}
