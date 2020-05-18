package com.team.app.backend.rest;


import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.Notification;
import com.team.app.backend.persistance.model.UserAchievement;
import com.team.app.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/achievement")
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @GetMapping("/all")
    public ResponseEntity<List<Achievement>> getAchievements() {
        return ResponseEntity.ok().body(achievementService.getAchievements());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<UserAchievement>> getUserAchievements(@PathVariable("user_id") long id) {
        return ResponseEntity.ok().body(achievementService.getUserAchievements(id));
    }

    @GetMapping("check/{user_id}")
    public ResponseEntity checkUserAchievements(@PathVariable("user_id") long id) {
        achievementService.checkUserAchievement(id);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @PostMapping("/create")
    public ResponseEntity createAchievement(@RequestBody Achievement achievement) {
        Map<String, String> response = new HashMap<>();
        try {
            achievementService.createAchievement(achievement);
        } catch (DataAccessException sqlEx){
            response.put("message", "There is a problem while creating achievement");
            ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Achievement was created!");
        return ResponseEntity.ok(response);
    }

}
