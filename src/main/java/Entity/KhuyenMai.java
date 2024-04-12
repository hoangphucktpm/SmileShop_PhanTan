package Entity;

import java.util.Date;
import java.util.Objects;

public class KhuyenMai {
    public static final int TrangThai = 0;
	private String maKhuyenMai;
    private String tenKhuyenMai;
    private int phanTram;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean trangThai;
    private int soLuongSanPhamKM;
  

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public int getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuongSanPhamKM() {
        return soLuongSanPhamKM;
    }

    public void setSoLuongSanPhamKM(int soLuongSanPhamKM) {
        this.soLuongSanPhamKM = soLuongSanPhamKM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKhuyenMai);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KhuyenMai other = (KhuyenMai) obj;
        return Objects.equals(maKhuyenMai, other.maKhuyenMai);
    }

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, int phanTram, Date ngayBatDau, Date ngayKetThuc,
            boolean trangThai, int soLuongSanPhamKM) {
        super();
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.phanTram = phanTram;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.soLuongSanPhamKM = soLuongSanPhamKM;
    }
    public KhuyenMai(String maKhuyenMai) {
        super();
        this.maKhuyenMai = maKhuyenMai;
    }
    public KhuyenMai() {
        super();
    }

    @Override
    public String toString() {
        return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", phanTram=" + phanTram
                + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai=" + trangThai
                + ", soLuongSanPhamKM=" + soLuongSanPhamKM + "]";
    }
}
