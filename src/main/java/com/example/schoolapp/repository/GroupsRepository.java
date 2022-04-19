package com.example.schoolapp.repository;

import com.example.schoolapp.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups, Integer> {
}
