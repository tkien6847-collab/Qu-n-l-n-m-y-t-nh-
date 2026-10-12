package com.maytinh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietDonId implements Serializable {

    @Column(name = "MaDonHang", length = 20)
    private String maDonHang;

    @Column(name = "MaSP", length = 20)
    private String maSP;

    public ChiTietDonId() {
    }

    public ChiTietDonId(String maDonHang, String maSP) {
        this.maDonHang = maDonHang;
        this.maSP = maSP;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietDonId)) return false;
        ChiTietDonId that = (ChiTietDonId) o;
        return Objects.equals(maDonHang, that.maDonHang) && Objects.equals(maSP, that.maSP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDonHang, maSP);
    }
}