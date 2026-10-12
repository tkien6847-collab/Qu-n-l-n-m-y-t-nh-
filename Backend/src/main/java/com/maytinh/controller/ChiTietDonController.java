package com.maytinh.controller;

import com.maytinh.entity.ChiTietDon;
import com.maytinh.entity.ChiTietDonId;
import com.maytinh.service.ChiTietDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chi-tiet-don")
public class ChiTietDonController {

    @Autowired
    private ChiTietDonService chiTietDonService;

    // 1. Lấy danh sách tất cả chi tiết đơn
    // GET: http://localhost:8080/api/chi-tiet-don
    @GetMapping
    public ResponseEntity<?> getAllChiTietDon() {
        try {
            List<ChiTietDon> list = chiTietDonService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi lấy danh sách chi tiết đơn: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // 2. Lấy 1 chi tiết đơn theo ID
    // GET: http://localhost:8080/api/chi-tiet-don/{id}
// 2. Lấy 1 chi tiết đơn theo ID phức hợp (Đường dẫn: /api/chi-tiet-don/don-hang/1/san-pham/2)
    @GetMapping("/don-hang/{donHangId}/san-pham/{sanPhamId}")
    public ResponseEntity<?> getChiTietDonById(@PathVariable String donHangId, @PathVariable String sanPhamId) {
        try {
            // Tạo đối tượng khóa chính phức hợp từ 2 ID
            ChiTietDonId id = new ChiTietDonId(donHangId, sanPhamId);

            ChiTietDon chiTietDon = chiTietDonService.getById(id);
            return ResponseEntity.ok(chiTietDon);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }






}