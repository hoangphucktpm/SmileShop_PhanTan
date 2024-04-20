package DAOTest;

import Entities.KhuyenMai;

import java.time.LocalDate;
import java.util.List;

public interface KhuyenMaiDao {
    public List<KhuyenMai> getAllKhuyenMai();

    public boolean themKhuyenMai(KhuyenMai khuyenMai);

    public boolean updateKhuyenMai(KhuyenMai khuyenMai);

    public Long soLuongCTKM();

    public KhuyenMai getKMTHeoMa(String MaKM);

    public KhuyenMai getKMTheoTen(String TenKM);

    public List<KhuyenMai> getKMTheoPhanTram(int PhanTramKM);

    public List<KhuyenMai> getKMTheoTrangThai(String TrangThaiKM);

    public boolean hetHan();

    public List<KhuyenMai> getTheoThoiGian(LocalDate begin, LocalDate end);

    public boolean adDSPKM(String maSP, String maKM);

    public void capNhatNull(String maKM);

    public List<String> dsMaSPKM(String ma);

    public String layKhuyenMaiTuSanPham(String ma);

}
