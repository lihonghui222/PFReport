package com.example.excel;

import com.example.excel.controller.PfQueryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableTransactionManagement
public class ExcelApplication {
    protected static final Logger logger = LoggerFactory.getLogger(ExcelApplication.class);

    @RequestMapping("hello")
    public String index(){
        return "Hello";
    }
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
        logger.info("启动成功！");
    }

}
