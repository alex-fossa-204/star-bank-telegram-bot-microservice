package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.TelegramMessage;

public interface TelegramBotMessageService {

    void sendNotification(TelegramMessage telegramMessage);

}
