package Entity;

public class LoaiKhachHang {
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
	public LoaiKhachHang(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	public LoaiKhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoaiKhachHang(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	@Override
	public String toString() {
		return "LoaiKhachHang [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
	
}
