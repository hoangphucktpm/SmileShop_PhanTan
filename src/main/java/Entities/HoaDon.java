package Entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity

@NamedQueries({
        @NamedQuery(name = "HoaDon.findAll", query = "SELECT hd FROM HoaDon hd"),
        @NamedQuery(name = "HoaDon.findByMaHoaDon", query = "SELECT hd FROM HoaDon hd WHERE hd.maHoaDon = :maHoaDon"),
        @NamedQuery(name = "HoaDon.findByNgayLapHoaDon", query = "SELECT hd FROM HoaDon hd WHERE hd.ngayLapHoaDon = :ngayLapHoaDon"),
        @NamedQuery(name = "HoaDon.findByTongTien", query = "SELECT hd FROM HoaDon hd WHERE hd.tongTien = :tongTien"),
        @NamedQuery(name = "HoaDon.findByDiemTichDuoc", query = "SELECT hd FROM HoaDon hd WHERE hd.diemTichDuoc = :diemTichDuoc"),
})


public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MaHoaDon", nullable = false, length = 50)
    private String maHoaDon;

    @Column(name = "TienKhachDua", nullable = false)
    private Double tienKhachDua;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "KhachHang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NhanVien", nullable = false)
    private NhanVien nhanVien;

    @Column(name = "TongTien", nullable = false)
    private Double tongTien;

    @Column(name = "NgayLapHoaDon", nullable = false)
    private Timestamp ngayLapHoaDon;


    @Column(name = "DiemTichDuoc", nullable = false)
    private Double diemTichDuoc;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(Double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Timestamp getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Timestamp ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public Double getDiemTichDuoc() {
        return diemTichDuoc;
    }

    public void setDiemTichDuoc(Double diemTichDuoc) {
        this.diemTichDuoc = diemTichDuoc;
    }

    public HoaDon(String maHoaDon, Double tienKhachDua, KhachHang khachHang, NhanVien nhanVien, Double tongTien, Timestamp ngayLapHoaDon, Double diemTichDuoc) {
        this.maHoaDon = maHoaDon;
        this.tienKhachDua = tienKhachDua;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.tongTien = tongTien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.diemTichDuoc = diemTichDuoc;
    }

    public HoaDon(String maHoaDon) {
    }

    public HoaDon() {
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", tienKhachDua=" + tienKhachDua +
                ", khachHang=" + khachHang +
                ", nhanVien=" + nhanVien +
                ", tongTien=" + tongTien +
                ", ngayLapHoaDon=" + ngayLapHoaDon +
                ", diemTichDuoc=" + diemTichDuoc +
                '}';
    }
}


