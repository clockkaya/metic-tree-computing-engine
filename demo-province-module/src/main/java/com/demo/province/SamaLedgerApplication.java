package com.sama.ledger;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.sama.ledger.mapper")
@SpringBootApplication(scanBasePackages = {"com.core4ct", "com.sama","com.sama.ledger.dubboImpl"})
public class SamaLedgerApplication {

    public static void main(String[] args) {
        // fastjson2 开启全局智能匹配，解决key带下划线无法识别
        JSON.config(JSONReader.Feature.SupportSmartMatch);
        SpringApplication.run(SamaLedgerApplication.class, args);
        System.out.println(
                " _____                      ___  _____  _____ \n" +
                        "/  __ \\                    /   |/  __ \\|_   _|\n" +
                        "| /  \\/  ___   _ __  ___  / /| || /  \\/  | |  \n" +
                        "| |     / _ \\ | '__|/ _ \\/ /_| || |      | |  \n" +
                        "| \\__/\\| (_) || |  |  __/\\___  || \\__/\\  | |  \n" +
                        " \\____/ \\___/ |_|   \\___|    |_/ \\____/  \\_/ \n" +
                        "----------------Run Success-------------------"
        );
    }

}
