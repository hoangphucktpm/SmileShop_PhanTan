package DAOTest;

import Entities.NhanVien;
import Entities.TaiKhoan;

import java.util.List;

public interface ThongTinCaNhanDao {
    public List<NhanVien> loadNhanVien(String ma);
    public String tenNV(String manv);
    public boolean sua( String tenNV,String sdt,  String Email, String MaNhanVien, String hinhAnh);
    public boolean suaMK(String matkhau, String MaNhanVien) ;
    public String mailNhanVien(String manv);
    public TaiKhoan loadTaiKhoan(String ma);

    }
