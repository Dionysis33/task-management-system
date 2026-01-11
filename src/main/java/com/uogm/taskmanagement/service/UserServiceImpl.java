package com.uogm.taskmanagement.service;

import com.uogm.taskmanagement.dto.UserRegistrationDTO;
import com.uogm.taskmanagement.entity.Role;
import com.uogm.taskmanagement.entity.User;
import com.uogm.taskmanagement.repository.RoleRepository;
import com.uogm.taskmanagement.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Υλοποίηση της υπηρεσίας διαχείρισης χρηστών.
 * ΣΗΜΑΝΤΙΚΟ: Υλοποιεί το UserDetailsService του Spring Security.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public void saveUser(UserRegistrationDTO registrationDto) {
        User user = new User();
        user.setName(registrationDto.getName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_MEMBER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_MEMBER");
            return roleRepository.save(newRole);
        });

        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}