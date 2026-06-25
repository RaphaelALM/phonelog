package com.application.phonelog.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("username")
    public String username(Principal principal) {
        if (principal == null) {
            return "";
        }

        return principal.getName();
    }
}
