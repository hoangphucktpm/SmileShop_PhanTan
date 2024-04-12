package Entity;

import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private int caLamViec;
	private String tenNV;
	private Date ngaySinh;
	private int gioiTinh;
	private String cmnd;
	private String email;
	private String soDienThoai;
	private int chucVu;
	public boolean trangThai;
	private String diaChi;
	private String hinhAnh;
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public int getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(int caLamViec) {
		this.caLamViec = caLamViec;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public int getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public int getChucVu() {
		return chucVu;
	}
	public void setChucVu(int chucVu) {
		this.chucVu = chucVu;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return maNhanVien == other.maNhanVien;
	}
	
	public NhanVien(String maNhanVien, int caLamViec, String tenNV, Date ngaySinh, int gioiTinh, String cmnd,
			String email, String soDienThoai, int chucVu, boolean trangThai, String diaChi, String hinhAnh) {
		super();
		this.maNhanVien = maNhanVien;
		this.caLamViec = caLamViec;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.chucVu = chucVu;
		this.trangThai = trangThai;
		this.diaChi = diaChi;
		this.hinhAnh = hinhAnh;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NhanVien(String tenNV) {
		super();
		this.tenNV = tenNV;
	}
	@Override
	public String toString() {
		return "nhanVien [maNhanVien=" + maNhanVien + ", caLamViec=" + caLamViec + ", tenNV=" + tenNV + ", ngaySinh="
				+ ngaySinh + ", gioiTinh=" + gioiTinh + ", cmnd=" + cmnd + ", email=" + email + ", soDienThoai="
				+ soDienThoai + ", chucVu=" + chucVu + ", trangThai=" + trangThai + ", diaChi=" + diaChi + ", hinhAnh="
				+ hinhAnh + "]";
	}

	
}
