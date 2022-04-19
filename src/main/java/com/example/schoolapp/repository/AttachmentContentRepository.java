package com.example.schoolapp.repository;

import com.example.schoolapp.entity.Attachment;
import com.example.schoolapp.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    Optional<AttachmentContent> findByAttachment(Attachment attachment);

}


