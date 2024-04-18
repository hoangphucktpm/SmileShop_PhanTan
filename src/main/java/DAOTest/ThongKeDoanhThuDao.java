package DAOTest;

import Entity.ThongKeDoanhThu;

import java.util.List;

public interface ThongKeDoanhThuDao {
    public List<ThongKeDoanhThu> getDTQuy(int quy, int ca);
    public int getKhuyemMai (String maKM);
    public double tongDoanhThuQuy(int quy, int ca);
    public List<ThongKeDoanhThu> getDTThang(int thang, int ca);
    public Double tongDoanhThuThang(int thang, int ca);
    public List<ThongKeDoanhThu> getDTNam(int nam, int ca);
    public Double tongDoanhThuNam(int nam, int ca);
    public List<ThongKeDoanhThu> getDTThangNam(int thang, int nam, int ca);
    public Double tongDoanhThuThangNam(int thang, int nam, int ca);
    public Double tongDoanhThuCuaKhachHang(int top);
    public String topKhachHang(int top);
}
