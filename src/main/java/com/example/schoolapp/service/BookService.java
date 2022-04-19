package com.example.schoolapp.service;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.dto.BookDto;
import com.example.schoolapp.entity.Attachment;
import com.example.schoolapp.entity.Book;
import com.example.schoolapp.entity.Groups;
import com.example.schoolapp.entity.User;
import com.example.schoolapp.repository.BookRepository;
import com.example.schoolapp.repository.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.Multipart;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final GroupsRepository groupsRepository;
    private final AttachmentService attachmentService;


    public ApiResponse add(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());

        Optional<Groups> groupsOptional = groupsRepository.findById(bookDto.getGroupId());
        if (groupsOptional.isEmpty()) return ApiResponse.builder().message("Not Found group").success(false).build();
        book.setGroup(groupsOptional.get());


        MultipartHttpServletRequest file = bookDto.getFile();
        Attachment fileBook = attachmentService.upload(file);
        book.setFile(fileBook);
        MultipartHttpServletRequest picture = bookDto.getPicture();
        Attachment pictureBook = attachmentService.upload(picture);
        book.setPicture(pictureBook);



        book.setLanguage(bookDto.getLanguage());
        return ApiResponse.builder().message("Added").success(true).build();
    }

    public ApiResponse bookMarkAdd(Integer bookId, User user) {
        List<Book> bookList = user.getBookList();
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) return ApiResponse.builder().success(false).message("Not Found Book").build();
        bookList.add(bookOptional.get());
        user.setBookList(bookList);
        return ApiResponse.builder().message("Successfully added to bookmark").success(true).build();
    }
}
