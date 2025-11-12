package org.example.farmingsellbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("org.example.farmingsellbackend.mapper")
@SpringBootApplication
public class FarmingsellBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(FarmingsellBackendApplication.class, args);
    }

}
