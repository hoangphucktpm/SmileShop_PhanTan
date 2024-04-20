package Entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "SanPham.getMaChatLieu", query = "SELECT c.maChatLieu FROM ChatLieu c WHERE c.tenChatLieu = :ten AND c.moTa = :moTa"),
        @NamedQuery(name = "SanPham.getTenChatLieu", query = "SELECT c.tenChatLieu FROM ChatLieu c WHERE c.maChatLieu = :ma"),
        @NamedQuery(name = "SanPham.getMoTaChatLieu", query = "SELECT c.moTa FROM ChatLieu c WHERE c.maChatLieu = :ma"),
        @NamedQuery(name ="SanPham.getlistSize", query = "SELECT s FROM SanPham s WHERE s.size = :kthuoc"),
        @NamedQuery(name = "SanPham.getlistTenMauSac", query = "SELECT s FROM SanPham s WHERE s.mauSac = :MS"),
        @NamedQuery(name = "SanPham.getlistTenCL", query = "SELECT s FROM SanPham s WHERE s.chatLieu.tenChatLieu = :ChatLieu"),
        @NamedQuery(name = "SanPham.getlistTenNCC", query = "SELECT s FROM SanPham s WHERE s.nhaCungCap.tenNhaCungCap = :nccap"),
        @NamedQuery(name = "SanPham.getlistTenLoaiSP", query = "SELECT s FROM SanPham s WHERE s.loaiSanPham.tenLoaiSP = :lsp"),
        @NamedQuery(name = "SanPham.getTenSP", query = "SELECT s FROM SanPham s WHERE s.tensp = :Ten"),
        @NamedQuery(name = "SanPham.getMa", query = "SELECT s FROM SanPham s WHERE s.maSp = :Ma"),
        @NamedQuery(name = "SanPham.getTenCL", query = "SELECT s.chatLieu.tenChatLieu FROM SanPham s WHERE s.maSp = :maSP"),
        @NamedQuery(name = "SanPham.getTenNhaCC", query = "SELECT s.nhaCungCap.tenNhaCungCap FROM SanPham s WHERE s.maSp = :maSP"),
        @NamedQuery(name = "SanPham.getTenLoaiSP", query = "SELECT s.loaiSanPham.tenLoaiSP FROM SanPham s WHERE s.maSp = :maSP"),
        @NamedQuery(name = "SanPham.soLuongChatLieu", query = "SELECT COUNT(c) FROM ChatLieu c"),
        @NamedQuery(name = "SanPham.soLuongLSP", query = "SELECT COUNT(l) FROM LoaiSanPham l"),
        @NamedQuery(name = "SanPham.vat", query = "SELECT s.vat FROM SanPham s WHERE s.maSp = :ma"),
        @NamedQuery(name="SanPham.soLuong", query = "SELECT COUNT(s) FROM SanPham s"),
        @NamedQuery(name = "SanPham.getKMTheoTen", query = "SELECT DISTINCT sp.khuyenMai FROM SanPham sp"),
        @NamedQuery(name = "SanPham.getKMTheoPhanTram", query = "SELECT sp.khuyenMai.phanTramKhuyenMai FROM SanPham sp WHERE sp.maSp = :maSP"),
        @NamedQuery(name = "SanPham.getTenNCC", query = "SELECT DISTINCT sp.nhaCungCap.tenNhaCungCap FROM SanPham sp"),
        @NamedQuery(name = "SanPham.getChatLieu", query = "SELECT DISTINCT sp.chatLieu FROM SanPham sp"),
        @NamedQuery(name = "SanPham.getLoaiSP", query = "SELECT DISTINCT sp.loaiSanPham FROM SanPham sp"),
         @NamedQuery(name = "SanPham.sua",query = "UPDATE SanPham s SET s.tensp = :tenSP, s.nhaCungCap = :nhaCungCap, s.khuyenMai = :km, s.giaBan = :giaBan, s.soluong = :soluong, s.ngaynhap = :ngayNhap, s.hinhanh = :hinhAnh, s.mauSac = :color, s.size = :size, s.chatLieu = :chatLieu, s.tinhTrang = :tinhTrang, s.donViTinh = :dvt, s.loaiSanPham = :loaiSP, s.vat = :VAT, s.giaBan = :giaBanRa WHERE s.maSp = :maSP"),
        @NamedQuery(name = "SanPham.findAllSP", query = "SELECT s FROM SanPham s"),
        @NamedQuery(name = "SanPham.suaKhongAnh", query = "UPDATE SanPham s SET s.tensp = :tenSP, s.nhaCungCap = :nhaCungCap, s.khuyenMai = :km, s.giaBan = :giaBan, s.soluong = :soluong, s.ngaynhap = :ngayNhap, s.mauSac = :color, s.size = :size, s.chatLieu = :chatLieu, s.tinhTrang = :tinhTrang, s.donViTinh = :dvt, s.loaiSanPham = :loaiSP, s.vat = :VAT, s.giaBan = :giaBanRa WHERE s.maSp = :maSP"),
        @NamedQuery(name = "SanPham.timTheoGia", query = "SELECT s FROM SanPham s WHERE s.giaBan BETWEEN :gia1 AND :gia2"),
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
    private Date ngaynhap;

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

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
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

    public SanPham(String maSp, String tensp, NhaCungCap nhaCungCap, KhuyenMai khuyenMai, Double gianhap, Integer soluong, Date ngaynhap, String hinhanh, String mauSac, String size, ChatLieu chatLieu, Boolean tinhTrang, String donViTinh, LoaiSanPham loaiSanPham, Short vat, Double giaBan) {
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
