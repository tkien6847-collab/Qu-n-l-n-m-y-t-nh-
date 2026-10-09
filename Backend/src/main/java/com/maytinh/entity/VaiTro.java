package com.maytinh.entity;

import jakarta.persistence.*;


@Entity 
@Table (name = "VaiTro")
public class VaiTro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "MaVaiTro")
        private Integer maVaiTro;
        @Column (name = "TenVaiTro")
        private String tenVaiTro;
        @Column (name = "Code")
        String code;

        public VaiTro() {
        }
        

        public VaiTro(int maVaiTro, String tenVaiTro, String code) {
            this.maVaiTro = maVaiTro;
            this.tenVaiTro = tenVaiTro;
            this.code = code;
        }


        public int getMaVaiTro() {
            return maVaiTro;
        }

        public void setMaVaiTro(int maVaiTro) {
            this.maVaiTro = maVaiTro;
        }

        public String getTenVaiTro() {
            return tenVaiTro;
        }

        public void setTenVaiTro(String tenVaiTro) {
            this.tenVaiTro = tenVaiTro;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
         
    
}
