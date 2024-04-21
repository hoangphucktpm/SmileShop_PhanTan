package Entities;

import java.io.Serializable;

public enum MauSac implements Serializable {
    Đỏ("Đỏ"),
    Xanh("Xanh"),
    Vàng("Vàng"),
    Tím("Tím"),
    Trắng("Trắng"),
    Đen("Đen"),
    Cam("Cam"),
    Xanh_da_trời("Xanh_da_trời"),
    Hồng_nhạt("Hồng_nhạt");
    public String nCo;

    private MauSac(String nCo) {
        this.nCo = nCo;
    }
}