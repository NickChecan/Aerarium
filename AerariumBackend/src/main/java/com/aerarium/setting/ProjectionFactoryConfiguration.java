package com.aerarium.setting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
public class ProjectionFactoryConfiguration {

     @Bean
     public SpelAwareProxyProjectionFactory projectionFactory() {
         return new SpelAwareProxyProjectionFactory();
     }

}
