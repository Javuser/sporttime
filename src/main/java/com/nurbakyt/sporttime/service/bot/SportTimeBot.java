package com.nurbakyt.sporttime.service.bot;

import com.nurbakyt.sporttime.config.SportTimeBotConfig;
import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.entity.TgState;
import com.nurbakyt.sporttime.entity.UserDemo;
import com.nurbakyt.sporttime.repository.MemberRepository;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import com.nurbakyt.sporttime.repository.UserDemoRepository;
import com.nurbakyt.sporttime.service.TgBotStateService;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SportTimeBot extends TelegramLongPollingBot {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserDemoRepository userDemoRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private TgBotStateService tgBotStateService;

    private final SportTimeBotConfig sportTimeBotConfig;


    private Member member;
    private Membership membership;

    private UserDemo userDemo;


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
        List<BotCommand> listOfCommands = new ArrayList();
        listOfCommands.add(new BotCommand("/start", "Нажмите чтоб запустить бота"));
        listOfCommands.add(new BotCommand("/register", "регистрация пользователя"));
        listOfCommands.add(new BotCommand("/mydata", "информация о пользователе"));
        listOfCommands.add(new BotCommand("/help", "помощь"));
        listOfCommands.add(new BotCommand("/buy", "покупка абонемента"));

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            TgState registrationStep = tgBotStateService.getStateForChatOrCreateNew(chatId);
            switch (message) {

                case "/start":

                    registerUser(update.getMessage(), "I came to gym");
                    startCommandLineReceived(chatId, update.getMessage().getChat().getFirstName());
//                    welcomeMessage(chatId, update);
//                    tgBotStateService.setStateForChat(chatId, TgState.REGISTER);
                    break;

                case "/help":
                    sendHelp(chatId, HELP_TEXT);

                    break;
                case "/register":
                    //register(chatId);
                    //sendRegister(chatId, "Registration");
                    break;
                default:
                    sendMessage(chatId, "Sorry command was not clear");
            }
        } else if (update.hasCallbackQuery()) {

            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();


            if(callbackData.equals("YES_BUTTON")) {


                String text = "Вы нажали на 'ДA' ";
                EditMessageText message = new EditMessageText();
                message.setChatId(String.valueOf(chatId));
                message.setText(text);
                message.setMessageId((int) messageId);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }
            else if (callbackData.equals("NO_BUTTON")) {
                String text = "Вы нажали на 'НЕТ' ";
                EditMessageText message = new EditMessageText();
                message.setChatId(String.valueOf(chatId));
                message.setText(text);
                message.setMessageId((int) messageId);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }

            else if (callbackData.equals("HELP_BUTTON")) {
                sendHelp(chatId, HELP_TEXT);
                EditMessageText message = new EditMessageText();

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }

//            else if (callbackData.equals("CHECK_IN_BUTTON")) {
//                //sendRegister(chatId, update.getMessage().getText());
//
//                EditMessageText message = new EditMessageText();
//
//              //
////                userDemo.setCommand(callbackData);
////                userDemoRepository.save(userDemo);
////
//                sendMessage(chatId, "Я пришел на тренировку");
//
//
//                try {
//                    execute(message);
//                } catch (TelegramApiException e) {
//                    log.error("Error occurred: " + e.getMessage());
//                }
//            }

            else if (callbackData.equals("CHECK_IN_BUTTON")) {
                EditMessageText message = new EditMessageText();
                sendMessage(chatId, "Я пришел на тренировку");

                // Assuming userDemo is a class-level variable or otherwise properly initialized
                if (userDemo != null) {
                    if (userDemo.getCommand() != null) {
                        userDemo.setCommand("CHECK_IN_BUTTON");
                        userDemoRepository.save(userDemo);
                    } else {
                        log.error("userDemo's command is already set. You may want to handle this case.");
                    }
                } else {
                    log.error("userDemo is null. Make sure it is properly initialized.");
                }

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }




        }

    }

        private void checkIn(long chatId) {
        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(chatId));
        message.setText("Вы хотите зарегистрироваться ?");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        var yesButton = new InlineKeyboardButton();
        yesButton.setText("Да");
        yesButton.setCallbackData("YES_BUTTON");

        var noButton = new InlineKeyboardButton();
        noButton.setText("Нет");
        noButton.setCallbackData("NO_BUTTON");

        rowInline.add(yesButton);
        rowInline.add(noButton);

        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }

    }


    private void registerUser(Message msg, String command) {
        if (userDemoRepository.findById(msg.getChatId()).isEmpty()) {
          var id = msg.getChatId();
          var chat = msg.getChat();

            UserDemo userDemo = new UserDemo();
            userDemo.setId(id);
            userDemo.setFname(chat.getFirstName());
            userDemo.setLname(chat.getLastName());
            userDemo.setUname(chat.getUserName());
            userDemo.setRegisteredat(new Timestamp(System.currentTimeMillis()));
            userDemo.setCommand(command);



            userDemoRepository.save(userDemo);
        }
    }


