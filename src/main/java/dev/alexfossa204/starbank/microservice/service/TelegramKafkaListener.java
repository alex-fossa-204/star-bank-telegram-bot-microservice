package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.GeneratedVerificationCodeTopicMessage;

public interface TelegramKafkaListener {

    void listenForVerificationCodeEvents(GeneratedVerificationCodeTopicMessage telegramMessage);

}
