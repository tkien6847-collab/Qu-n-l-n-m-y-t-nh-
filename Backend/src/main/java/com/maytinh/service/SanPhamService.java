package com.maytinh.service;

import com.maytinh.entity.SanPham;
import com.maytinh.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;

    // Khởi tạo Repository
    public SanPhamService(SanPhamRepository sanPhamRepository)
    {
        this.sanPhamRepository = sanPhamRepository;
    }

    // 1. Lấy danh sách sản phẩm
    public List<SanPham> getAll()
    {
        return sanPhamRepository.findAll();
    }

    // 2. Tìm sản phẩm theo mã
    public SanPham getById(String maSP)
    {
        return sanPhamRepository.findById(maSP)
        .orElseThrow(()-> new RuntimeException("không thấy sản phẩm mã "+maSP));
    }

    // 3. Thêm sản phẩm
    public SanPham create(SanPham sanPham)
    {
        return sanPhamRepository.save(sanPham);
    }

    // 4. Cập nhật sản phẩm
    public SanPham update(String maSP, SanPham sanPhamMoi) {
        SanPham sanPhamCu= sanPhamRepository.findById(maSP)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm có mã: " + maSP));

                   sanPhamCu.setDonGia(sanPhamMoi.getDonGia());
                   sanPhamCu.setMoTa(sanPhamMoi.getMoTa());
                   sanPhamCu.setSoLuongTon(sanPhamMoi.getSoLuongTon());
                   sanPhamCu.setTenSP(sanPhamMoi.getTenSP());

                    return sanPhamRepository.save(sanPhamCu);




    }

    // 5. Xóa sản phẩm
    public void delete(String maSP) {
       SanPham sanPham = sanPhamRepository.findById(maSP)
               .orElseThrow(() -> new RuntimeException("không thấy Id "+maSP));
       sanPhamRepository.delete(sanPham);
    }

}
