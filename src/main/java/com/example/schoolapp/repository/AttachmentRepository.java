package com.example.schoolapp.repository;

import com.example.schoolapp.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
