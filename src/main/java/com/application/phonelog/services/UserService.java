package com.application.phonelog.services;

import com.application.phonelog.model.User;
import com.application.phonelog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかれませんでした"));

    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getName())
            .password(user.getPassword())
            .authorities("USER")
            .build();
    }
}
