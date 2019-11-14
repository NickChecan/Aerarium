package com.aerarium.security;

import com.aerarium.setting.EnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final EnvironmentVariable env;

    @Autowired
    public ResourceServerConfiguration(EnvironmentVariable env) {
        this.env = env;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceConfig) {
        resourceConfig.resourceId(this.env.getResource());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable due to the android nature of the application
                .httpBasic().disable() // Disable default Spring Security default login process
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                /* Base path for metadata analysis */
                .antMatchers(HttpMethod.GET, "/").fullyAuthenticated()
                /* Metadata endpoint */
                .antMatchers(HttpMethod.GET, "/profiles/**").fullyAuthenticated()
                /* Role endpoints */
                .antMatchers(HttpMethod.GET, "/roles/**").hasAnyRole("ADMIN")
                /* User endpoints */
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN")
                /* Bank endpoints */
                .antMatchers(HttpMethod.GET, "/banks/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/banks/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/banks/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/banks/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/banks/**").hasAnyRole("ADMIN")
                /* Payment Type endpoints */
                .antMatchers(HttpMethod.GET, "/paymentTypes/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/paymentTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/paymentTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/paymentTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/paymentTypes/**").hasAnyRole("ADMIN")
                /* Bill Status endpoints */
                .antMatchers(HttpMethod.GET, "/billStatuses/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/billStatuses/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/billStatuses/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/billStatuses/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/billStatuses/**").hasAnyRole("ADMIN")
                /* Installment endpoints */
                .antMatchers(HttpMethod.GET, "/installments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/installments/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/installments/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/installments/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/installments/**").hasAnyRole("ADMIN")
                /* Specification endpoints */
                .antMatchers(HttpMethod.GET, "/specifications/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/specifications/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/specifications/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/specifications/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/specifications/**").hasAnyRole("ADMIN")
                /* Cost Type endpoints */
                .antMatchers(HttpMethod.GET, "/costTypes/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/costTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/costTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/costTypes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/costTypes/**").hasAnyRole("ADMIN")
                /* Company endpoints */
                .antMatchers(HttpMethod.GET, "/companies/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/companies/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/companies/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/companies/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/companies/**").hasAnyRole("ADMIN")
                /* Sale endpoints */
                .antMatchers(HttpMethod.GET, "/sales/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/sales/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/sales/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/sales/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/sales/**").hasAnyRole("ADMIN", "USER")
                /* Expense endpoints */
                .antMatchers(HttpMethod.GET, "/expenses/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/expenses/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/expenses/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/expenses/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/expenses/**").hasAnyRole("ADMIN", "USER")
                /* Investment endpoints */
                .antMatchers(HttpMethod.GET, "/investments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/investments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/investments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/investments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/investments/**").hasAnyRole("ADMIN", "USER")
                /* Accounts Payable endpoints */
                .antMatchers(HttpMethod.GET, "/accountsPayable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/accountsPayable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/accountsPayable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/accountsPayable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/accountsPayable/**").hasAnyRole("ADMIN", "USER")
                /* Accounts Receivable endpoints */
                .antMatchers(HttpMethod.GET, "/accountsReceivable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/accountsReceivable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/accountsReceivable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/accountsReceivable/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/accountsReceivable/**").hasAnyRole("ADMIN", "USER")
                /* Any other requests... */
                //.anyRequest().denyAll();
                .anyRequest().permitAll();
    }

}
