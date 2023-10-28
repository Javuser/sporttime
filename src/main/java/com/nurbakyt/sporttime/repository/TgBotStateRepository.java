package com.nurbakyt.sporttime.repository;

import com.nurbakyt.sporttime.entity.TgBotState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TgBotStateRepository extends JpaRepository<TgBotState, Long> {
    Optional<TgBotState> findByChatId(Long chatId);
}
