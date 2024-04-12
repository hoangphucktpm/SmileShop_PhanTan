package Entities;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class NhaCungCap {
    @Id
    @Column(name = "MaNhaCungCap", nullable = false, length = 50)
    private String maNhaCungCap;

    @Column(name = "TenNhaCungCap", nullable = false, length = 50)
    private String tenNhaCungCap;

    @Column(name = "Sdt", nullable = false, length = 50)
    private String sdt;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = false, length = 50)
    private String diaChi;

    @Column(name = "TinhTrang", columnDefinition = "tinyint not null")
    private Short tinhTrang;

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public Short getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Short tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdt, String email, String diaChi, Short tinhTrang) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" +
                "maNhaCungCap='" + maNhaCungCap + '\'' +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", tinhTrang=" + tinhTrang +
                '}';
    }
}