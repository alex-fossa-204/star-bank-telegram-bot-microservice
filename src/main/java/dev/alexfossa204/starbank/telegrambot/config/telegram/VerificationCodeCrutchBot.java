package dev.alexfossa204.starbank.telegrambot.config.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class VerificationCodeCrutchBot extends TelegramLongPollingBot {

    private final String botUserName = "@verification-code-crutch-bot";

    private final String botToken = "5358315281:AAFrfHDCAyHgapsF2sJhpdvVy6DZ4Uw0B1w";

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {}
}
