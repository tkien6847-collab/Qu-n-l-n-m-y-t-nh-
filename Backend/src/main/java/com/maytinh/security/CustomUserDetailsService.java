package com.maytinh.security;
//Mục đích của CustomUserDetailsService là lấy thông tin tài khoản từ Database
// để Spring Security biết người dùng là ai và có quyền gì khi đăng nhập.
import com.maytinh.entity.TaiKhoan;
import com.maytinh.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm thông tin tài khoản trong Database
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));


        // Kiểm tra tài khoản đã có vai trò chưa
        if (taiKhoan.getVaitro() == null) {
            throw new RuntimeException("Tài khoản chưa được gán vai trò");
        }

        // Lấy tên vai trò
        String roleName = taiKhoan.getVaitro().getTenVaiTro();
        return new User(
                taiKhoan.getTenDangNhap(),
                taiKhoan.getMatKhau(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName))
        );
    }
}