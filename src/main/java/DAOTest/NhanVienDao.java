package DAOTest;

import Entities.NhanVien;
import Entities.TaiKhoan;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface NhanVienDao {
    List<NhanVien> getAllNV();

    public boolean them(String maNV, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String hinh, String Email, String DiaChi);
    public boolean sua(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String Hinh, String maNV);
    public boolean suakhonganh(String maBanDau, String tenNV, Date ngaySinh, String CCCD, String sdt, int gend, int sta, int ca, int chuc, String Email, String DiaChi, String maNV);
    public long soLuongNV();
    public long soLuongQL();
    public NhanVien getNVTHeoMa(String maNV);
    public NhanVien getNVTHeoTen(String tenNV);
    public NhanVien getNVTHeoSdt(String sdt);
    public List<NhanVien> getNVTHeoCa(int ca);
    public List<NhanVien> getNVTHeoChuc(int chuc);
    public TaiKhoan getTK(String nv);
    public boolean addTaiKhoan(String tk);


}
