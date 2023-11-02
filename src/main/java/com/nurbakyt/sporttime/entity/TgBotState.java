package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bot_state")
public class TgBotState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private TgState state;

    public TgBotState(Long id, Long chatId, TgState state) {
        this.id = id;
        this.chatId = chatId;
        this.state = state;
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

    public TgState getState() {
        return state;
    }

    public void setState(TgState state) {
        this.state = state;
    }
}
