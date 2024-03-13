package com.nurbakyt.sporttime.service.bot.register;

import com.nurbakyt.sporttime.service.MemberService;
import com.nurbakyt.sporttime.service.bot.register.model.RegistrationHandlerResult;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class RegistrationMessage {

    private MemberService memberService;

    public RegistrationMessage(MemberService memberService) {
        this.memberService = memberService;
    }

    abstract RegistrationHandlerResult apply(Update update);
}
