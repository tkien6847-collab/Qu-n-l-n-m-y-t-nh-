package com.maytinh.controller;

import com.maytinh.entity.DonHang;
import com.maytinh.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/don-hang")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    // 1. Lấy danh sách tất cả đơn hàng
    // GET: http://localhost:8080/api/don-hang
    @GetMapping
    public ResponseEntity<?> getAllDonHang() {
        try {
            List<DonHang> list = donHangService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy thông tin 1 đơn hàng theo ID
    // GET: http://localhost:8080/api/don-hang/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getDonHangById(@PathVariable String id) {
        try {
            DonHang donHang = donHangService.getById(id);
            return ResponseEntity.ok(donHang);
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

    // 3. Tạo mới đơn hàng
    // POST: http://localhost:8080/api/don-hang
    @PostMapping
    public ResponseEntity<?> createDonHang(@RequestBody DonHang donHang) {
        try {
            DonHang newDonHang = donHangService.create(donHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDonHang);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tạo đơn hàng thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 4. Cập nhật thông tin / trạng thái đơn hàng
    // PUT: http://localhost:8080/api/don-hang/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDonHang(@PathVariable String id, @RequestBody DonHang donHangDetails) {
        try {
            DonHang updatedDonHang = donHangService.update(id, donHangDetails);
            return ResponseEntity.ok(updatedDonHang);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Cập nhật đơn hàng thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // 5. Xóa / Hủy đơn hàng theo ID
    // DELETE: http://localhost:8080/api/don-hang/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonHang(@PathVariable String id) {
        try {
            donHangService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa đơn hàng thành công!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Xóa đơn hàng thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}