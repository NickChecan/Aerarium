package com.aerarium.event;

import com.aerarium.exception.InvalidOperationException;
import com.aerarium.exception.MissingPasswordException;
import com.aerarium.exception.RoleOverflowException;
import com.aerarium.model.User;
import com.aerarium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {

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

    @HandleBeforeDelete
    public void handleBeforeDelete(User user) {
        // Avoid admin user deletion
        if (user.getEmail().equals("admin"))
            throw new InvalidOperationException();
    }

}
