package DAOTest;

import Entities.HoaDon;
import Entities.KhachHang;
import Entities.SanPham;

import java.util.List;

public interface LapHoaDonDao {

    public List<HoaDon> getAllLapHoaDon();

    public String maHoaDon(String maNhanVien);

    public List<KhachHang> timKhachHangBySDT(String Sdt);

    public float getDiem(String Sdt);

    public int soLuongHD();

    public int soLuongSPDaBan(String masp);
    public int soLuongNhap(String masp);
    public boolean addCT_HoaDon(String maHoaDon,String maSP,int soLuong);
    public String getTenNV(String ma) ;
    public SanPham laySP(String ten, String mau, String kt);
    public boolean upDateHoaDon(String mahd, double tienkd, float diemtichduoc,  String nhanvien, String khachhang, double tongtien );
    public boolean updateDiem(double diem, String sdt);
    public int getKMTheoPhanTram(String ma);
}


