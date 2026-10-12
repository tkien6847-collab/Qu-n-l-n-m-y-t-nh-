package com.maytinh.service;

import com.maytinh.entity.KhachHang;
import com.maytinh.repository.KhachHangRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//quản lý giao dịch với database
import java.util.List;

@Service
@Transactional(readOnly = true)// pt trong class dùng để đọc , có @tran thì đc change
public class KhachHangService {


    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository)
    {
        this.khachHangRepository = khachHangRepository;
    }


    public List<KhachHang> getAll()
    {
        return khachHangRepository.findAll();
    }//lấy theo tên entity

    // Tìm khách hàng theo mã
    public KhachHang getById(String maKH) {
        return khachHangRepository.findById(maKH)
                .orElseThrow(() -> new RuntimeException("No see this Id: " +maKH));
    }

    // Thêm khách hàng
    @Transactional
    public KhachHang create(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    // Cập nhật khách hàng
    @Transactional
    public KhachHang update(String maKH, KhachHang khachHangMoi) {
        KhachHang khachHangCu = khachHangRepository.findById(maKH)
                .orElseThrow(() -> new RuntimeException("Không thấy ID: " + maKH));

        khachHangCu.setTenKhachHang(khachHangMoi.getTenKhachHang());
        khachHangCu.setDiaChi(khachHangMoi.getDiaChi());
        khachHangCu.setsDT(khachHangMoi.getsDT());
        khachHangCu.setEmail(khachHangMoi.getEmail());


        return khachHangRepository.save(khachHangCu);
    }

    // Xóa khách hàng
    @Transactional
    public void delete(String maKH) {

        KhachHang khachHang = khachHangRepository.findById(maKH)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        khachHangRepository.delete(khachHang);


    }


}
