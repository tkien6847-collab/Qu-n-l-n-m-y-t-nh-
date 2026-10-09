
package com.maytinh.entity;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietDonId implements Serializable {//đối tượng thuộc class này có khả năng tuần ự hóa

    private String maDonHang;
    private String maSP;

    public ChiTietDonId() {
    }

    public ChiTietDonId(String maDonHang, String maSP) {
        this.maDonHang = maDonHang;
        this.maSP = maSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietDonId)) return false;

        ChiTietDonId that = (ChiTietDonId) o;

        return Objects.equals(maDonHang, that.maDonHang)
                && Objects.equals(maSP, that.maSP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDonHang, maSP);
    }
}