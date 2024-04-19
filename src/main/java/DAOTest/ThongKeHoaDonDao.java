package DAOTest;

import Entity.ThongKeHoaDon;

import java.util.List;

public interface ThongKeHoaDonDao {
    public List<ThongKeHoaDon> getHoaDonTheoNgayVaCa(int day, int month, int year, int ca);
    public List<ThongKeHoaDon> getHoaDonTheoNV(int day, int month, int year, String maNhanVien, int ca);

}
