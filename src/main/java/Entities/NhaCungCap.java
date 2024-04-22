package Entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "NhaCungCap.getNhaCungCaps", query = "select ncc from NhaCungCap ncc"),
        @NamedQuery(name = "NhaCungCap.sua", query = "update NhaCungCap ncc set ncc.tenNhaCungCap = :ten, ncc.sdt = :sdt, ncc.email = :email, ncc.diaChi = :diaChi, ncc.tinhTrang = :tinhTrang where ncc.maNhaCungCap = :maNCC"),
        @NamedQuery(name = "NhaCungCap.getTen", query = "select ncc from NhaCungCap ncc where ncc.tenNhaCungCap like :tenNCC"),
        @NamedQuery(name = "NhaCungCap.getMa", query = "select ncc from NhaCungCap ncc where ncc.maNhaCungCap = :MaNCC"),
        @NamedQuery(name = "NhaCungCap.getSDT", query = "select ncc from NhaCungCap ncc where ncc.sdt like :SDT"),
        @NamedQuery(name = "NhaCungCap.getEmail", query = "select ncc from NhaCungCap ncc where ncc.email like :em "),
        @NamedQuery(name = "NhaCungCap.soLuongNCC", query = "select count(ncc) from NhaCungCap ncc"),


})
public class NhaCungCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaNhaCungCap", nullable = false, length = 50)
    private String maNhaCungCap;

    @Column(name = "TenNhaCungCap", nullable = false, length = 50)
    private String tenNhaCungCap;

    @Column(name = "Sdt", nullable = false, length = 50)
    private String sdt;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = false, length = 50)
    private String diaChi;

    @Column(name = "TinhTrang", columnDefinition = "tinyint not null")
    private int tinhTrang;

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdt, String email, String diaChi, int tinhTrang) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" +
                "maNhaCungCap='" + maNhaCungCap + '\'' +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", tinhTrang=" + tinhTrang +
                '}';
    }


}