package Entity;

public class taiKhoan {
	private String tenDangNhap;
	private String matKhau;
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public taiKhoan(String tenDangNhap, String matKhau) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}
	public taiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "taiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + "]";
	}
	
	
}
