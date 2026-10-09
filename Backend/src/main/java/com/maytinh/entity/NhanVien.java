package com.maytinh.entity;

import jakarta.persistence.*;

@Entity
@Table (name="KhachHang")

public class NhanVien {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String  maNV;
    @Column(name="TenNV")
    private String tenNV;
    @Column (name="SDT")
    private String sDT;
    @Column (name="Email")
    private String email;
    @ManyToOne
    @JoinColumn(name="MaTaiKhoan")
    private TaiKhoan taiKhoan;

    public NhanVien(){}
    public NhanVien(String maNV, TaiKhoan taiKhoan, String email, String sDT, String tenNV) {
        this.maNV = maNV;
        this.taiKhoan = taiKhoan;
        this.email = email;
        this.sDT = sDT;
        this.tenNV = tenNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
}
