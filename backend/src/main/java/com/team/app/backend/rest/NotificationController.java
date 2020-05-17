package com.team.app.backend.rest;

import com.team.app.backend.persistance.model.Notification;
import com.team.app.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
//@RequestMapping("api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Notification not) {
        try {
            notificationService.create(not);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().build();

    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Notification not) {
        try {
            notificationService.update(not);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Notification> notifications) {
        try {
            notificationService.delete(notifications);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().build();

    }
//    @MessageMapping("/notifications")
//    @SendTo("/topic/getall")
//    public Notification greeting() throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Notification();
//    }
    @GetMapping("/get/{id}")
    public ResponseEntity getAll (@PathVariable("id") Long user_id) {
        List<Notification>  notifications = null;
        try {
            notifications = notificationService.getAll(user_id);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(notifications);

    }
    @GetMapping("/settings/get/{id}")
    public  ResponseEntity getSetting(@PathVariable("id") Long userId) {
        List<Notification> notifications = null;
        try {
            notifications = this.notificationService.getSetting(userId);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(notifications);
    }
    @PostMapping("/settings")
    public ResponseEntity setSetting(@RequestBody Notification not) {
        try {
            this.notificationService.setSetting(not);
        }
        catch (DataAccessException sqlEx)
        {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().build();

    }


}
