package Entity;

import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private Date ngayLap;
	private double tienKhachDua;
	private float diemTichDuoc;
	private NhanVien nv;
	private KhachHang khachHang;
	private double tongTien;
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public double getTienKhachDua() {
		return tienKhachDua;
	}
	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}
	public float getDiemTichDuoc() {
		return diemTichDuoc;
	}
	public void setDiemTichDuoc(float diemTichDuoc) {
		this.diemTichDuoc = diemTichDuoc;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", tienKhachDua=" + tienKhachDua
				+ ", diemTichDuoc=" + diemTichDuoc + ", nv=" + nv + ", khachHang=" + khachHang + ", tongTien="
				+ tongTien + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	public HoaDon(String maHoaDon, Date ngayLap, double tienKhachDua, float diemTichDuoc, NhanVien nv,
			KhachHang khachHang, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.tienKhachDua = tienKhachDua;
		this.diemTichDuoc = diemTichDuoc;
		this.nv = nv;
		this.khachHang = khachHang;
		this.tongTien = tongTien;
	}
	public HoaDon(String mahd, Double tienKD, KhachHang kh, NhanVien nv2, double tongTien2, Date ngayLap2,
			float diemTich) {
		// TODO Auto-generated constructor stub
	}
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}