package com.example.restful_todo.service;

import com.example.restful_todo.mapper.*;
import com.example.restful_todo.model.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoRepository;

    public TodoService(TodoMapper todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoItem> getTodoItems() {
        return this.todoRepository.getTodoItems();
    }

    public TodoItem getTodoItem(Id id) {
        return this.todoRepository.getTodoItem(id);
    }

    public TodoItem createTodoItem(TodoItem todoItem) {
        return this.todoRepository.createTodoItem(todoItem);
    }

    public TodoItem updateTodoItem(Id id, TodoItem requestTodoItem) {
        return this.todoRepository.updateTodoItem(id, requestTodoItem);
    }

    public void deleteTodoItem(Id id) {
        this.todoRepository.deleteTodoItem(id);
    }
}
