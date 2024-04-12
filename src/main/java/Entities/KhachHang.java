package Entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Entity

@NamedQueries({
    @NamedQuery(name = "KhachHang.findAll", query = "SELECT kh FROM KhachHang kh"),
        @NamedQuery(name = "KhachHang.findByMaKH", query = "SELECT kh FROM KhachHang kh WHERE kh.maKH = :maKH"),
        @NamedQuery(name = "KhachHang.find5kPoint", query = "SELECT kh FROM KhachHang kh WHERE kh.diemTichLuy >= 5000"),
        @NamedQuery(name = "KhachHang.findKHByName", query = "SELECT kh FROM KhachHang kh WHERE kh.tenKH = :tenKH"),
        @NamedQuery(name = "KhachHang.findKHBySDT", query = "SELECT kh FROM KhachHang kh WHERE kh.sdt = :sdt"),
        @NamedQuery(name = "KhachHang.findKHByCCCD", query = "SELECT kh FROM KhachHang kh WHERE kh.cccd = :cccd"),
        @NamedQuery(name = "KhachHang.findByGender", query = "SELECT kh FROM KhachHang kh WHERE kh.gioiTinh = :gioiTinh"),
        @NamedQuery(name = "KhachHang.findByLoaiKH", query = "SELECT kh FROM KhachHang kh WHERE kh.loaiKH.tenLoaiKH = :tenLoaiKH"),
})
public class KhachHang {
    @Id
    @Column(name = "MaKH", nullable = false, length = 50)
    private String maKH;

    @Column(name = "TenKH", nullable = false, length = 50)
    private String tenKH;

    @Column(name = "Sdt", nullable = false, length = 50)
    private String sdt;

    @Column(name = "CCCD", length = 50)
    private String cccd;

    @Column(name = "NgaySinh", nullable = false)
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "GioiTinh", columnDefinition = "tinyint not null")
    private int gioiTinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LoaiKH", nullable = false)
    private LoaiKhachHang loaiKH;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "diemTichLuy")
    private float diemTichLuy;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LoaiKhachHang getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(LoaiKhachHang loaiKH) {
        this.loaiKH = loaiKH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(float diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public KhachHang(String maKH, String tenKH, String sdt, String cccd, Date ngaySinh, String diaChi, int gioiTinh, LoaiKhachHang loaiKH, String email, float diemTichLuy) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.cccd = cccd;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.loaiKH = loaiKH;
        this.email = email;
        this.diemTichLuy = diemTichLuy;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", sdt='" + sdt + '\'' +
                ", cccd='" + cccd + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", loaiKH=" + loaiKH +
                ", email='" + email + '\'' +
                ", diemTichLuy=" + diemTichLuy +
                '}';
    }
}