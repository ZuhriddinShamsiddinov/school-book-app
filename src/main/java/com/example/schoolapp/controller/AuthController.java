package com.example.schoolapp.controller;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.dto.LoginDto;
import com.example.schoolapp.entity.User;
import com.example.schoolapp.entity.enums.RoleEnum;
import com.example.schoolapp.repository.UserRepository;
import com.example.schoolapp.security.JwtProvider;
import com.example.schoolapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    final JwtProvider jwtProvider;
    final AuthService authService;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        UserDetails userDetails = authService.loadUserByUsername(loginDto.getName());
        String token = jwtProvider.generateToken(loginDto.getName());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByUsername(loginDto.getName());
        if (userOptional.isPresent())
            return ResponseEntity.ok().body(ApiResponse.builder().message("User Already exist!").success(false).build());
        User user = new User();
        user.setUsername(loginDto.getName());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        user.setRoleEnum(RoleEnum.USER);
        userRepository.save(user);
        String token = jwtProvider.generateToken(loginDto.getName());
        return ResponseEntity.ok().body(token);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
