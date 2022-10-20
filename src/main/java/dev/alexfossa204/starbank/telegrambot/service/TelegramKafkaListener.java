package dev.alexfossa204.starbank.telegrambot.service;

import dev.alexfossa204.starbank.telegrambot.service.dto.GeneratedVerificationCodeTopicMessage;

public interface TelegramKafkaListener {

    void listenForVerificationCodeEvents(GeneratedVerificationCodeTopicMessage telegramMessage);

}
