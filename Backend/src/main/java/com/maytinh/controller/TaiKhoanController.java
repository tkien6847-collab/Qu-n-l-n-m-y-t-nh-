package com.maytinh.controller;

import com.maytinh.entity.TaiKhoan;
import com.maytinh.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tai-khoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    // 1. Lấy danh sách tất cả tài khoản
    // GET: http://localhost:8080/api/tai-khoan
    @GetMapping
    public ResponseEntity<?> getAllTaiKhoan() {
        try {
            List<TaiKhoan> list = taiKhoanService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 tài khoản theo ID
    // GET: http://localhost:8080/api/tai-khoan/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaiKhoanById(@PathVariable Integer id) {
        try {
            TaiKhoan taiKhoan = taiKhoanService.getById(id);
            return ResponseEntity.ok(taiKhoan);
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

    // 3. Thêm mới / Đăng ký tài khoản
    // POST: http://localhost:8080/api/tai-khoan
    @PostMapping
    public ResponseEntity<?> createTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        try {
            TaiKhoan newTaiKhoan = taiKhoanService.create(taiKhoan);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTaiKhoan);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tạo tài khoản thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 4. Cập nhật thông tin tài khoản (mật khẩu, trạng thái...)
    // PUT: http://localhost:8080/api/tai-khoan/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaiKhoan(@PathVariable Integer id, @RequestBody TaiKhoan taiKhoanDetails) {
        try {
            TaiKhoan updatedTaiKhoan = taiKhoanService.update(id, taiKhoanDetails);
            return ResponseEntity.ok(updatedTaiKhoan);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật tài khoản thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 5. Xóa tài khoản theo ID
    // DELETE: http://localhost:8080/api/tai-khoan/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaiKhoan(@PathVariable Integer id) {
        try {
            taiKhoanService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa tài khoản thành công!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Xóa tài khoản thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}