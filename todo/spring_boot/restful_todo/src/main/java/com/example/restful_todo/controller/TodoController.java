package com.example.restful_todo.controller;

import com.example.restful_todo.model.TodoItem;
import com.example.restful_todo.service.TodoItemService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    private final TodoItemService todoItemService;

    @GetMapping("/get")
    public Collection<TodoItem> getAllItems() {
        return todoItemService.getAllItems();
    }

    @GetMapping("/get/todoId_{todoId}")
    public TodoItem getItemById(@PathVariable String todoId) {
        return todoItemService.getItemById(todoId);
    }

    @GetMapping("/get/status_{status}")
    public Collection<TodoItem> getItemsByStatus(@PathVariable String status) {
        if (status == "All") {
            return getAllItems();
        }
        return todoItemService.getItemsByStatus(status);
    }

    @PostMapping("/post/{todoId}/{title}/{status}")
    public void insertItemWithoutDetails(
            @PathVariable String todoId, @PathVariable String title, @PathVariable String status) {
        todoItemService.insertItem(new TodoItem(todoId, title, status, ""));
    }

    @PostMapping("/post/{todoId}/{title}/{status}/{details}")
    public void insertItemWithDetails(
            @PathVariable String todoId,
            @PathVariable String title,
            @PathVariable String status,
            @PathVariable String details) {
        todoItemService.insertItem(new TodoItem(todoId, title, status, details));
    }

    @PatchMapping("/patch/{todoId}/{title}/{status}/{details}")
    public void updateItem(
            @PathVariable String todoId,
            @PathVariable String title,
            @PathVariable String status,
            @PathVariable String details) {
        todoItemService.updateItem(new TodoItem(todoId, title, status, details));
    }

    @DeleteMapping("/delete/{todoId}")
    public void deleteItem(@PathVariable String todoId) {
        todoItemService.deleteItem(todoId);
    }
}
