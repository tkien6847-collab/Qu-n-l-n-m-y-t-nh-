package com.maytinh.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name= "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String maSP;
    @Column(name="TenSP")
    private String tenSP;
    @Column(name = "MoTa")
    private String moTa;
    @Column(name="DonGia")
    private BigDecimal donGia;
    @Column (name="SoLuongTon")
    private Integer soLuongTon;

    public SanPham(){

    }
    public SanPham(String maSP, Integer soLuongTon, BigDecimal donGia, String moTa, String tenSP) {
        this.maSP = maSP;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.moTa = moTa;
        this.tenSP = tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
