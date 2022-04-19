package com.example.schoolapp.service;

import com.example.schoolapp.dto.ApiResponse;
import com.example.schoolapp.entity.Attachment;
import com.example.schoolapp.entity.AttachmentContent;
import com.example.schoolapp.repository.AttachmentContentRepository;
import com.example.schoolapp.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public Attachment upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        Attachment attachmentL = null;
        while (fileNames.hasNext()) {
            List<MultipartFile> files = request.getFiles(fileNames.next());
            for (MultipartFile file : files) {
                Attachment attachment = new Attachment();
                attachment.setName(file.getOriginalFilename());
                attachment.setSize(file.getSize());
                attachment.setType(file.getContentType());

                Attachment save = attachmentRepository.save(attachment);
                attachmentL = attachment;

                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(save);
                try {
                    attachmentContent.setBytes(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachmentContentRepository.save(attachmentContent);
            }
        }
        return attachmentL;
    }


    public HttpEntity<?> download(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isEmpty())
            return ResponseEntity.status(404).body(ApiResponse.builder().message("Not Found File").build());
        Attachment attachment = attachmentOptional.get();
        Optional<AttachmentContent> attachmentContentOptional = attachmentContentRepository.findByAttachment(attachment);
        AttachmentContent attachmentContent = attachmentContentOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file")
                .body(attachmentContent.getBytes());
    }
}
