package Entity;

public class ChiTietHoaDon {
	private int soLuongSP;
	private sanPham SanPham;
	private HoaDon hoaDon;
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	public sanPham getSanPham() {
		return SanPham;
	}
	public void setSanPham(sanPham sanPham) {
		SanPham = sanPham;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuongSP=" + soLuongSP + ", SanPham=" + SanPham
				+ ", hoaDon=" + hoaDon + "]";
	}
	public ChiTietHoaDon(int soLuongSP, float diemSuDung, sanPham sanPham, HoaDon hoaDon) {
		super();
		this.soLuongSP = soLuongSP;
		SanPham = sanPham;
		this.hoaDon = hoaDon;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
