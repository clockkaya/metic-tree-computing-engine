package com.sama.officer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.sama", "com.core4ct"})
@MapperScan({"com.sama.officer.mapper"})
public class SamaOfficerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamaOfficerApplication.class, args);
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
