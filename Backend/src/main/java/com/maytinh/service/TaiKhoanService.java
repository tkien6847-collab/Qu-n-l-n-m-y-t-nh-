package com.maytinh.service;

import com.maytinh.entity.TaiKhoan;
import com.maytinh.repository.TaiKhoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {
    private final TaiKhoanRepository repository;

    public TaiKhoanService(TaiKhoanRepository repository) {
        this.repository = repository;
    }

    // Lấy danh sách tài khoản
    public List<TaiKhoan> getAll() {
        return repository.findAll();
    }

    // Tìm tài khoản theo mã
    public TaiKhoan getById(Integer maTaiKhoan) {
        return repository.findById(maTaiKhoan)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy tài khoản: " + maTaiKhoan));
    }

    // Thêm tài khoản
    public TaiKhoan create(TaiKhoan taiKhoan) {
        return repository.save(taiKhoan);
    }

    // Cập nhật tài khoản
    public TaiKhoan update(Integer maTaiKhoan, TaiKhoan taiKhoanMoi) {
        TaiKhoan taiKhoanCu = getById(maTaiKhoan);

        // Cập nhật các thuộc tính tại đây
        // Ví dụ: taiKhoanCu.setTenDangNhap(taiKhoanMoi.getTenDangNhap());

        return repository.save(taiKhoanCu);
    }

    // Xóa tài khoản
    public void delete(Integer maTaiKhoan) {
        repository.delete(getById(maTaiKhoan));
    }

}
