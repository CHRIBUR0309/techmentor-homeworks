package com.example.restful_todo.controller;

import com.example.restful_todo.model.*;
import com.example.restful_todo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class TodoController {
    @Autowired
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("todo_items/")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        return new ResponseEntity<List<TodoItem>>(this.todoService.getTodoItems(), HttpStatus.OK);
    }

    @GetMapping("todo_items/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable Id id) {
        return new ResponseEntity<TodoItem>(this.todoService.getTodoItem(id), HttpStatus.OK);
    }

    @PostMapping("todo_items/")
    public ResponseEntity<TodoItem> createTodoItem(@Validated @RequestBody TodoItem todoItem) {
        return new ResponseEntity<TodoItem>(this.todoService.createTodoItem(todoItem),
                HttpStatus.CREATED);
    }

    @PatchMapping("todo_items/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@RequestBody TodoItem todoItem,
            @PathVariable Id id) {
        return new ResponseEntity<TodoItem>(this.todoService.updateTodoItem(id, todoItem),
                HttpStatus.CREATED);
    }

    @DeleteMapping("todo_items/{id}")
    public ResponseEntity<HttpStatus> deleteTodoItem(@PathVariable Id id) {
        this.todoService.deleteTodoItem(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
