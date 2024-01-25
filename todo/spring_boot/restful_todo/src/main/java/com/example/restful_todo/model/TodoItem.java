package com.example.restful_todo.model;

import lombok.*;

@Data
public class TodoItem {
    private TodoId todoId;
    private Title title;
    private Status status;
    private Details details;
}
