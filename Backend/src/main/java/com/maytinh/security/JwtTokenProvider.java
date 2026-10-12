package com.maytinh.security;
//jwt 1 chẩn để truyền thông tin giữa các người dùng dưới dạng token
//dùng để xác thực ngơ dùng sau khi đăng nhập
import io.jsonwebtoken.*; //các class của thư viện JWT,tạo đọc JWT
import io.jsonwebtoken.security.Keys;//tạo khóa k JWT
import org.springframework.stereotype.Component;//đánh dấu class để spring quản lý

import java.security.Key;//kiểu dữ liệu ịa diện cho khóa mật mã
import java.util.Date;//đại diện cho thời điểm tạo và hết hạn token

@Component
public class JwtTokenProvider {
    // Chuỗi bí mật dùng để ký token (nên dài tối thiểu 32 ký tự)
    private final String JWT_SECRET = "ChuoiBiMatCucKyDaiVaAnToanChoJWTToken1234567890";
    private final long JWT_EXPIRATION = 86400000L; // Thời gian hết hạn: 1 ngày (ms),
    //sau 1 ngày k thao tác đc cần đăng nhập lại

    //Tạo khóa bí mật để ký token, giúp server kiểm tra token có bị giả mạo hoặc chỉnh sửa hay không.
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }//chuyển chuỗi bí mật thành mảng byte
    //sau đó tạo đối tượng khóa từ mảng byte
    // trả khóa về để method khác dùng khi tạo , xác minh jwt

    public String generateToken(String username) {
        //tạo 1 jwt cho người dùng dựa trên username
        Date now = new Date();
        //lấy thoi diem hiện tại để ghi nhận lúc tạo

        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // thời điểm hết hạn

        return Jwts.builder()// bắt đầu khởi tạo Jwt
                .setSubject(username)//lưu tên đăng nhập
                .setIssuedAt(now)// lưu thời điểm tạo token
                .setExpiration(expiryDate)// tgian hết hạn
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                //ký bằng khóa bí mật dùng HS256
                .compact();//chuyển token thành chuỗi string , đây là hàm có sẵn của Jwt
        //các method có sẵn của thư viện jwt
    }
        // Lấy tenDangNhap từ Token
        public String getUsernameFromJWT(String token) {
            Claims claims = Jwts.parserBuilder()//tạo bộ phân tích jwt
                    .setSigningKey(getSigningKey())//cung cấp khóa bí mật check chữ kí
                    .build()//hoàn  tất cấu hình bộ phân tích
                    .parseClaimsJws(token)//phân tích token và kểm tra chữ kí tạm thời
                    .getBody();//lấy phần nội dung claims của token
            return claims.getSubject();// lấy tên đng nhập đã luu trong token
        }
    // Kiểm tra Token hợp lệ
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    }

