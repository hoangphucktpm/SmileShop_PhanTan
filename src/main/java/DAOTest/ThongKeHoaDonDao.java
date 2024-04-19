package DAOTest;

import Entities.ThongKeHoaDon;

import java.util.List;

public interface ThongKeHoaDonDao {
    public List<ThongKeHoaDon> getHoaDonTheoNgayVaCa(int day, int month, int year, int ca);
    public List<ThongKeHoaDon> getHoaDonTheoNV(int day, int month, int year, String maNhanVien, int ca);
    public int tongHoaDon (int top);
    public String nhanVienTop(int top);


}
