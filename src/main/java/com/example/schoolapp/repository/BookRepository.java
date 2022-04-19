package com.example.schoolapp.repository;

import com.example.schoolapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
