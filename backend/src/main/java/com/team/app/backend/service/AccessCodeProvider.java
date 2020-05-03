package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.Session;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.HashMap;
import java.util.Map;

public class AccessCodeProvider {

    public static String createAccessCode(Session session, Quiz quiz) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        String link = "?quiz_id=" + quiz.getId() + "&session_id=" + session.getId();
        return basicTextEncryptor.encrypt(link);
    }

    public static Map<String, Long> parseAccessCode(String accessCode) {
        Map<String, Long> map = new HashMap<>();
        String[] values =
                accessCode
                .replace("?quiz_id=", "")
                .replace("session_id=", "")
                .split("&");
        map.put("quiz_id", Long.parseLong(values[0]));
        map.put("session_id", Long.parseLong(values[1]));
        return map;
    }

}
