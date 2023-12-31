package org.example;

import org.example.services.SeedService;
import org.example.storage.StorageProperties;
import org.example.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService, SeedService seedService) {
        return (args)-> {
            try {
                storageService.init();
                seedService.seedRoleData();
                seedService.seedUserData();
            }catch(Exception ex) {
                System.out.println("--------Хюстон у нас проблеми--------"+ex.getMessage());
            }
        };
    }
}