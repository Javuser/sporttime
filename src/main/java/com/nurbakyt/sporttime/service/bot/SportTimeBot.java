package com.nurbakyt.sporttime.service.bot;

import com.nurbakyt.sporttime.config.SportTimeBotConfig;
import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.TgState;
import com.nurbakyt.sporttime.repository.MemberRepository;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import com.nurbakyt.sporttime.service.TgBotStateService;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SportTimeBot extends TelegramLongPollingBot {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private TgBotStateService tgBotStateService;

    private final SportTimeBotConfig sportTimeBotConfig;


    private Member member;


    private final static String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "You can execute commands from the main menu on the left or by typing commands:\n\n" +
            "Type /start to see welcome message\n\n" +
            "Type /register to register yourself\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again";


    public SportTimeBot(SportTimeBotConfig sportTimeBotConfig) {
        this.sportTimeBotConfig = sportTimeBotConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            TgState registrationStep = tgBotStateService.getStateForChatOrCreateNew(chatId);
            switch (registrationStep) {

                case START:
                    if(message.equals("/start")) {
                        startCommandLineReceived(chatId, update.getMessage().getChat().getFirstName());
                        tgBotStateService.setStateForChat(chatId, TgState.REGISTER);
                    }

                case HELP:
                    sendHelp(chatId, HELP_TEXT);

                case REGISTER:

                    if (message.equals("/register")) {
                        sendMessage(chatId, "Для регистрации, введите ваше имя:");
//                        registrationStep = UserRegistrationStep.ASK_NAME;
                        tgBotStateService.setStateForChat(chatId, TgState.ASK_NAME);
                    }
                    break;

                case ASK_NAME:
                    member = new Member();
                    //member.setTg_chat_id(chatId);
                    member.setName(message);
                    sendMessage(chatId, "Отлично! Теперь введите ваш возраст:");
//                    registrationStep = UserRegistrationStep.ASK_AGE;
                    tgBotStateService.setStateForChat(chatId, TgState.ASK_AGE);
                    break;

                case ASK_AGE:
                    if (message.matches("\\d+")) {
                        int age = Integer.parseInt(message);
                        member.setAge(age);
                        memberRepository.save(member);
                        sendMessage(chatId, "Спасибо! Регистрация завершена.");
//                        registrationStep = UserRegistrationStep.COMPLETE;
                        tgBotStateService.setStateForChat(chatId, TgState.COMPLETE);
                    } else {
                        sendMessage(chatId, "Пожалуйста, введите ваш возраст цифрами.");
                    }
                    break;

                case COMPLETE:
                    //sendHelp(chatId, HELP_TEXT);
//                    registrationStep = UserRegistrationStep.START;
                    tgBotStateService.setStateForChat(chatId, TgState.START);
                    sendHelp(chatId, HELP_TEXT);
                    // Optionally, handle any additional logic after registration is complete
                    break;


            }

        }

    }


    private void startCommandLineReceived ( long chatId, String name){
        String answer = EmojiParser.parseToUnicode("Сәлеметсіз бе, " + name + ", cізді көргеніме қуаныштымын" + "\uD83D\uDE0A");
        sendMessage(chatId, answer);
    }


    private void sendMessage(long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        //sendMessage.setReplyMarkup(startCommandKeyboard());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendHelp(long chatId, String helpText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(helpText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }


    private ReplyKeyboardMarkup startCommandKeyboard() {

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Тіркелу");
        row.add("Абонемент сатып алу");
        row.add("Көмек");

        rows.add(row);

        row = new KeyboardRow();
        rows.add(row);

        keyboard.setKeyboard(rows);

        return keyboard;
    }
    @Override
    public String getBotUsername() {
        return sportTimeBotConfig.getBotName();
    }
    @Override
    public String getBotToken() {
        return sportTimeBotConfig.getBotToken();
    }
}
