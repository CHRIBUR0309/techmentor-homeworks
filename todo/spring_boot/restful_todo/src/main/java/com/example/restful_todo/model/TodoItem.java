package com.example.restful_todo.model;

import lombok.*;

@Data
public class TodoItem {
    private final TodoId todoId;
    private final UserId userId;
    private final Title title;
    private final Status status;
    private final Details details;
}
