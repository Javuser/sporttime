package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.TgBotState;
import com.nurbakyt.sporttime.entity.TgState;
import com.nurbakyt.sporttime.exception.EntityNotFoundException;
import com.nurbakyt.sporttime.repository.TgBotStateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TgBotStateServiceImpl implements TgBotStateService{

    private final TgBotStateRepository tgBotStateRepository;

    public TgBotStateServiceImpl(TgBotStateRepository tgBotStateRepository) {
        this.tgBotStateRepository = tgBotStateRepository;
    }

    @Override
    @Transactional
    public TgState getStateForChatOrCreateNew(Long chatId) {
        return tgBotStateRepository.findByChatId(chatId)
            .orElseGet(() -> {
                TgBotState tgBotState = new TgBotState(null, chatId, TgState.START);
                return tgBotStateRepository.save(tgBotState);

            }).getState();
    }

    @Override
    @Transactional
    public void setStateForChat(Long chatId, TgState tgState) {
        TgBotState tgBotState = tgBotStateRepository.findByChatId(chatId)
                .orElseThrow(EntityNotFoundException::new);

        tgBotState.setState(tgState);
        tgBotStateRepository.save(tgBotState);
    }
}
