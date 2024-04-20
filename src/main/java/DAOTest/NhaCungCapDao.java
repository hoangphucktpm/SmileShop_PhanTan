package DAOTest;

import Entities.NhaCungCap;

import java.util.List;

public interface NhaCungCapDao {
    public List<NhaCungCap> getNhaCungCaps();
    public boolean them(NhaCungCap nhaCungCap);
    public boolean sua(NhaCungCap nhaCungCap);
    public List<NhaCungCap> getTen (String tenNCC);
    public List<NhaCungCap> getMa (String MaNCC);
    public List<NhaCungCap> getSDT (String SDT);
    public List<NhaCungCap> getEmail(String email);
    public int soLuongNCC();
}
