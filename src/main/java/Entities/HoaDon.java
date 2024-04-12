package Entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@NoArgsConstructor
public class HoaDon {
    @Id
    @Column(name = "MaHoaDon", nullable = false, length = 50)
    private String maHoaDon;

    @Column(name = "TienKhachDua", nullable = false)
    private Double tienKhachDua;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "KhachHang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NhanVien", nullable = false)
    private NhanVien nhanVien;

    @Column(name = "TongTien", nullable = false)
    private Double tongTien;

    @Column(name = "NgayLapHoaDon", nullable = false)
    private Instant ngayLapHoaDon;

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

    public Instant getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Instant ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public Double getDiemTichDuoc() {
        return diemTichDuoc;
    }

    public void setDiemTichDuoc(Double diemTichDuoc) {
        this.diemTichDuoc = diemTichDuoc;
    }

    public HoaDon(String maHoaDon, Double tienKhachDua, KhachHang khachHang, NhanVien nhanVien, Double tongTien, Instant ngayLapHoaDon, Double diemTichDuoc) {
        this.maHoaDon = maHoaDon;
        this.tienKhachDua = tienKhachDua;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.tongTien = tongTien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.diemTichDuoc = diemTichDuoc;
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