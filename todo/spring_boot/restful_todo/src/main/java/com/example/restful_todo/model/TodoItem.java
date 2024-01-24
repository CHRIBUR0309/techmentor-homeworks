package com.example.restful_todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Table(name = "todo")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    @NotBlank
    @NotNull
    private String title;
    @Column(name = "status")
    @NotBlank
    @NotNull
    private String status;
    @Column(name = "details")
    private String details;
}
