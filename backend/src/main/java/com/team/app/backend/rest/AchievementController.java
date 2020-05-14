package com.team.app.backend.rest;


import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;
import com.team.app.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
