package Application;

import DAOTest.*;
import DAOTest.impl.KhachHangImpl;
import DAOTest.impl.KhuyenMaiImpl;
import DAOTest.impl.LapHoaDonImpl;
import DAOTest.impl.SanPhamImpl;
import Entities.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class application {
    public static void main(String[] args) {


        SanPhamDao Sp = new SanPhamImpl();
        System.out.println(Sp.getKMTheoTenOne("KM001").getTenKhuyenMai());



    }
}
