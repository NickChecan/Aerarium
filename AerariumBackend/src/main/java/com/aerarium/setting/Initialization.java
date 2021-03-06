package com.aerarium.setting;

import com.aerarium.exception.AccessProvidenceException;
import com.aerarium.model.Role;
import com.aerarium.model.User;
import com.aerarium.repository.CompanyRepository;
import com.aerarium.repository.RoleRepository;
import com.aerarium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Initialization implements ApplicationListener<ContextRefreshedEvent> {

    private final EnvironmentVariable env;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final CompanyRepository companyRepository;

    @Autowired
    public Initialization(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          CompanyRepository companyRepository,
                          EnvironmentVariable env) {
        this.env = env;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /* Application administrator user creation*/
        if (this.userRepository.findByEmailIgnoreCase("admin").isEmpty()) {

            // Get the administrator role for the user creation
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(this.roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(AccessProvidenceException::new));

            // Set up the administrator user
            User admin = new User();
            admin.setActive(true);
            admin.setName("Administrator");
            admin.setEmail("admin");
            admin.setCompany(this.companyRepository.findByName("Aerarium")
                    .orElseThrow(AccessProvidenceException::new));
            admin.setPassword(this.passwordEncoder.encode("BigPassword_123"));
            admin.setRoles(adminRoles);

            userRepository.save(admin);

        }

    }

}
