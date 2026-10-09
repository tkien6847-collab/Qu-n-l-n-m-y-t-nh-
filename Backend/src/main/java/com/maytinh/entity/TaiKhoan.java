package com.maytinh.entity;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.*;

@Entity 
@Table (name = "TaiKhoan")
public class TaiKhoan {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "MaTaiKhoan")
     private Integer maTaiKhoan;
    @Column (name = "TenDangNhap")
    private  String  tenDangNhap;
    @Column (name = "MatKhau")
    private String matKhau;

    @ManyToOne
    @JoinColumn (name="MaVaiTro")
    private VaiTro  vaitro;

    public TaiKhoan(){

    }

    public TaiKhoan(Integer maTaiKhoan, VaiTro vaitro, String matKhau, String tenDangNhap) {
        this.maTaiKhoan = maTaiKhoan;
        this.vaitro = vaitro;
        this.matKhau = matKhau;
        this.tenDangNhap = tenDangNhap;
    }


    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public VaiTro getVaitro() {
        return vaitro;
    }

    public void setVaitro(VaiTro vaitro) {
        this.vaitro = vaitro;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
}
// secu log in out 