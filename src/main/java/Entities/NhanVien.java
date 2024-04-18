package Entities;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class NhanVien {
    @Id
    @Column(name = "MaNhanvien", nullable = false, length = 50)
    private String maNhanvien;

    @Column(name = "TenNhanVien", nullable = false, length = 50)
    private String tenNhanVien;

    @Column(name = "NgaySinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "CCCD", nullable = false, length = 50)
    private String cccd;

    @Column(name = "Sdt", nullable = false, length = 50)
    private String sdt;

    @Column(name = "GioiTinh", columnDefinition = "tinyint not null")
    private Short gioiTinh;

    @Column(name = "TrangThai", columnDefinition = "tinyint not null")
    private Short trangThai;

    @Column(name = "CaLamViec", columnDefinition = "tinyint not null")
    private Short caLamViec;

    @Column(name = "ChucVu", columnDefinition = "tinyint not null")
    private Short chucVu;

    @Column(name = "HinhAnh", nullable = false, length = 50)
    private String hinhAnh;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = false, length = 50)
    private String diaChi;

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

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
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

    public Short getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Short gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    public Short getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(Short caLamViec) {
        this.caLamViec = caLamViec;
    }

    public Short getChucVu() {
        return chucVu;
    }

    public void setChucVu(Short chucVu) {
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

    public NhanVien(String maNhanvien, String tenNhanVien, LocalDate ngaySinh, String cccd, String sdt, Short gioiTinh, Short trangThai, Short caLamViec, Short chucVu, String hinhAnh, String email, String diaChi) {
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