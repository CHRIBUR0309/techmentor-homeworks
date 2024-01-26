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

    @GetMapping("/todo_items")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        return new ResponseEntity<List<TodoItem>>(this.todoService.getTodoItems(), HttpStatus.OK);
    }

    private TodoId idStringToTodoId(String idString) {
        return new TodoId(Integer.parseInt(idString));
    }

    @GetMapping("/todo_items/{idString}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable String idString) {
        return new ResponseEntity<TodoItem>(
                this.todoService.getTodoItem(idStringToTodoId(idString)), HttpStatus.OK);
    }

    @PostMapping("/todo_items")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody String titleString,
            @RequestBody String statusString, @RequestBody String detailsString) {
        return new ResponseEntity<TodoItem>(this.todoService.createTodoItem(new Title(titleString),
                new Status(statusString), new Details(detailsString)), HttpStatus.CREATED);
    }

    @PatchMapping("/todo_items/{idString}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable String idString,
            @RequestBody String titleString, @RequestBody String statusString,
            @RequestBody String detailsString) {
        return new ResponseEntity<TodoItem>(
                this.todoService.updateTodoItem(idStringToTodoId(idString), new Title(titleString),
                        new Status(statusString), new Details(detailsString)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/todo_items/{idString}")
    public ResponseEntity<HttpStatus> deleteTodoItem(@PathVariable String idString) {
        this.todoService.deleteTodoItem(idStringToTodoId(idString));
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
