package com.maytinh.controller;

import com.maytinh.entity.VaiTro;
import com.maytinh.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vai-tro")
public class VaiTroController {

    @Autowired
    private VaiTroService vaiTroService;

    // 1. Lấy danh sách tất cả vai trò (Dùng cho giao diện dropdown chọn quyền/vai trò khi tạo tài khoản)
    // GET: http://localhost:8080/api/vai-tro
    @GetMapping
    public ResponseEntity<?> getAllVaiTro() {
        try {
            List<VaiTro> list = vaiTroService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách vai trò: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 vai trò theo ID
    // GET: http://localhost:8080/api/vai-tro/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getVaiTroById(@PathVariable Integer id) {
        try {
            VaiTro vaiTro = vaiTroService.getById(id);
            return ResponseEntity.ok(vaiTro);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage()); // Bắt lỗi .orElseThrow() từ Service
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}