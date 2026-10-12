package com.maytinh.controller;

import com.maytinh.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController// cho spring biết đây là nơi xử lý API và trả dữ liệu về người dùng
@RequestMapping("/api/auth") //đặt đường dẫn chung cho API trong lớp
public class AuthController {

    @Autowired // nhờ spring secu kiểm tra thông tin đăng nhập
    private AuthenticationManager authenticationManager;
              //interface c sẵn trong spring

    @Autowired//dùng để tạo Jwt sau khi đăng nhập thành công
    //là tên đăng nhập và thời hạn
    private JwtTokenProvider tokenProvider;

    // API Đăng nhập: POST http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest)
    //cho phép kiểu dữ liệu trả về cùng mã http như 200,401,v.v dấu ? nghĩa là kiểu dữ liêệu cụ thể có thể khac nhau
    {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            // 1. Xác thực tên đăng nhập và mật khẩu
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. Lưu thông tin xác thực vào hệ thống
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3. Tạo JWT Token
            String jwt = tokenProvider.generateToken(username);

            // 4. Trả kết quả về cho Client
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", jwt);
            response.put("tokenType", "Bearer");

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}