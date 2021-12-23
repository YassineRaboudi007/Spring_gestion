package com.example.ecommerce;

import com.example.ecommerce.utils.FileUploadUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.example.ecommerce","com.example.ecommerce.utils"})
public class EcommerceApplication {

    public static void main(String[] args) {
        new File(FileUploadUtils.uploadDir).mkdir();
        SpringApplication.run(EcommerceApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
