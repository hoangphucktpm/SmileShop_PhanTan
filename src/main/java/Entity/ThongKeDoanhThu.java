package Entity;

public class ThongKeDoanhThu {
	private String maSP;
	private String tenSP;
	private String mauSac;
	private String size;
	private int khuyenMai;
	private double giaNhap;
	private int soLuong;
	private int soLuongBan;
	private double giaBan;
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
	public int getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public ThongKeDoanhThu(String maSP, String tenSP, String mauSac, String size, int khuyenMai, double giaNhap,
			int soLuong, int soLuongBan, double giaBan) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.mauSac = mauSac;
		this.size = size;
		this.khuyenMai = khuyenMai;
		this.giaNhap = giaNhap;
		this.soLuong = soLuong;
		this.soLuongBan = soLuongBan;
		this.giaBan = giaBan;
	}
	public ThongKeDoanhThu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThongKeDoanhThu [maSP=" + maSP + ", tenSP=" + tenSP + ", mauSac=" + mauSac + ", size=" + size
				+ ", khuyenMai=" + khuyenMai + ", giaNhap=" + giaNhap + ", soLuong=" + soLuong + ", soLuongBan="
				+ soLuongBan + ", giaBan=" + giaBan + "]";
	}
	
	
}
