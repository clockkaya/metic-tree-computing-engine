package com.sama.maint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.sama", "com.core4ct"})
@MapperScan("com.sama.maint.mapper")
public class SamaMaintApplication {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(SamaMaintApplication.class);
            app.setRegisterShutdownHook(true);
            app.run(args);
            System.out.println(
                    " _____                      ___  _____  _____ \n" +
                            "/  __ \\                    /   |/  __ \\|_   _|\n" +
                            "| /  \\/  ___   _ __  ___  / /| || /  \\/  | |  \n" +
                            "| |     / _ \\ | '__|/ _ \\/ /_| || |      | |  \n" +
                            "| \\__/\\| (_) || |  |  __/\\___  || \\__/\\  | |  \n" +
                            " \\____/ \\___/ |_|   \\___|    |_/ \\____/  \\_/ \n" +
                            "----------------Run Success-------------------"
            );
        } catch (Exception e) {
            System.out.println("应用程序启动失败" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
