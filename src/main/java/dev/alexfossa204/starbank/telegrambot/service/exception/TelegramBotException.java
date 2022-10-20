package dev.alexfossa204.starbank.telegrambot.service.exception;

public class TelegramBotException extends ServiceException {

    public TelegramBotException(String message) {
        super(message);
    }

    public TelegramBotException(Throwable cause) {
        super(cause);
    }
}
