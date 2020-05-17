package com.team.app.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties",
                 "classpath:sql_query.properties"})
public class AppConfig {

}
