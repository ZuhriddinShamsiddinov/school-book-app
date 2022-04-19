package com.example.schoolapp.service;


import com.example.schoolapp.entity.User;
import com.example.schoolapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User toplimadi"));
    }
}
