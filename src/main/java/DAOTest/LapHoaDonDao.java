package DAOTest;

import Entities.HoaDon;
import Entities.KhachHang;

import java.util.List;

public interface LapHoaDonDao {

    public List<HoaDon> getAllLapHoaDon();
    public String maHoaDon(String maNhanVien);
    public List<KhachHang> timKhachHangBySDT(String Sdt);
    public float getDiem(String Sdt);
    public int soLuongHD() ;
}


