package com.aerarium.event;

import com.aerarium.exception.MissingPasswordException;
import com.aerarium.exception.RoleOverflowException;
import com.aerarium.model.User;
import com.aerarium.repository.UserRepository;
import com.aerarium.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    private Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEventHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @HandleBeforeCreate
    public void handleUserCreate(User user) {
        user.setId(null); // Clear the id to avoid unnecessary update operations
        if (user.getRoles() != null) // Avoid more than one role per user
            if (user.getRoles().size() > 1)
                throw new RoleOverflowException();
        if (user.getPassword() == null || user.getPassword().equals(""))
            throw new MissingPasswordException();
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

}
