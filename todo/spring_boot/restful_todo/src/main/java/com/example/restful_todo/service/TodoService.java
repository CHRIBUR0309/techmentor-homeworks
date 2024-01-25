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

    public TodoItem createTodoItem(TodoItem todoItem) {
        return this.todoMapper.createTodoItem(todoItem);
    }

    public TodoItem updateTodoItem(TodoId todoId, TodoItem requestTodoItem) {
        return this.todoMapper.updateTodoItem(todoId, requestTodoItem);
    }

    public void deleteTodoItem(TodoId todoId) {
        this.todoMapper.deleteTodoItem(todoId);
    }
}
