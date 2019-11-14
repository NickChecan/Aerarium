package com.aerarium.service;

import com.aerarium.model.User;
import com.aerarium.repository.UserRepository;
import com.aerarium.security.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(username).map(UserSecurityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }

    public boolean isAdmin() {
        // Check if the logged user have the administrator role
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    }

    public User getMe() {
        // Get email from the current user to query his information
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails) ?
                ((UserDetails)principal).getUsername() : principal.toString();
        return this.userRepository.findByEmailIgnoreCase(email).orElseThrow(ResourceNotFoundException::new);
    }

}
