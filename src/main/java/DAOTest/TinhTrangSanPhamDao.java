package DAOTest;

import Entities.ThongKeSanPham;

import java.util.Date;
import java.util.List;

public interface TinhTrangSanPhamDao {
    public List<ThongKeSanPham> getSPOUT();
    public List<ThongKeSanPham> getSPRE();
    public List<ThongKeSanPham> getALMOUT();
    public List<ThongKeSanPham> getNewAdd(Date date);
    public List<ThongKeSanPham> getOld();
    public List<ThongKeSanPham> getSold(int day, int month, int year);
    public int soLuongBan(String ma, int day, int month, int year);
    public Double tongTienBan(int top);
    public String sanPhamTop(int top);
}
