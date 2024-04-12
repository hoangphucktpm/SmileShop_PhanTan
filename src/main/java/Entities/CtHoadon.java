package Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CT_HoaDon")
public class CtHoadon {
    @EmbeddedId
    private CtHoadonId id;

    @MapsId("maHoaDon")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private HoaDon maHoaDon;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaSanPham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "SoLuongSP", nullable = false)
    private Integer soLuongSP;

    public CtHoadonId getId() {
        return id;
    }

    public void setId(CtHoadonId id) {
        this.id = id;
    }

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

    public CtHoadon(CtHoadonId id, HoaDon maHoaDon, SanPham maSanPham, Integer soLuongSP) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuongSP = soLuongSP;
    }

    public CtHoadon() {
    }

    @Override
    public String toString() {
        return "CtHoadon{" +
                "id=" + id +
                ", maHoaDon=" + maHoaDon +
                ", maSanPham=" + maSanPham +
                ", soLuongSP=" + soLuongSP +
                '}';
    }
}