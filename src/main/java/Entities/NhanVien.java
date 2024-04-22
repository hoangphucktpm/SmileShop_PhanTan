package Entities;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Entity
@NamedQuery(name = "NhanVien.findAll", query = "SELECT n FROM NhanVien n")
public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaNhanvien", nullable = false, length = 50)
    private String maNhanvien;

    @Column(name = "TenNhanVien", nullable = false, length = 50)
    private String tenNhanVien;

    @Column(name = "NgaySinh", nullable = false)
    private Date ngaySinh;

    @Column(name = "CCCD", nullable = false, length = 50)
    private String cccd;

    @Column(name = "Sdt", nullable = false, length = 50)
    private String sdt;

    @Column(name = "GioiTinh", columnDefinition = "tinyint not null")
    private int gioiTinh;

    @Column(name = "TrangThai", columnDefinition = "tinyint not null")
    private int trangThai;

    @Column(name = "CaLamViec", columnDefinition = "tinyint not null")
    private int caLamViec;

    @Column(name = "ChucVu", columnDefinition = "tinyint not null")
    private int chucVu;

    @Column(name = "HinhAnh", nullable = false, length = 50)
    private String hinhAnh;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = false, length = 50)
    private String diaChi;

    public NhanVien(String maNhanvien, String tenNhanVien, Date ngaySinh, String cccd, String sdt, int gioiTinh, int trangThai, int caLamViec, int chucVu, String hinhAnh, String email, String diaChi) {
        this.maNhanvien = maNhanvien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.caLamViec = caLamViec;
        this.chucVu = chucVu;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.diaChi = diaChi;
    }

    public String getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(String maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(int caLamViec) {
        this.caLamViec = caLamViec;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanvien='" + maNhanvien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", cccd='" + cccd + '\'' +
                ", sdt='" + sdt + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", trangThai=" + trangThai +
                ", caLamViec=" + caLamViec +
                ", chucVu=" + chucVu +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }


}