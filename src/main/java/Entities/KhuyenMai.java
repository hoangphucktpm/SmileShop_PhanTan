package Entities;

import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "KhuyenMai.findAll", query = "SELECT km FROM KhuyenMai km"),
        @NamedQuery(name = "KhuyenMai.findByMaKhuyenMai", query = "SELECT km FROM KhuyenMai km WHERE km.maKhuyenMai = :maKhuyenMai"),
        @NamedQuery(name = "KhuyenMai.getPercent", query = "SELECT km.phanTramKhuyenMai FROM KhuyenMai km WHERE km.maKhuyenMai = :maKhuyenMai")
})
public class KhuyenMai {
    @Id
    @Column(name = "MaKhuyenMai", nullable = false, length = 50)
    private String maKhuyenMai;

    @Column(name = "TenKhuyenMai", nullable = false, length = 50)
    private String tenKhuyenMai;

    @Column(name = "PhanTramKhuyenMai")
    private Integer phanTramKhuyenMai;

    @Column(name = "NgayBatDau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "NgayKetThuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "TrangThai", columnDefinition = "tinyint not null")
    private Short trangThai;

    @Column(name = "SoLuongSanPhamKM", nullable = false)
    private Integer soLuongSanPhamKM;

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

    public Integer getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }

    public void setPhanTramKhuyenMai(Integer phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoLuongSanPhamKM() {
        return soLuongSanPhamKM;
    }

    public void setSoLuongSanPhamKM(Integer soLuongSanPhamKM) {
        this.soLuongSanPhamKM = soLuongSanPhamKM;
    }

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, Integer phanTramKhuyenMai, LocalDate ngayBatDau, LocalDate ngayKetThuc, Short trangThai, Integer soLuongSanPhamKM) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.soLuongSanPhamKM = soLuongSanPhamKM;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maKhuyenMai='" + maKhuyenMai + '\'' +
                ", tenKhuyenMai='" + tenKhuyenMai + '\'' +
                ", phanTramKhuyenMai=" + phanTramKhuyenMai +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", trangThai=" + trangThai +
                ", soLuongSanPhamKM=" + soLuongSanPhamKM +
                '}';
    }
}