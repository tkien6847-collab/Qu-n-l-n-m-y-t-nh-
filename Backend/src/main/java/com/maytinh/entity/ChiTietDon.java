package com.maytinh.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ChiTietDon")
public class ChiTietDon {

    @EmbeddedId
    private ChiTietDonId id;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGia;

    @ManyToOne
    @MapsId("maDonHang") // Trùng tên thuộc tính maDonHang trong ChiTietDonId
    @JoinColumn(name = "MaDonHang")
    private DonHang donHang;

    @ManyToOne
    @MapsId("maSP") // Trùng tên thuộc tính maSP trong ChiTietDonId
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    public ChiTietDon() {
    }

    public ChiTietDon(ChiTietDonId id, Integer soLuong, BigDecimal donGia) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietDonId getId() {
        return id;
    }

    public void setId(ChiTietDonId id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
}