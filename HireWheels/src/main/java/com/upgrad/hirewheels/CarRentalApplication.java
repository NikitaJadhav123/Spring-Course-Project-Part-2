package com.upgrad.hirewheels;

import com.upgrad.hirewheels.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {

            SpringApplication.run(CarRentalApplication.class, args);

    }

    @Bean
    CommandLineRunner init (InitService initService){
        return args -> {
            initService.init();
        };
    }






}

