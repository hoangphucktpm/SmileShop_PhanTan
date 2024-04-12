package Entity;

import java.util.Date;
import java.util.Objects;

public class KhachHang {
	public String maKH;
	private String tenKH;
	private String sdt;
	private String CCCD;
	private Date ngaySinh;
	private String diaChi;
	public int gioiTinh;
	private LoaiKhachHang loaiKH;
	private String Email;
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
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
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
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public float getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(float diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
	public KhachHang(String maKH, String tenKH, String sdt, String cCCD, Date ngaySinh, String diaChi, int gioiTinh,
			LoaiKhachHang loaiKH, String email, float diemTichLuy) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		CCCD = cCCD;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.loaiKH = loaiKH;
		Email = email;
		this.diemTichLuy = diemTichLuy;
	}
	
	public KhachHang(String tenKH) {
		super();
		this.tenKH = tenKH;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", CCCD=" + CCCD + ", ngaySinh="
				+ ngaySinh + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", loaiKH=" + loaiKH + ", Email=" + Email
				+ ", diemTichLuy=" + diemTichLuy + "]";
	}
	
	
	
	
}
