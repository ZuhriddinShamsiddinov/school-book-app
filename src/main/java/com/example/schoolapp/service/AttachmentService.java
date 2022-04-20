package com.example.schoolapp.service;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.entity.Attachment;
import com.example.schoolapp.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public ResponseEntity<?> download(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isEmpty())
            return ResponseEntity.status(404).body(ApiResponse.builder().message("Not Found File").build());
        Attachment attachment = attachmentOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file")
                .body(attachment.getBytes());
    }
}
