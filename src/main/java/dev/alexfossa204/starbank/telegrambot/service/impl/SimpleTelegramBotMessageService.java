package dev.alexfossa204.starbank.telegrambot.service.impl;

import dev.alexfossa204.starbank.telegrambot.config.telegram.VerificationCodeCrutchBot;
import dev.alexfossa204.starbank.telegrambot.service.dto.TelegramMessage;
import dev.alexfossa204.starbank.telegrambot.service.TelegramBotMessageService;
import dev.alexfossa204.starbank.telegrambot.service.exception.TelegramBotException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class SimpleTelegramBotMessageService implements TelegramBotMessageService {

    private static final String consumerChatId = "-1001757185984";

    private final VerificationCodeCrutchBot verificationCodeCrutchBot;

    private String nonClientRegex = "Title: %s \n" +
            "Phone number: %s \n" +
            "Verification code: %s \n" +
            "Reason: %s \n" +
            "isClient: %s \n" +
            "Date: %s";
    private String clientRegex = "Title: %s \n" +
            "Phone number: %s \n" +
            "Passport number: %s \n" +
            "Client: %s %s \n" +
            "Verification code: %s \n" +
            "Reason: %s \n" +
            "isClient: %s \n" +
            "Date: %s";

    @Async
    @Override
    public void sendNotification(TelegramMessage telegramMessage) {
        String formattedMessage = "";
        if(telegramMessage.getIsClient() == false) {
            formattedMessage = String.format(nonClientRegex,
                    telegramMessage.getMessageTitle(),
                    telegramMessage.getPhoneNumber(),
                    telegramMessage.getVerificationCode(),
                    telegramMessage.getReason(),
                    telegramMessage.getIsClient(),
                    new Date());
        }
        if(telegramMessage.getIsClient() == true) {
            formattedMessage = String.format(clientRegex,
                    telegramMessage.getMessageTitle(),
                    telegramMessage.getPhoneNumber(),
                    telegramMessage.getPassportLogin(),
                    telegramMessage.getFirstName(),
                    telegramMessage.getLastName(),
                    telegramMessage.getVerificationCode(),
                    telegramMessage.getReason(),
                    telegramMessage.getIsClient(),
                    new Date());
        }
        try {
            verificationCodeCrutchBot.execute(SendMessage.builder()
                    .chatId(consumerChatId)
                    .text(formattedMessage)
                    .build());
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e);
        }
    }
}
