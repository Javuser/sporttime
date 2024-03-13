package com.nurbakyt.sporttime.service.bot.register.model;

import com.nurbakyt.sporttime.entity.Member;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RegistrationHandlerResult {
    private final SendMessage message;
    private final Member member;

    public RegistrationHandlerResult(SendMessage message, Member member) {
        this.message = message;
        this.member = member;
    }

    public SendMessage getMessage() {
        return message;
    }

    public Member getMember() {
        return member;
    }
}
