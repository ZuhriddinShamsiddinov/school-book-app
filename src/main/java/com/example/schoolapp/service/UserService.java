package com.example.schoolapp.service;

import com.example.schoolapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//
//    public ApiResponse addUser(UserDto userDto) {
//        String roleDto = userDto.get();
//        RoleEnum roleEnum = RoleEnum.valueOf(roleDto);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User principal = (User) authentication.getPrincipal();
//
//        for (Role role : principal.getRoleList()) {
//            if (role.getRoleName().name().equals(RoleEnum.ROLE_ADMIN.name())) {
//                User user = new User();
//                user.setUsername(userDto.getUsername());
//                user.setPassword(passwordEncoder.encode("111"));
//
//                Optional<Role> roleOptional = roleRepository.findByRoleName(roleEnum);
//                user.setRoleList(new LinkedHashSet<>(Collections.singleton(roleOptional.get())));
//                userRepository.save(user);
//            } else {
//                ApiResponse.builder().success(false).message("Szda bunaqa huquq yoq").build();
//            }
//        }
//        return ApiResponse.builder().message("Added!!").success(true).build();
//    }
}
