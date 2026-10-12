package com.maytinh.controller;

import com.maytinh.entity.SanPham;
import com.maytinh.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    // 1. Lấy danh sách tất cả sản phẩm
    // GET: http://localhost:8080/api/san-pham
    @GetMapping
    public ResponseEntity<?> getAllSanPham() {
        try {
            List<SanPham> list = sanPhamService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 sản phẩm theo ID
    // GET: http://localhost:8080/api/san-pham/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getSanPhamById(@PathVariable String id) {
        try {
            SanPham sanPham = sanPhamService.getById(id);
            return ResponseEntity.ok(sanPham);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage()); // Trả về câu thông báo lỗi từ orElseThrow trong Service
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 3. Thêm mới sản phẩm
    // POST: http://localhost:8080/api/san-pham
    @PostMapping
    public ResponseEntity<?> createSanPham(@RequestBody SanPham sanPham) {
        try {
            SanPham newSanPham = sanPhamService.create(sanPham);
            return ResponseEntity.status(HttpStatus.CREATED).body(newSanPham);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Thêm mới sản phẩm thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 4. Cập nhật thông tin sản phẩm
    // PUT: http://localhost:8080/api/san-pham/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSanPham(@PathVariable String id, @RequestBody SanPham sanPhamDetails) {
        try {
            SanPham updatedSanPham = sanPhamService.update(id, sanPhamDetails);
            return ResponseEntity.ok(updatedSanPham);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật sản phẩm thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 5. Xóa sản phẩm theo ID
    // DELETE: http://localhost:8080/api/san-pham/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSanPham(@PathVariable String id) {
        try {
            sanPhamService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa sản phẩm thành công!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Xóa sản phẩm thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}