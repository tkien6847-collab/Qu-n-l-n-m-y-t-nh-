package com.maytinh.service;

import com.maytinh.entity.SanPham;
import com.maytinh.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;

    // Khởi tạo Repository
    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    // 1. Lấy danh sách sản phẩm
    public List<SanPham> getAll()
    {
        return sanPhamRepository.findAll();
    }

    // 2. Tìm sản phẩm theo mã
    public Optional<SanPham> getById(String maSP)
    {
        return sanPhamRepository.findById(maSP);
    }

    // 3. Thêm sản phẩm
    public SanPham create(SanPham sanPham)
    {
        return sanPhamRepository.save(sanPham);
    }

    // 4. Cập nhật sản phẩm
    public SanPham update(String maSP, SanPham sanPhamMoi) {
        return sanPhamRepository.findById(maSP)
                .map(sanPhamCu -> {
                    // Cập nhật các thuộc tính sản phẩm tại đây
                    // Ví dụ: sanPhamCu.setTenSP(sanPhamMoi.getTenSP());

                    return sanPhamRepository.save(sanPhamCu);
                })
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy sản phẩm có mã: " + maSP));
    }

    // 5. Xóa sản phẩm
    public void delete(String maSP) {
        if (!sanPhamRepository.existsById(maSP)) {
            throw new RuntimeException(
                    "Không tìm thấy sản phẩm có mã: " + maSP);
        }

        sanPhamRepository.deleteById(maSP);
    }

}
