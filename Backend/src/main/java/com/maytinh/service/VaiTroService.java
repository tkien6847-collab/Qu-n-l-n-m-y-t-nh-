package com.maytinh.service;

import com.maytinh.entity.VaiTro;
import com.maytinh.repository.VaiTroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {


    private final VaiTroRepository repository;

    public VaiTroService(VaiTroRepository repository) {
        this.repository = repository;
    }

    // Lấy danh sách vai trò
    public List<VaiTro> getAll() {
        return repository.findAll();
    }

    // Tìm vai trò theo mã
    public VaiTro getById(Integer maVaiTro) {
        return repository.findById(maVaiTro)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy vai trò: " + maVaiTro));
    }

    // Thêm vai trò
    public VaiTro create(VaiTro vaiTro) {
        return repository.save(vaiTro);
    }

    // Cập nhật vai trò
    public VaiTro update(Integer maVaiTro, VaiTro vaiTroMoi) {
        VaiTro vaiTroCu = getById(maVaiTro);

        // Cập nhật các thuộc tính của vai trò tại đây

        return repository.save(vaiTroCu);
    }

    // Xóa vai trò
    public void delete(Integer maVaiTro) {
        repository.delete(getById(maVaiTro));
    }


}
