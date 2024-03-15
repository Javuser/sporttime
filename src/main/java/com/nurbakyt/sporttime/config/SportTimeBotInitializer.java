//package com.nurbakyt.sporttime.config;
//
////import com.nurbakyt.sporttime.service.bot.SportTimeBot;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//@Slf4j
//@Configuration
//public class SportTimeBotInitializer {
//
//    private final SportTimeBot sportTimeBot;
//
//    public SportTimeBotInitializer(SportTimeBot sportTimeBot) {
//        this.sportTimeBot = sportTimeBot;
//    }
//
//    @Bean
////    public TelegramBotsApi telegramBotsApi(SportTimeBot sportTimeBot) throws TelegramApiException {
////        var api = new TelegramBotsApi(DefaultBotSession.class);
////        api.registerBot(sportTimeBot);
////        return api;
////    }
//    @EventListener({ContextRefreshedEvent.class})
//    public void init() throws TelegramApiException {
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        try {
//            telegramBotsApi.registerBot(sportTimeBot);
//        } catch (TelegramApiException e){
//            log.error("Error occurred: " + e.getMessage() );
//        }
//    }
//
//
//}
