package com.maytinh.service;

import com.maytinh.entity.DonHang;
import com.maytinh.repository.DonHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangService {

    private final DonHangRepository repository;

    public DonHangService(DonHangRepository repository) {
        this.repository = repository;
    }

    // Lấy danh sách đơn hàng
    public List<DonHang> getAll() {
        return repository.findAll();
    }

    // Tìm đơn hàng theo mã
    public DonHang getById(String maDonHang) {
        return repository.findById(maDonHang)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy đơn hàng: " + maDonHang));
    }

    // Thêm đơn hàng
    public DonHang create(DonHang donHang) {
        return repository.save(donHang);
    }

    // Cập nhật đơn hàng
    public DonHang update(String maDonHang, DonHang donHangMoi) {
        DonHang donHangCu = getById(maDonHang);

        // Cập nhật các thuộc tính đơn hàng tại đây

        return repository.save(donHangCu);
    }

    // Xóa đơn hàng
    public void delete(String maDonHang) {
        repository.delete(getById(maDonHang));
    }

}
