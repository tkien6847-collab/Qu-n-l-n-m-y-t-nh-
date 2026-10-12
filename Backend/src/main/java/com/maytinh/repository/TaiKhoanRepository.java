package com.maytinh.repository;

import com.maytinh.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer>{
    Optional<TaiKhoan> findByTenDangNhap(String username);
}