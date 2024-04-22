package Entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "CT_HoaDon")
public class CtHoadon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private HoaDon maHoaDon;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaSanPham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "SoLuongSP", nullable = false)
    private Integer soLuongSP;


    public HoaDon getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(HoaDon maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public SanPham getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(SanPham maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(Integer soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public CtHoadon(HoaDon maHoaDon, SanPham maSanPham, Integer soLuongSP) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuongSP = soLuongSP;
    }

    public CtHoadon() {
    }

    @Override
    public String toString() {
        return "CtHoadon{" +
                ", maHoaDon=" + maHoaDon +
                ", maSanPham=" + maSanPham +
                ", soLuongSP=" + soLuongSP +
                '}';
    }
}