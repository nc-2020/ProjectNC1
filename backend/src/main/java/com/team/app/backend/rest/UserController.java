package com.team.app.backend.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class UserController {

    //TO DO
    @GetMapping("/quiz/id")
    public Map<String, Object> quiz() {
        Map<String, Object> model = new HashMap<String, Object>();

        return model;
    }
    //TO DO
    @GetMapping("/quiz")
    public Map<String, Object> qui() {
        Map<String, Object> model = new HashMap<String, Object>();

        return model;
    }
}
