package Entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "SanPham.getDTQuy", query = "SELECT s.maSp, s.tensp, s.mauSac, s.size, s.khuyenMai, s.gianhap, s.soluong, SUM(ct.soLuongSP) as tongSoLuong, s.giaBan\n" +
                "FROM SanPham s \n" +
                "JOIN CtHoadon ct ON s.maSp = ct.id.maSanPham \n" +
                "JOIN HoaDon h ON h.maHoaDon = ct.id.maHoaDon\n" +
                "JOIN NhanVien n ON n.maNhanvien = h.nhanVien\n" +
                "WHERE QUARTER(h.ngayLapHoaDon) = :quy AND HOUR(h.ngayLapHoaDon) >= :startHour  and HOUR(h.ngayLapHoaDon) < :endHour and year(h.ngayLapHoaDon) = 2023\n" +
                "GROUP BY s.maSp, s.tensp, s.mauSac, s.size, s.khuyenMai, s.gianhap, s.soluong, s.giaBan")
})
public class SanPham {
    @Id
    @Column(name = "maSp", nullable = false, length = 50)
    private String maSp;

    @Column(name = "Tensp", nullable = false, length = 50)
    private String tensp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NhaCungCap", nullable = false)
    private NhaCungCap nhaCungCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KhuyenMai")
    private KhuyenMai khuyenMai;

    @Column(name = "Gianhap", nullable = false)
    private Double gianhap;

    @Column(name = "Soluong", nullable = false)
    private Integer soluong;

    @Column(name = "Ngaynhap", nullable = false)
    private LocalDate ngaynhap;

    @Column(name = "Hinhanh", nullable = false, length = 50)
    private String hinhanh;

    @Column(name = "MauSac", nullable = false, length = 50)
    private String mauSac;

    @Column(name = "\"Size\"", nullable = false, length = 50)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ChatLieu", nullable = false)
    private ChatLieu chatLieu;

    @Column(name = "TinhTrang", nullable = false)
    private Boolean tinhTrang = false;

    @Column(name = "DonViTinh", nullable = false, length = 50)
    private String donViTinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LoaiSanPham", nullable = false)
    private LoaiSanPham loaiSanPham;

    @Column(name = "VAT", columnDefinition = "tinyint")
    private Short vat;

    @Column(name = "GiaBan")
    private Double giaBan;

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public Double getGianhap() {
        return gianhap;
    }

    public void setGianhap(Double gianhap) {
        this.gianhap = gianhap;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public LocalDate getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public Short getVat() {
        return vat;
    }

    public void setVat(Short vat) {
        this.vat = vat;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public SanPham(String maSp, String tensp, NhaCungCap nhaCungCap, KhuyenMai khuyenMai, Double gianhap, Integer soluong, LocalDate ngaynhap, String hinhanh, String mauSac, String size, ChatLieu chatLieu, Boolean tinhTrang, String donViTinh, LoaiSanPham loaiSanPham, Short vat, Double giaBan) {
        this.maSp = maSp;
        this.tensp = tensp;
        this.nhaCungCap = nhaCungCap;
        this.khuyenMai = khuyenMai;
        this.gianhap = gianhap;
        this.soluong = soluong;
        this.ngaynhap = ngaynhap;
        this.hinhanh = hinhanh;
        this.mauSac = mauSac;
        this.size = size;
        this.chatLieu = chatLieu;
        this.tinhTrang = tinhTrang;
        this.donViTinh = donViTinh;
        this.loaiSanPham = loaiSanPham;
        this.vat = vat;
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSp='" + maSp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", nhaCungCap=" + nhaCungCap +
                ", khuyenMai=" + khuyenMai +
                ", gianhap=" + gianhap +
                ", soluong=" + soluong +
                ", ngaynhap=" + ngaynhap +
                ", hinhanh='" + hinhanh + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", size='" + size + '\'' +
                ", chatLieu=" + chatLieu +
                ", tinhTrang=" + tinhTrang +
                ", donViTinh='" + donViTinh + '\'' +
                ", loaiSanPham=" + loaiSanPham +
                ", vat=" + vat +
                ", giaBan=" + giaBan +
                '}';
    }
}
