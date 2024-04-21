package DAOTest;

import Entities.CtHoadon;
import Entities.HoaDon;
import Entities.KhachHang;
import Entities.SanPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LapHoaDonDao extends Remote {

    public List<HoaDon> getAllLapHoaDon() throws RemoteException;

    public String maHoaDon(String maNhanVien) throws RemoteException;

    public List<KhachHang> timKhachHangBySDT(String Sdt) throws RemoteException;

    public float getDiem(String Sdt) throws RemoteException;

    public int soLuongHD() throws RemoteException;

    public int soLuongSPDaBan(String masp) throws RemoteException;

    public int soLuongNhap(String masp) throws RemoteException;

    public boolean addCT_HoaDon(String mahd, String masp, int soluong) throws RemoteException;

    public String getTenNV(String ma) throws RemoteException;

    public SanPham laySP(String ten, String mau, String kt) throws RemoteException;

    public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc, String nhanvien, String khachhang, double tongtien) throws RemoteException;

    public boolean updateDiem(double diem, String sdt) throws RemoteException;

    public int getKMTheoPhanTram(String ma) throws RemoteException;

    public String getKMTheoTen(String TenKM) throws RemoteException;

    public HoaDon getHoaDon(String mahd) throws RemoteException;

    public SanPham getSanPham(String masp) throws RemoteException;

    ;
}


