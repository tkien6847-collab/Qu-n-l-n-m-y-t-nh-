package com.maytinh.service;

import com.maytinh.entity.NhanVien;
import com.maytinh.repository.NhanVienRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {


    private final NhanVienRepository nhanVienRepository;

    // Nhận Repository để làm việc với database
    public NhanVienService(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    // 1. Xem danh sách nhân viên
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    // 2. Xem nhân viên theo mã
    public Optional<NhanVien> getById(String maNV) {
        return nhanVienRepository.findById(maNV);
    }

    // 3. Thêm nhân viên
    public NhanVien create(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    // 4. Sửa thông tin nhân viên
    public NhanVien update(String maNV, NhanVien nhanVienMoi) {
        return nhanVienRepository.findById(maNV)
                .map(nhanVienCu -> {
                    // Cập nhật các thuộc tính tại đây
                    // Ví dụ: nhanVienCu.setHoTen(nhanVienMoi.getHoTen());

                    return nhanVienRepository.save(nhanVienCu);
                })
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy nhân viên có mã: " + maNV));
    }

    // 5. Xóa nhân viên theo mã
    public void delete(String maNV) {
        if (!nhanVienRepository.existsById(maNV)) {
            throw new RuntimeException(
                    "Không tìm thấy nhân viên có mã: " + maNV);
        }

        nhanVienRepository.deleteById(maNV);
    }


}
