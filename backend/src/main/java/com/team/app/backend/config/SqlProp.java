package com.team.app.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource("classpath:path/sql_query.properties")
@Component
public class SqlProp {
    @Autowired
    Environment environment;
    public void execute(){
        String attr = this.environment.getProperty("ds.type");
    }
}
