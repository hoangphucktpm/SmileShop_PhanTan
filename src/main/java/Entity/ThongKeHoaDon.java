package Entity;

import java.util.Date;

public class ThongKeHoaDon {
	private String maHoaDon;
	private String maNhanVien;
	private int caLamViec;
	private int soLuongSP;
	private Date ngayLap;
	private String loaiKH;
	private double doanhThu;
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
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
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getLoaiKH() {
		return loaiKH;
	}
	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	public ThongKeHoaDon(String maHoaDon, String maNhanVien, int caLamViec, int soLuongSP, Date ngayLap, String loaiKH,
			double doanhThu) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.caLamViec = caLamViec;
		this.soLuongSP = soLuongSP;
		this.ngayLap = ngayLap;
		this.loaiKH = loaiKH;
		this.doanhThu = doanhThu;
	}
	public ThongKeHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThongKeHoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", caLamViec=" + caLamViec
				+ ", soLuongSP=" + soLuongSP + ", ngayLap=" + ngayLap + ", loaiKH=" + loaiKH + ", doanhThu=" + doanhThu
				+ "]";
	}

	
}