//    public void sendRegister(long chatId, String message) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setId(String.valueOf(chatId));
//        sendMessage.setText(message);
//        // Check the current registration step
//        TgState registrationStep = tgBotStateService.getStateForChatOrCreateNew(chatId);
//
//        switch (registrationStep) {
//            case ASK_NAME:
//                // User is entering their name
//                processNameInput(message, chatId);
//                break;
//            case ASK_AGE:
//                // User is entering their age
//                processAgeInput(message, chatId);
//                break;
//            default:
//                // Invalid registration step
//                sendMessage(chatId, "Ошибка в процессе регистрации.");
//        }
//    }
//
//    private void processNameInput(String name, long chatId) {
//        // Create a new member or retrieve an existing one
//        Member member = new Member();
//
//        // Save the name and move to the next step (entering age)
//        member.setName(name);
//        tgBotStateService.setStateForChat(chatId, TgState.ASK_AGE);
//
//        // Send a request to enter the age
//        sendMessage(chatId, "Отлично, " + name + "! Теперь введите ваш возраст:");
//    }
//
//    private void processAgeInput(String ageInput, long chatId) {
//        try {
//            int age = Integer.parseInt(ageInput);
//
//            member.setAge(age);
//            memberRepository.save(member);
//
//            if (member != null) {
//
//                // Send a message about successful registration
//                sendMessage(chatId, "Спасибо за регистрацию, " + member.getName() + "!");
//            } else {
//                // Member not found, handle accordingly
//                sendMessage(chatId, "Ошибка в процессе регистрации.");
//            }
//
//            // Reset the registration state
//            tgBotStateService.setStateForChat(chatId, TgState.HELP);
//
//
//        } catch (NumberFormatException e) {
//            // If the entered age is not a number, request input again
//            sendMessage(chatId, "Пожалуйста, введите корректный возраст (целое число):");
//        }
//    }




    private void startCommandLineReceived ( long chatId, String name){
        String answer = EmojiParser.parseToUnicode("Сәлеметсіз бе, " + name + ", cізді көргеніме қуаныштымын" + "\uD83D\uDE0A");
//        sendMessage(chatId, answer);

        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(chatId));
        message.setText(answer);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        var registerButton = new InlineKeyboardButton();
        registerButton.setText("Я пришел на тренировку");
        registerButton.setCallbackData("CHECK_IN_BUTTON");

        var helpButton = new InlineKeyboardButton();
        helpButton.setText("помощь");
        helpButton.setCallbackData("HELP_BUTTON");

        rowInline.add(registerButton);
        rowInline.add(helpButton);

        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void sendCheckInNotificationToAdmin(long chatId, String userName) {
        long adminChatId = chatId;
                // укажите ID чата админа;
                 String notificationText = "Пользователь " + userName + " пришел на тренировку.";
        sendMessage(adminChatId, notificationText);
    }



    private void sendMessage(long chatId, String textToSend) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void sendHelp(long chatId, String helpText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(helpText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
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
