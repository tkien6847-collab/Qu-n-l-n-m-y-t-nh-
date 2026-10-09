package com.maytinh.service;

import com.maytinh.entity.KhachHang;
import com.maytinh.repository.KhachHangRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class KhachHangService {


    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    // Lấy danh sách khách hàng
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    // Tìm khách hàng theo mã
    public KhachHang getById(String maKH) {
        return khachHangRepository.findById(maKH)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Không tìm thấy khách hàng: " + maKH));
    }

    // Thêm khách hàng
    @Transactional
    public KhachHang create(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    // Cập nhật khách hàng
    @Transactional
    public KhachHang update(String maKH, KhachHang khachHangMoi) {
        KhachHang khachHangCu = getById(maKH);

        // Cập nhật các trường được phép sửa tại đây

        return khachHangRepository.save(khachHangCu);
    }

    // Xóa khách hàng
    @Transactional
    public void delete(String maKH) {
        KhachHang khachHang = getById(maKH);
        khachHangRepository.delete(khachHang);
    }


}
