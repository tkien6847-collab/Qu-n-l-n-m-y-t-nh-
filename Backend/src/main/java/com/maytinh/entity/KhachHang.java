package com.maytinh.entity;

import jakarta.persistence.*;

@Entity 
@Table (name = "KhachHang")
public class KhachHang {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private String  maKH;
         @ManyToOne
         @JoinColumn(name="MaTaiKhoan")
         private  TaiKhoan taiKhoan;
         @Column (name="TenKhachHang")
         private  String tenKhachHang;
         @Column (name="SDT")
         private String sDT;
         @Column (name="Email")
         private String email;
         @Column (name="DiaChi")
         private String diaChi;

         public KhachHang(){

         }

    public KhachHang(String maKH, String diaChi, String email, String sDT, String tenKhachHang, TaiKhoan taiKhoan) {
        this.maKH = maKH;
        this.diaChi = diaChi;
        this.email = email;
        this.sDT = sDT;
        this.tenKhachHang = tenKhachHang;
        this.taiKhoan = taiKhoan;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

}
