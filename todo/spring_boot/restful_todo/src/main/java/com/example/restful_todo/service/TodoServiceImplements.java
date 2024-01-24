package com.example.restful_todo.service;

import com.example.restful_todo.model.*;
import com.example.restful_todo.repository.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public class TodoServiceImplements implements TodoService {
    private final TodoRepository todoRepository;

    public TodoServiceImplements(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoItem> getTodoItems() {
        return this.todoRepository.findAll();
    }

    @Override
    public TodoItem getTodoItem(int id) {
        Optional<TodoItem> todoItemOptional = this.todoRepository.findById(id);
        if (todoItemOptional.isEmpty()) {
            throw new IllegalArgumentException("Error: No such item.");
        }
        return todoItemOptional.get();
    }

    @Override
    public TodoItem createTodoItem(TodoItem todoItem) {
        return this.todoRepository.save(todoItem);
    }

    @Override
    public TodoItem updateTodoItem(int id, TodoItem requestTodoItem) {
        TodoItem todoItem = getTodoItem(id);
        todoItem.setTitle(requestTodoItem.getTitle() == null ? todoItem.getTitle()
                : requestTodoItem.getTitle());
        todoItem.setStatus(requestTodoItem.getStatus() == null ? todoItem.getStatus()
                : requestTodoItem.getStatus());
        todoItem.setDetails(requestTodoItem.getDetails() == null ? todoItem.getDetails()
                : requestTodoItem.getDetails());
        return this.todoRepository.save(todoItem);
    }

    @Override
    public void deleteTodoItem(int id) {
        getTodoItem(id);
        this.todoRepository.deleteById(id);
    }
}
