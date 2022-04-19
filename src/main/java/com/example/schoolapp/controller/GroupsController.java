package com.example.schoolapp.controller;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.entity.Groups;
import com.example.schoolapp.repository.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupsController {
    private final GroupsRepository groupsRepository;

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(groupsRepository.findAll());
    }

    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Groups groups) {
        groupsRepository.save(groups);
        ApiResponse response = ApiResponse.builder().message("Added").success(true).build();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        groupsRepository.deleteById(id);
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("Deleted").build());
    }
}
