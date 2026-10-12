package com.maytinh.service;

import com.maytinh.entity.NhanVien;
import com.maytinh.repository.NhanVienRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;//tạo danh sách nhiều pần tử
import java.util.Optional;//biểu diễn giá trị tồn tại hay ko tồn tại

@Service
//khai báo lớp xử lý nghiệp vụ
@Transactional(readOnly = true)
public class NhanVienService {


    private final NhanVienRepository nhanVienRepository;
    //khai báo 1 biến để nhanvien service dùng nhanvienrepo làm việc cùng database

    // Nhận Repository để làm việc với database

    public NhanVienService(NhanVienRepository nhanVienRepository)
    {
        this.nhanVienRepository = nhanVienRepository;
    }//ấn gener contructor là tạo đc auto

    // 1. Xem danh sách all nhân viên
    public List<NhanVien> getAll()
    {
        return nhanVienRepository.findAll();
    }

    // 2. Xem nhân viên theo mã
    public NhanVien getById(String maNV)
    {
        return nhanVienRepository.findById(maNV)
             .orElseThrow(()->new RuntimeException("Không tìm thấy nhân viên "));
    }

    // 3. Thêm nhân viên, kiểu dữ liệu dunng tn như entity
    @Transactional
    public NhanVien create(NhanVien nhanVien)
    {
        return nhanVienRepository.save(nhanVien);
    }

    // 4. Sửa thông tin nhân viên
    public NhanVien update(String maNV, NhanVien nhanVienMoi) {
        return nhanVienRepository.findById(maNV)
                .map(nhanVienCu -> {
                    nhanVienCu.setEmail(nhanVienMoi.getEmail());
                    nhanVienCu.setsDT(nhanVienMoi.getsDT());
                    nhanVienCu.setTenNV(nhanVienMoi.getTenNV());

                    return nhanVienRepository.save(nhanVienCu);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên có mã: " + maNV));
    }

    // 5. Xóa nhân viên theo mã
    public void delete(String maNV) {

        NhanVien nhanVien = nhanVienRepository.findById(maNV)
                .orElseThrow(() -> new RuntimeException("không có mã nhân viên "+maNV));
        nhanVienRepository.delete(nhanVien);
    }


}
