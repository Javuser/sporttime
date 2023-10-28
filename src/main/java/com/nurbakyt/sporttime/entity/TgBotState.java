package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tg_bot_state")
public class TgBotState {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "tg_chat_id")
    private Long chatId;
    @Column(name = "chat_state")
    private TgState tgState;

    public TgBotState(Long id, Long chatId, TgState tgState) {
        this.id = id;
        this.chatId = chatId;
        this.tgState = tgState;
    }

    public TgBotState() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public TgState getTgState() {
        return tgState;
    }

    public void setTgState(TgState tgState) {
        this.tgState = tgState;
    }
}
