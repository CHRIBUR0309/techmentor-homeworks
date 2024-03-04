package com.example.restful_todo.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.example.restful_todo.model.*;
import com.example.restful_todo.service.*;
import lombok.*;


@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoItemService todoItemService;

    @GetMapping("/get")
    public Collection<TodoItem> getAllItems() {
        return todoItemService.getAllItems();
    }

    @GetMapping("/get/{todoId}")
    public TodoItem getItem(@PathVariable String todoId) {
        return todoItemService.getItem(todoId);
    }

    @PostMapping("/post/{title}/{status}")
    public void insertItemWithoutDetails(@PathVariable String title, @PathVariable String status) {
        todoItemService.insertItem(new TodoItem("", title, status, ""));
    }

    @PostMapping("/post/{title}/{status}/{details}")
    public void insertItemWithDetails(@PathVariable String title, @PathVariable String status,
            @PathVariable String details) {
        todoItemService.insertItem(new TodoItem("", title, status, details));
    }

    @PatchMapping("/patch/{todoId}/{title}/{status}/{details}")
    public void updateItem(@PathVariable String todoId, @PathVariable String title,
            @PathVariable String status, @PathVariable String details) {
        todoItemService.updateItem(new TodoItem(todoId, title, status, details));
    }

    @DeleteMapping("/delete/{todoId}")
    public void updateItem(@PathVariable String todoId) {
        todoItemService.deleteItem(todoId);
    }
}
