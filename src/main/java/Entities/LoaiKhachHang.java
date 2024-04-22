package Entities;

import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "loaiKhachHang")

@NamedQueries({
        @NamedQuery(name = "LoaiKhachHang.findAll", query = "SELECT lkh FROM LoaiKhachHang lkh"),
})
public class LoaiKhachHang implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MaLoaiKH", nullable = false, length = 50)
    private String maLoaiKH;

    @Column(name = "TenLoaiKH", nullable = false, length = 50)
    private String tenLoaiKH;

    public String getMaLoaiKH() {
        return maLoaiKH;
    }

    public void setMaLoaiKH(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public String getTenLoaiKH() {
        return tenLoaiKH;
    }

    public void setTenLoaiKH(String tenLoaiKH) {
        this.tenLoaiKH = tenLoaiKH;
    }

    public LoaiKhachHang(String maLoaiKH) {
        super();
        this.maLoaiKH = maLoaiKH;
    }

    public LoaiKhachHang(String maLoaiKH, String tenLoaiKH) {
        this.maLoaiKH = maLoaiKH;
        this.tenLoaiKH = tenLoaiKH;
    }

    @Override
    public String toString() {
        return "LoaiKhachHang{" +
                "maLoaiKH='" + maLoaiKH + '\'' +
                ", tenLoaiKH='" + tenLoaiKH + '\'' +
                '}';
    }
}