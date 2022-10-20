package dev.alexfossa204.starbank.telegrambot.service.impl;

import dev.alexfossa204.starbank.telegrambot.service.dto.GeneratedVerificationCodeTopicMessage;
import dev.alexfossa204.starbank.telegrambot.service.dto.UserDto;
import dev.alexfossa204.starbank.telegrambot.service.dto.TelegramMessage;
import dev.alexfossa204.starbank.telegrambot.service.TelegramBotMessageService;
import dev.alexfossa204.starbank.telegrambot.service.TelegramKafkaListener;
import dev.alexfossa204.starbank.telegrambot.config.kafka.KafkaConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static dev.alexfossa204.starbank.telegrambot.config.kafka.KafkaConsumerConfig.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramKafkaListenerImpl implements TelegramKafkaListener {

    private final TelegramBotMessageService telegramBotMessageService;

    @KafkaListener(id = "telegramEventListener", topics = KafkaConstant.PUBLISH_GENERATED_VERIFICATION_CODE, groupId = CONSUMER_GROUP_ID)
    @Override
    public void listenForVerificationCodeEvents(GeneratedVerificationCodeTopicMessage brokerMessage) {
        UserDto userDto = brokerMessage.getUser();
        boolean isClient = userDto.getIsClient();
        if(isClient == true) {
            telegramBotMessageService.sendNotification(TelegramMessage.builder()
                    .messageTitle("Verification code message from A-Bank")
                    .verificationCode(brokerMessage.getVerificationCode())
                    .phoneNumber(userDto.getPhoneLogin())
                    .isClient(userDto.getIsClient())
                    .reason(brokerMessage.getReason())
                    .passportLogin(userDto.getPassportLogin())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .build());
        }
        if (isClient == false) {
            telegramBotMessageService.sendNotification(TelegramMessage.builder()
                    .messageTitle("Verification code message from A-Bank")
                    .verificationCode(brokerMessage.getVerificationCode())
                    .phoneNumber(userDto.getPhoneLogin())
                    .isClient(userDto.getIsClient())
                    .reason(brokerMessage.getReason())
                    .build());
        }
    }
}
