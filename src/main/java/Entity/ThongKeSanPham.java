package Entity;

import java.util.Date;

public class ThongKeSanPham {
	private String maSP;
	private String tenSP;
	private String loai;
	private int soLuongNhap;
	private Date ngayNhap;
	private double giaNhap;
	private double giaBan;
	private String mauSac;
	private String kichThuoc;
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public String getKichThuoc() {
		return kichThuoc;
	}
	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}
	public ThongKeSanPham(String maSP, String tenSP, String loai, int soLuongNhap, Date ngayNhap, double giaNhap,
			double giaBan, String mauSac, String kichThuoc) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loai = loai;
		this.soLuongNhap = soLuongNhap;
		this.ngayNhap = ngayNhap;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.mauSac = mauSac;
		this.kichThuoc = kichThuoc;
	}
	public ThongKeSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThongKeSanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", loai=" + loai + ", soLuongNhap=" + soLuongNhap
				+ ", ngayNhap=" + ngayNhap + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", mauSac=" + mauSac
				+ ", kichThuoc=" + kichThuoc + "]";
	}
	
	
	
}
