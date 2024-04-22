package DAOTest;

import Entities.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public interface SanPhamDao extends Remote {
    public List<SanPham> getAllSP() throws RemoteException;

    ;

    public List<SanPham> timTheoGia(double gia1, double gia2) throws RemoteException;

    ;

    public boolean them(String maSP, String tenSP, String nhaCungCap, String km, double giaBan, int soluong, Date ngayNhap,
                        String color, String size, String img, String chatLieu, int tinhTrang, String dvt, String loaiSP, int VAT,
                        double giaBanRa) throws RemoteException;

    ;

    public boolean sua(SanPham sanPham) throws RemoteException;

    ;

    public List<Entities.LoaiSanPham> getLoaiSP() throws RemoteException;

    ;

    public List<Entities.ChatLieu> getChatLieu() throws RemoteException;

    ;

    public ChatLieu getChatLieuOne(String ma) throws RemoteException;

    ;

    public LoaiSanPham getLoaiSPOne(String ma) throws RemoteException;

    ;

    public List<Entities.NhaCungCap> getTenNCC() throws RemoteException;

    ;

    public List<Entities.KhuyenMai> getKMTheoTen() throws RemoteException;

    ;

    public KhuyenMai getKMTheoTenOne(String ma) throws RemoteException;

    ;

    public ChatLieu getCLTheoTenOne(String ma) throws RemoteException;

    ;

    public LoaiSanPham getLSPTheoTenOne(String ma) throws RemoteException;

    ;

    public int getKMTheoPhanTram(String ma) throws RemoteException;

    ;

    public int soLuong() throws RemoteException;

    ;;

    public boolean themLoaiSP(Entities.LoaiSanPham loaiSanPham) throws RemoteException;

    ;

    public int soLuongLSP() throws RemoteException;

    ;

    public boolean themChatLieu(ChatLieu chatLieu) throws RemoteException;

    ;

    public int soLuongChatLieu() throws RemoteException;

    ;

//    public String getTenLoaiSP(String maSP) throws RemoteException;
//
//    ;

    public String getTenNhaCC(String maSP) throws RemoteException;

    ;

    public String getTenCL(String maSP) throws RemoteException;

    ;

    public SanPham getMa(String Ma) throws RemoteException;

    ;

    public List<SanPham> getListTenSP(String Ten) throws RemoteException;

    ;

    public List<SanPham> getlistTenLoaiSP(String lsp) throws RemoteException;

    ;

    public List<SanPham> getlistTenNCC(String nccap) throws RemoteException;

    ;

    public List<SanPham> getlistTenCL(String ChatLieu) throws RemoteException;

    ;

    public List<SanPham> getlistTenMauSac(MauSac MS) throws RemoteException;

    ;

    public List<SanPham> getlistSize(Size kthuoc) throws RemoteException;

    ;

    public String getMoTaChatLieu(String ma) throws RemoteException;

    ;

    public String getTenChatLieu(String ma) throws RemoteException;

    ;

    public String getMaChatLieu(String ten, String moTa) throws RemoteException;

    ;

    public NhaCungCap getMaOne(String MaNCC) throws RemoteException;

    ;


}
