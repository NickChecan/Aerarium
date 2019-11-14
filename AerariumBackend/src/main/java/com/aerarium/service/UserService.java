package com.aerarium.service;

import com.aerarium.exception.RoleOverflowException;
import com.aerarium.model.User;
import com.aerarium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User update(Long id, User user) {
        return this.userRepository.findById(id).map(existingUser -> {

            // Change and validate user roles when necessary
            if (user.getRoles() != null)
                if (user.getRoles().size() > 1)
                    throw new RoleOverflowException();

            // Password change request
            if (user.getPassword() != null && !user.getPassword().equals(""))
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

            if (user.getCompany() != null) // Company
                existingUser.setCompany(user.getCompany());
            if (user.getName() != null) // Name
                existingUser.setName(user.getName());
            if (user.getEmail() != null) // E-mail
                existingUser.setEmail(user.getEmail());
            if (user.getRoles() != null) // Roles
                existingUser.setRoles(user.getRoles());
            if (user.getPhone() != null) // Phone
                existingUser.setPhone(user.getPhone());

            // Enable or disable user
            if (user.isActive() != existingUser.isActive())
                existingUser.setActive(user.isActive());

            return this.userRepository.save(existingUser);

        }).orElseThrow(ResourceNotFoundException::new);
    }

}
