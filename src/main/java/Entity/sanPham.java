package Entity;

import java.util.Date;
import java.util.Objects;

import Entity.NhaCungCap;
public class sanPham {
	private String maSP;
	private String tenSP;
	private Double giaNhap;
	private int soLuong;
	private Date ngayNhap;
	private String hinhAnh;
	public enum MauSac {Đỏ("Đỏ"), 
		Xanh("Xanh"), 
		Vàng("Vàng"), 
		Tím("Tím"), 
		Trắng("Trắng"), 
		Đen("Đen"), 
		Cam("Cam"), 
		Xanh_da_trời("Xanh da trời"), 
		Hồng_nhạt("Hồng nhạt");
		public String nCo;
		private MauSac (String nCo)
		{
			this.nCo = nCo;
		}
	}
	public enum Size {S("S"), M("M"), L("L"), XL("XL"), XXL("XXL"), XXXL("XXXL"), FREE_SIZE("FREE SIZE");
		public String nSiz;
		private Size (String nSiz)
		{
			this.nSiz = nSiz;
		}
		}
	private MauSac mauSac;
	private Size size;
	private ChatLieu chatLieu;
	private boolean tinhTrang;
	private String donVitinh;
	private LoaiSanPham maLoai;
	private NhaCungCap nhaCungCap;
	private KhuyenMai khuyenMai;
	private Double giaBan;
	private int VAT;
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
	public Double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(Double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public MauSac getMauSac() {
		return mauSac;
	}
	public void setMauSac(MauSac mauSac) {
		this.mauSac = mauSac;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public LoaiSanPham getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(LoaiSanPham maLoai) {
		this.maLoai = maLoai;
	}
	public ChatLieu getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(ChatLieu chatLieu) {
		this.chatLieu = chatLieu;
	}
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getDonVitinh() {
		return donVitinh;
	}
	public void setDonVitinh(String donVitinh) {
		this.donVitinh = donVitinh;
	}
	
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan =  giaBan;
	}
	
	public float getVAT() {
		return VAT;
	}
	public void setVAT(int vAT) {
		VAT = vAT;
	}
	@Override
	public String toString() {
		return "sanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", giaNhap=" + giaNhap + ", soLuong=" + soLuong
				+ ", ngayNhap=" + ngayNhap + ", hinhAnh=" + hinhAnh + ", mauSac=" + mauSac + ", size=" + size
				+ ", chatLieu=" + chatLieu + ", tinhTrang=" + tinhTrang + ", donVitinh=" + donVitinh + ", maLoai="
				+ maLoai + ", nhaCungCap=" + nhaCungCap + ", khuyenMai=" + khuyenMai + ", giaBan=" + giaBan + ", VAT="
				+ VAT + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		sanPham other = (sanPham) obj;
		return maSP == other.maSP;
	}


	
	public sanPham(String maSP, String tenSP, Double giaNhap, int soLuong, Date ngayNhap) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaNhap = giaNhap;
		this.soLuong = soLuong;
		this.ngayNhap = ngayNhap;
	}
	
	public sanPham(String maSP, String tenSP, Double giaNhap, int soLuong, Date ngayNhap, String hinhAnh, MauSac mauSac,
			Size size, ChatLieu chatLieu, boolean tinhTrang, String donVitinh, LoaiSanPham maLoai,
			NhaCungCap nhaCungCap, KhuyenMai khuyenMai, Double giaBan, int vAT) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaNhap = giaNhap;
		this.soLuong = soLuong;
		this.ngayNhap = ngayNhap;
		this.hinhAnh = hinhAnh;
		this.mauSac = mauSac;
		this.size = size;
		this.chatLieu = chatLieu;
		this.tinhTrang = tinhTrang;
		this.donVitinh = donVitinh;
		this.maLoai = maLoai;
		this.nhaCungCap = nhaCungCap;
		this.khuyenMai = khuyenMai;
		this.giaBan = giaBan;
		VAT = vAT;
	}
	public sanPham(String maSP, String tenSP) {
		super();
		// TODO Auto-generated constructor stub
	}
	public sanPham() {
		// TODO Auto-generated constructor stub
	}
	
	public java.sql.Date getNgayBatDau() {
		// TODO Auto-generated method stub
		return null;
	}
	public java.sql.Date getNgayKetThuc() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
