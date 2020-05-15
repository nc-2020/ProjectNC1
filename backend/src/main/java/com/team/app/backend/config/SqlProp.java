package com.team.app.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class SqlProp {
    @Autowired
    Environment environment;

}
