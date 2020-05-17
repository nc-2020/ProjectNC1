package com.team.app.backend.rest;


import com.team.app.backend.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/activity")
public class UserActivityController {

    @Autowired
    UserActivityService userActivityService;


    @GetMapping("/all/{user_id}")
    public ResponseEntity getFriendsActivities(@PathVariable("user_id") long user_id) {
        return ResponseEntity.ok(userActivityService.getFriendsActivities(user_id));
    }
}
