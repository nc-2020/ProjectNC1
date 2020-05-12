package com.team.app.backend.rest;


import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserInvite;
import com.team.app.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/achievement")
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @GetMapping("/achievements")
    public ResponseEntity<List<Achievement>> getAchievements() {
        return ResponseEntity.ok().body(achievementService.getAchievements());
    }
}
