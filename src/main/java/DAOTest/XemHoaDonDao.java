package DAOTest;

import Entities.CtHoadon;
import Entities.HoaDon;

import java.util.List;

public interface XemHoaDonDao {
    public List<HoaDon> getAllHoaDon();

    public List<CtHoadon> getCT_HoaDon(String mahd);

    public String getTenNV(String manv);

    public String getTenKH(String makh);

    public HoaDon getHDTHeoMa(String MaHD);

    public List<HoaDon> getHDTheoTenNV(String tennv);

    public List<HoaDon> getHDTheoTenKH(String tenkh);

    public List<HoaDon> getHDTheoNgayLap(int ngay, int thang, int nam);

}
