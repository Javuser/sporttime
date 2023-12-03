package com.nurbakyt.sporttime.service.bot;

import com.nurbakyt.sporttime.config.SportTimeBotConfig;
import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
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
    private Membership membership;


    private final static String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "You can execute commands from the main menu on the left or by typing commands:\n\n" +
            "Type /start to see welcome message\n\n" +
            "Type /register to register yourself\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again\n\n" +
            "Type /buy to buy membership\n\n" +
            "/checkIn";


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
                    break;

                case HELP:
                    if(message.equals("/help")) {
                        sendHelp(chatId, HELP_TEXT);
                    }

                    break;

                case REGISTER:

                    if (message.equals("/register")) {
                        sendMessage(chatId, "Для регистрации, введите ваше имя:");
                        tgBotStateService.setStateForChat(chatId, TgState.ASK_NAME);
                    }
                    break;

                case ASK_NAME:
                    member = new Member();
                    member.setName(message);
                    sendMessage(chatId, "Отлично! Теперь введите ваш возраст:");
                    tgBotStateService.setStateForChat(chatId, TgState.ASK_AGE);
                    break;

                case ASK_AGE:
                    if (message.matches("\\d+")) {
                        int age = Integer.parseInt(message);
                        member.setAge(age);
                        memberRepository.save(member);
                        sendMessage(chatId, "Спасибо! Регистрация завершена.");
                        tgBotStateService.setStateForChat(chatId, TgState.BUY);
                    } else {
                        sendMessage(chatId, "Пожалуйста, введите ваш возраст цифрами.");
                    }
                    break;

                case BUY:
                    if (message.equals("/buy")) {
                        // Create a custom keyboard

                        membership = new Membership();
//                         Create the message with the custom keyboard
                        //SendMessage sendMessage = new SendMessage();
                        //sendMessage.setChatId(chatId);
                        sendMessage(chatId,"Какой вид абонемента хотите купить?");
                        //sendMessage.setReplyMarkup(keyboardMarkup);

                        String type = message;

                        membership.setType(type);
                        membershipRepository.save(membership);
                        tgBotStateService.setStateForChat(chatId, TgState.COMPLETE);
                        // Send the message
                    }
                    break;


                case COMPLETE:
                    tgBotStateService.setStateForChat(chatId, TgState.START);
                    sendMessage(chatId, HELP_TEXT);
                    break;

                case MY_DATA:
                    if(message.equals("/myData")) {
                        sendMessage(chatId, member.getName() + " " + member.getAge() + "\n" + membership.getType());

                    }
                    break;
                case CHECK_IN:
                    if (message.equals("/checkIn")) {
                        // Отправить уведомление админу о приходе пользователя
                        sendCheckInNotificationToAdmin(chatId, member.getName());
                        sendMessage(chatId, "Вы успешно отметились о приходе на тренировку!");
                    }
                    break;
            }

        }

    }


    private void startCommandLineReceived ( long chatId, String name){
        String answer = EmojiParser.parseToUnicode("Сәлеметсіз бе, " + name + ", cізді көргеніме қуаныштымын" + "\uD83D\uDE0A");
        sendMessage(chatId, answer);
    }

    private void sendCheckInNotificationToAdmin(long chatId, String userName) {
        long adminChatId = chatId;
                // укажите ID чата админа;
                 String notificationText = "Пользователь " + userName + " пришел на тренировку.";
        sendMessage(adminChatId, notificationText);
    }



    private void sendMessage(long chatId, String textToSend) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        // Create a list to store keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Create the first row of buttons
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Дневной абонемент"));

        // Create the second row of buttons
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Безлимитный абонемент"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Я пришел на тренировку"));

        // Add the rows to the keyboard
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);


        // Set the keyboard to the SendMessage object
        keyboardMarkup.setKeyboard(keyboard);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        sendMessage.setReplyMarkup(keyboardMarkup);

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
