package DAOTest;

import Entities.*;

import java.sql.Date;
import java.util.List;

public interface SanPhamDao {
    public List<SanPham> getAllSP() ;
    public List<SanPham> timTheoGia(double gia1, double gia2);
    public boolean them(String maSP, String tenSP, String nhaCungCap, String km, double giaBan, int soluong, Date ngayNhap,
                        String color, String size, String img, String chatLieu, int tinhTrang, String dvt, String loaiSP, int VAT,
                        double giaBanRa);
    public boolean sua(SanPham sanPham);
    public List<Entities.LoaiSanPham> getLoaiSP();
    public List<Entities.ChatLieu> getChatLieu();
    public ChatLieu getChatLieuOne(String ma );
    public LoaiSanPham getLoaiSPOne(String ma );
    public List<Entities.NhaCungCap> getTenNCC();
    public List<Entities.KhuyenMai> getKMTheoTen();
    public KhuyenMai getKMTheoTenOne(String ma);
    public ChatLieu getCLTheoTenOne(String ma);
    public LoaiSanPham getLSPTheoTenOne(String ma);

    public int getKMTheoPhanTram(String ma);
    public int soLuong();
    public int vat(String ma);
    public boolean themLoaiSP(Entities.LoaiSanPham loaiSanPham);
    public int soLuongLSP();
    public boolean themChatLieu(Entities.ChatLieu chatLieu);
    public int soLuongChatLieu();
    public String getTenLoaiSP(String maSP);
    public String getTenNhaCC(String maSP);
    public String getTenCL(String maSP);
    public SanPham getMa(String Ma);
    public SanPham getTenSP(String Ten);
    public List<SanPham> getlistTenLoaiSP(String lsp);
    public List<SanPham> getlistTenNCC(String nccap);
    public List<SanPham> getlistTenCL(String ChatLieu);
    public List<SanPham> getlistTenMauSac(String MS);
    public List<SanPham> getlistSize(String kthuoc);
    public String getMoTaChatLieu (String ma);
    public String getTenChatLieu (String ma);
    public String getMaChatLieu (String ten, String moTa);
    public NhaCungCap getMaOne(String MaNCC);



}
