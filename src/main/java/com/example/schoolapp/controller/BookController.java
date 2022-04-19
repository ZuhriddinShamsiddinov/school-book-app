package com.example.schoolapp.controller;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.dto.BookDto;
import com.example.schoolapp.entity.Book;
import com.example.schoolapp.entity.User;
import com.example.schoolapp.repository.BookRepository;
import com.example.schoolapp.repository.UserRepository;
import com.example.schoolapp.service.AttachmentService;
import com.example.schoolapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final AttachmentService attachmentService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return attachmentService.download(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        ApiResponse build = ApiResponse.builder().message("Deleted").success(true).build();
        return ResponseEntity.ok().body(build);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BookDto bookDto) {
        ApiResponse response = bookService.add(bookDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }


    @PostMapping("/{id}/favorite")
    public HttpEntity<?> addFavourite(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        user.getBookList().add(optionalBook.get());
        userRepository.save(user);
        return ResponseEntity.ok().body("Success");
    }

    @DeleteMapping("/{id}/favorite")
    public HttpEntity<?> deleteFavourite(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        user.getBookList().removeIf(book -> book.getId().equals(optionalBook.get().getId()));
        userRepository.save(user);
        return ResponseEntity.ok().body("Success!");
    }
}
