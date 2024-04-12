package Entity;

import java.util.Objects;

public class ChatLieu {
	private String maChatLieu;
	private String tenChatLieu;
	private String moTa;
	public String getMaChatLieu() {
		return maChatLieu;
	}
	public void setMaChatLieu(String maChatLieu) {
		this.maChatLieu = maChatLieu;
	}
	public String getTenChatLieu() {
		return tenChatLieu;
	}
	public void setTenChatLieu(String tenChatLieu) {
		this.tenChatLieu = tenChatLieu;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChatLieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatLieu other = (ChatLieu) obj;
		return Objects.equals(maChatLieu, other.maChatLieu);
	}
	public ChatLieu(String maChatLieu, String tenChatLieu, String moTa) {
		super();
		this.maChatLieu = maChatLieu;
		this.tenChatLieu = tenChatLieu;
		this.moTa = moTa;
	}
	public ChatLieu(String maChatLieu) {
		super();
		this.maChatLieu = maChatLieu;
	}
	public ChatLieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "chatLieu [maChatLieu=" + maChatLieu + ", tenChatLieu=" + tenChatLieu + ", moTa=" + moTa + "]";
	}
	
}
