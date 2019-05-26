package com.example.odata.own;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class OwnodataApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnodataApplication.class, args);
    }

}
