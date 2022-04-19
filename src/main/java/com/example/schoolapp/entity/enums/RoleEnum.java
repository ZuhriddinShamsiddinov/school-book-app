package com.example.schoolapp.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN(List.of("READ", "ADD", "DELETE")),
    USER(List.of("READ"));
    private final List<String> permissions;

}
