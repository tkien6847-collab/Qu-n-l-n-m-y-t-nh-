package com.maytinh.controller;

import com.maytinh.entity.NhanVien;
import com.maytinh.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    // 1. Lấy danh sách tất cả nhân viên
    // GET: http://localhost:8080/api/nhan-vien
    @GetMapping
    public ResponseEntity<?> getAllNhanVien() {
        try {
            List<NhanVien> list = nhanVienService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách nhân viên: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 nhân viên theo ID
    // GET: http://localhost:8080/api/nhan-vien/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getNhanVienById(@PathVariable String id) {
        try {
            NhanVien nhanVien = nhanVienService.getById(id);
            return ResponseEntity.ok(nhanVien);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage()); // Trả về thông báo lỗi từ orElseThrow trong Service
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 3. Thêm mới nhân viên
    // POST: http://localhost:8080/api/nhan-vien
    @PostMapping
    public ResponseEntity<?> createNhanVien(@RequestBody NhanVien nhanVien) {
        try {
            NhanVien newNhanVien = nhanVienService.create(nhanVien);
            return ResponseEntity.status(HttpStatus.CREATED).body(newNhanVien);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Thêm mới nhân viên thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 4. Cập nhật thông tin nhân viên
    // PUT: http://localhost:8080/api/nhan-vien/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNhanVien(@PathVariable String id, @RequestBody NhanVien nhanVienDetails) {
        try {
            NhanVien updatedNhanVien = nhanVienService.update(id, nhanVienDetails);
            return ResponseEntity.ok(updatedNhanVien);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật nhân viên thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 5. Xóa nhân viên theo ID
    // DELETE: http://localhost:8080/api/nhan-vien/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable String id) {
        try {
            nhanVienService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa nhân viên thành công!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Xóa nhân viên thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}