package com.example.schoolapp.component;


import com.example.schoolapp.entity.User;
import com.example.schoolapp.entity.enums.RoleEnum;
import com.example.schoolapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            userRepository.save(new User("admin", passwordEncoder.encode("123"), RoleEnum.ADMIN));
        }
    }
}

