package Entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CtHoadonId implements Serializable {
    private static final long serialVersionUID = -6237156482070753919L;
    @Column(name = "MaHoaDon", nullable = false, length = 50)
    private String maHoaDon;

    @Column(name = "MaSanPham", nullable = false, length = 50)
    private String maSanPham;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CtHoadonId entity = (CtHoadonId) o;
        return Objects.equals(this.maSanPham, entity.maSanPham) &&
                Objects.equals(this.maHoaDon, entity.maHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSanPham, maHoaDon);
    }

}