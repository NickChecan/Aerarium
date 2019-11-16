package com.aerarium.setting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class EnvironmentVariable {

    @Value("${app.security.encoder}")
    private String encoder;

    @Value("${app.oauth2.resource}")
    private String resource;

    @Value("${app.oauth2.client}")
    private String client;

    @Value("${app.oauth2.secret}")
    private String secret;

    @Value("${app.oauth2.scopes}")
    private String[] scopes;

    @Value("${app.oauth2.grants}")
    private String[] grants;

    @Value("${app.admin}")
    private String admin;

    @Value("${app.company}")
    private String company;

}
