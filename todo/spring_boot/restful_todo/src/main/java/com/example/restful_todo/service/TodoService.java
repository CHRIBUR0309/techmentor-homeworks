package com.example.restful_todo.service;

import com.example.restful_todo.mapper.*;
import com.example.restful_todo.model.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<TodoItem> getTodoItems(UserId userId) {
        return this.todoMapper.getTodoItems(userId);
    }

    public TodoItem getTodoItem(TodoId todoId, UserId userId) {
        return this.todoMapper.getTodoItem(todoId, userId);
    }

    public TodoItem createTodoItem(UserId userId, Title title, Status status, Details details) {
        return this.todoMapper.createTodoItem(userId, title, status, details);
    }

    public TodoItem updateTodoItem(TodoId todoId, UserId userId, Title title, Status status,
            Details details) {
        return this.todoMapper.updateTodoItem(todoId, userId, title, status, details);
    }

    public void deleteTodoItem(TodoId todoId, UserId userId) {
        this.todoMapper.deleteTodoItem(todoId, userId);
    }
}
