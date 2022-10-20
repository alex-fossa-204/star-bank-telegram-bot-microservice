package dev.alexfossa204.starbank.telegrambot.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramMessage {

    private String messageTitle;

    private String verificationCode;

    private String phoneNumber;

    private Boolean isClient;

    private String reason;

    private String passportLogin;

    private String firstName;

    private String lastName;

}
