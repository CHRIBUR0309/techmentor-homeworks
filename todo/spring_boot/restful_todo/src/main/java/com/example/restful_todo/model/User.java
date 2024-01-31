package com.example.restful_todo.model;

import lombok.*;

@Data
public class User {
    private final UserId userId;
    private final HashedPassword hashedPassword;
}
