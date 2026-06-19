package com.application.phonelog.controller;

import com.application.phonelog.model.User;
import com.application.phonelog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository repository,
            PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return "redirect:/login";
    }
}