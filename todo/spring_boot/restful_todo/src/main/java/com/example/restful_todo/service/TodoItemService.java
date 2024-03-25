package com.example.restful_todo.service;

import com.example.restful_todo.mapper.TodoItemMapper;
import com.example.restful_todo.model.TodoItem;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TodoItemService {
    private final TodoItemMapper todoItemMapper;

    public List<TodoItem> getAllItems() {
        return todoItemMapper.getAllItems();
    }

    public TodoItem getItemById(String todoId) {
        return todoItemMapper.getItemById(todoId);
    }

    public List<TodoItem> getItemsByStatus(String status) {
        return todoItemMapper.getItemsByStatus(status);
    }

    public void insertItem(TodoItem todoItem) {
        if (todoItem.getDetails().isBlank()) {
            todoItemMapper.insertItemWithoutDetails(
                    todoItem.getTodoId(), todoItem.getTitle(), todoItem.getStatus());
        } else {
            todoItemMapper.insertItemWithDetails(
                    todoItem.getTodoId(),
                    todoItem.getTitle(),
                    todoItem.getStatus(),
                    todoItem.getDetails());
        }
    }

    public void updateItem(TodoItem todoItem) {
        todoItemMapper.updateItem(
                todoItem.getTodoId(),
                todoItem.getTitle(),
                todoItem.getStatus(),
                todoItem.getDetails());
    }

    public void deleteItem(String todoId) {
        todoItemMapper.deleteItem(todoId);
    }
}
