package com.example.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @Builder.Default
    private boolean success = false;
    private String message;
    @Builder.Default
    private Object obj = new HashMap<>();
}
