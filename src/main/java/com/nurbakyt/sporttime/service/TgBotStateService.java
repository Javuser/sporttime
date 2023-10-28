package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.TgState;

public interface TgBotStateService {

    TgState getStateForChatOrCreateNew(Long chatId);
    void setStateForChat(Long chatId, TgState tgState);
}
