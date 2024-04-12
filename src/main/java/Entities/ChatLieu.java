package Entities;



import jakarta.persistence.*;
import lombok.*;
@Entity
public class ChatLieu {
    @Id
    @Column(name = "MaChatLieu", nullable = false, length = 50)
    private String maChatLieu;

    @Column(name = "TenChatLieu", nullable = false, length = 50)
    private String tenChatLieu;

    @Column(name = "MoTa", length = 50)
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

    public ChatLieu(String maChatLieu, String tenChatLieu, String moTa) {
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.moTa = moTa;
    }

    public ChatLieu() {
    }

    @Override
    public String toString() {
        return "ChatLieu{" +
                "maChatLieu='" + maChatLieu + '\'' +
                ", tenChatLieu='" + tenChatLieu + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}