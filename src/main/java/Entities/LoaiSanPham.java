package Entities;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class LoaiSanPham {
    @Id
    @Column(name = "MaLoaiSP", nullable = false, length = 50)
    private String maLoaiSP;

    @Column(name = "TenLoaiSP", nullable = false, length = 50)
    private String tenLoaiSP;

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }


    public LoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "maLoaiSP='" + maLoaiSP + '\'' +
                ", tenLoaiSP='" + tenLoaiSP + '\'' +
                '}';
    }
}