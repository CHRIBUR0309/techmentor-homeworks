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

    public List<TodoItem> getTodoItems() {
        return this.todoMapper.getTodoItems();
    }

    public TodoItem getTodoItem(TodoId todoId) {
        return this.todoMapper.getTodoItem(todoId);
    }

    public TodoItem createTodoItem(Title title, Status status, Details details) {
        return this.todoMapper.createTodoItem(title, status, details);
    }

    public TodoItem updateTodoItem(TodoId todoId, Title title, Status status, Details details) {
        return this.todoMapper.updateTodoItem(todoId, title, status, details);
    }

    public void deleteTodoItem(TodoId todoId) {
        this.todoMapper.deleteTodoItem(todoId);
    }
}
