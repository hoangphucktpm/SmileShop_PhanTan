package DAOTest;

import Entity.ThongKeDoanhThu;

import java.util.List;

public interface ThongKeDoanhThuDao {
    public List<ThongKeDoanhThu> getDTQuy(int quy, int ca);
    public int getKhuyemMai (String maKM);
    public double tongDoanhThuQuy(int quy, int ca);
}
