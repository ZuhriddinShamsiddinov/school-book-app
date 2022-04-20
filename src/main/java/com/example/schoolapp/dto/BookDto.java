package com.example.schoolapp.dto;

import com.example.schoolapp.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String name;
    private Integer groupId;
    private String author;
    private Language language;
    private MultipartFile file, picture;
}
