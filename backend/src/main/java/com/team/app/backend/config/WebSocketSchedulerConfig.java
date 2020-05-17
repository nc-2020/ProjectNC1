package com.team.app.backend.config;

import com.team.app.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



@Configuration
@EnableScheduling
public class WebSocketSchedulerConfig {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private NotificationService notificationService;

//    @Scheduled(fixedRate = 3000)
//    public void publishUpdates() {
//        System.out.println("Message: 1" );
//        template.convertAndSend("/topic/getall", notificationService.getAll(72L));
//    }

}
