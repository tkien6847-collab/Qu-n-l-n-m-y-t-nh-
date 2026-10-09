package com.maytinh.service;

import com.maytinh.entity.ChiTietDon;
import com.maytinh.entity.ChiTietDonId;
import com.maytinh.repository.ChiTietDonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietDonService {

    private final ChiTietDonRepository repository;

    public ChiTietDonService(ChiTietDonRepository repository) {
        this.repository = repository;
    }

    // Lấy danh sách chi tiết đơn hàng
    public List<ChiTietDon> getAll() {
        return repository.findAll();
    }

    // Tìm chi tiết theo mã đơn hàng và mã sản phẩm
    public ChiTietDon getById(ChiTietDonId id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy chi tiết đơn hàng"));
    }

    // Thêm chi tiết đơn hàng
    public ChiTietDon create(ChiTietDon chiTietDon) {
        return repository.save(chiTietDon);
    }

    // Cập nhật chi tiết đơn hàng
    public ChiTietDon update(ChiTietDonId id, ChiTietDon chiTietMoi) {
        ChiTietDon chiTietCu = getById(id);

        // Cập nhật số lượng, đơn giá... theo Entity thực tế

        return repository.save(chiTietCu);
    }

    // Xóa chi tiết đơn hàng
    public void delete(ChiTietDonId id) {
        repository.delete(getById(id));
    }
}
