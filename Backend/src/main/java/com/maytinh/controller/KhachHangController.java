package com.maytinh.controller;

import com.maytinh.entity.KhachHang;
import com.maytinh.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    // 1. Lấy danh sách tất cả khách hàng
    // GET: http://localhost:8080/api/khach-hang
    @GetMapping
    public ResponseEntity<?> getAllKhachHang() {
        try {
            List<KhachHang> list = khachHangService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 khách hàng theo ID (Bắt ngoại lệ từ .orElseThrow)
    // GET: http://localhost:8080/api/khach-hang/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getKhachHangById(@PathVariable String id) {
        try {
            KhachHang khachHang = khachHangService.getById(id);
            return ResponseEntity.ok(khachHang);
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

    // 3. Thêm mới khách hàng
    // POST: http://localhost:8080/api/khach-hang
    @PostMapping
    public ResponseEntity<?> createKhachHang(@RequestBody KhachHang khachHang) {
        try {
            KhachHang newKhachHang = khachHangService.create(khachHang);// đặt theo trong khachhangservice
            return ResponseEntity.status(HttpStatus.CREATED).body(newKhachHang);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Thêm mới khách hàng thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 4. Cập nhật thông tin khách hàng (Bắt ngoại lệ từ .orElseThrow)
    // PUT: http://localhost:8080/api/khach-hang/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKhachHang(@PathVariable String id, @RequestBody KhachHang khachHangDetails) {
        try {
            KhachHang updatedKhachHang = khachHangService.update(id, khachHangDetails);
            return ResponseEntity.ok(updatedKhachHang);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage()); // Trả về câu thông báo lỗi khi không tìm thấy ID
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 5. Xóa khách hàng theo ID (Bắt ngoại lệ từ .orElseThrow)
    // DELETE: http://localhost:8080/api/khach-hang/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKhachHang(@PathVariable String id) {
        try {
            khachHangService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa khách hàng thành công!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Xóa thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}