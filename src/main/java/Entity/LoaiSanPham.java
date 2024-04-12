package Entity;

import java.util.Objects;

public class LoaiSanPham {
	private String maLoai;
	private String tenLoai;
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSanPham other = (LoaiSanPham) obj;
		return Objects.equals(maLoai, other.maLoai);
	}
	public LoaiSanPham(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	public LoaiSanPham(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "loaiSanPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
}
