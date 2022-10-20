package dev.alexfossa204.starbank.telegrambot.service;

import dev.alexfossa204.starbank.telegrambot.service.dto.TelegramMessage;

public interface TelegramBotMessageService {

    void sendNotification(TelegramMessage telegramMessage);

}
