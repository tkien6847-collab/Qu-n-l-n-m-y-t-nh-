package com.maytinh.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name="DonHang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maDonHang;
    @Column(name="NgayDat")
    private LocalDateTime ngayDat;
    @Column(name="TongTien")
    private BigDecimal tongTien;
    @Column(name="TrangThai")
    private String trangThai;
    @ManyToOne
    @JoinColumn(name="MaKH")
    private KhachHang khachHang;

    public DonHang(String maDonHang, KhachHang khachHang, BigDecimal tongTien, String trangThai, LocalDateTime ngayDat) {
        this.maDonHang = maDonHang;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ngayDat = ngayDat;
    }


    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }
}
