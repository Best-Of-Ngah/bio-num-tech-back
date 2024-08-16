package com.best.of.ngah.bionumtech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BioNumTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(BioNumTechApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println(Boolean.valueOf("true"));
        };
    }

}
