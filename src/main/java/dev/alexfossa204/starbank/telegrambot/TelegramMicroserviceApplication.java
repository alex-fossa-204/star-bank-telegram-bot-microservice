package dev.alexfossa204.starbank.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class TelegramMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramMicroserviceApplication.class, args);
    }

}
