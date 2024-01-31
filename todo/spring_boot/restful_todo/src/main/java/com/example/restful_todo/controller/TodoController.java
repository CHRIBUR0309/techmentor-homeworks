package com.example.restful_todo.controller;

import com.example.restful_todo.model.*;
import com.example.restful_todo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo_items/{userIdString}")
    public ResponseEntity<List<TodoItem>> getTodoItems(@PathVariable String userIdString) {
        return new ResponseEntity<List<TodoItem>>(
                this.todoService.getTodoItems(new UserId(userIdString)), HttpStatus.OK);
    }

    private TodoId todoIdStringToTodoId(String todoIdString) {
        return new TodoId(Integer.parseInt(todoIdString));
    }

    @GetMapping("/todo_items/{userIdString}/{todoIdString}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable String todoIdString,
            @PathVariable String userIdString) {
        return new ResponseEntity<TodoItem>(this.todoService.getTodoItem(
                todoIdStringToTodoId(todoIdString), new UserId(userIdString)), HttpStatus.OK);
    }

    @PostMapping("/todo_items/{userIdString}")
    public ResponseEntity<TodoItem> createTodoItem(@PathVariable String userIdString,
            @RequestBody String titleString, @RequestBody String statusString,
            @RequestBody String detailsString) {
        return new ResponseEntity<TodoItem>(
                this.todoService.createTodoItem(new UserId(userIdString), new Title(titleString),
                        new Status(statusString), new Details(detailsString)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/todo_items/{userIdString}/{todoIdString}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable String todoIdString,
            @PathVariable String userIdString, @RequestBody String titleString,
            @RequestBody String statusString, @RequestBody String detailsString) {
        return new ResponseEntity<TodoItem>(
                this.todoService.updateTodoItem(todoIdStringToTodoId(todoIdString),
                        new UserId(userIdString), new Title(titleString), new Status(statusString),
                        new Details(detailsString)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/todo_items/{userIdString}/{todoIdString}")
    public ResponseEntity<HttpStatus> deleteTodoItem(@PathVariable String todoIdString,
            @PathVariable String userIdString) {
        this.todoService.deleteTodoItem(todoIdStringToTodoId(todoIdString),
                new UserId(userIdString));
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
