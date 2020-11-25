package com.example.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExcelApplication {
    @RequestMapping("hello")
    public String index(){
        return "Hello";
    }
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
        System.out.println("启动成功！");
    }

}
