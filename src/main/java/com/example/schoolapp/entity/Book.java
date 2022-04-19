package com.example.schoolapp.entity;

import com.example.schoolapp.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name, author;
    @ManyToOne
    private Groups group;
    @OneToOne(cascade = {CascadeType.REMOVE})
    private Attachment file;
    @OneToOne(cascade = {CascadeType.REMOVE})
    private Attachment picture;
    @Enumerated(EnumType.STRING)
    private Language language;
}
