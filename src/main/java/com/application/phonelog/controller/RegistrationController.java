package com.application.phonelog.controller;

import com.application.phonelog.model.User;
import com.application.phonelog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phonelog")
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
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam String confirmPassword, Model model) {

        if(!user.getPassword().equals(confirmPassword)){

         model.addAttribute("user", user);
         model.addAttribute("errorMessage", "パスワードが一致しません。");
            return "register";
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return "redirect:/phonelog";
    }
}