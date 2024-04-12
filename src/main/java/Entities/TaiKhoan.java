package Entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
public class TaiKhoan implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TenTaiKhoan", nullable = false)
    private NhanVien tenTaiKhoan;

    @Column(name = "MatKhau", nullable = false, length = 50)
    private String matKhau;

    public NhanVien getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(NhanVien tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public TaiKhoan(NhanVien tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "tenTaiKhoan=" + tenTaiKhoan +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}