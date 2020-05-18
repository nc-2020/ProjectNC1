package com.team.app.backend.rest;

import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class LanguageController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/change/{lang}", method = RequestMethod.PUT)
    public void changeSessionLanguage(HttpServletRequest request, HttpServletResponse response,
                                      @PathVariable("lang") String lang, @RequestBody User user) {
        if ("ua".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("ua", "ua"));
        } else if ("en".equals(lang)) {
            localeResolver.setLocale(request, response, Locale.getDefault());
        }
        userService.changeLanguage(lang,user.getId());
    }
}
