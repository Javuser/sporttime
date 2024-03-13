package com.nurbakyt.sporttime.service.bot.register;

import com.nurbakyt.sporttime.service.MemberService;
import com.nurbakyt.sporttime.service.bot.register.model.RegistrationHandlerResult;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NameRegistrationHandler extends RegistrationMessage{

    private final static String HELLO_MESSAGE = "Здравствуй, дорогой пользователь!" +
            " Я смотрю ты у нас новый посетитель. Давай пройдем мини регистрацию. \n\n Напиши свое имя:";


    public NameRegistrationHandler(MemberService memberService) {
        super(memberService);
    }

    @Override
    RegistrationHandlerResult apply(Update update) {

        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(TelegramUtils.);
        return null;
    }


}
