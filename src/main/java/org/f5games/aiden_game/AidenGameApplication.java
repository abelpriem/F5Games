package org.f5games.aiden_game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AidenGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(AidenGameApplication.class, args);
    }

    @Bean
    CommandLineRunner startGame(GameMenu gameMenu) {
        return args -> gameMenu.start();
    }
}
