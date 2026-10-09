package com.maytinh.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ChiTietDon")
@IdClass(ChiTietDonId.class)
public class ChiTietDon {


    @Column(name = "MaDonHang")
    private String donHang;


    @Column(name = "MaSP")
    private String sanPham;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGia;

    public ChiTietDon() {
    }

    public ChiTietDon(String donHang, BigDecimal donGia, Integer soLuong, String sanPham) {
        this.donHang = donHang;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.sanPham = sanPham;
    }


    public String getDonHang() {
        return donHang;
    }

    public void setDonHang(String donHang) {
        this.donHang = donHang;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }
}