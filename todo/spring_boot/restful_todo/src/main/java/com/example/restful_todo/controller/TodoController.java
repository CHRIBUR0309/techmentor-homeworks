package com.example.restful_todo.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import com.example.restful_todo.model.*;
import com.example.restful_todo.service.*;
import lombok.*;


@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoItemService todoItemService;

    @GetMapping("/")
    public String topPage() {
        return "index";
    }

    @GetMapping("/get")
    public String getAllItems(Model model) {
        model.addAttribute("todoItems", todoItemService.getAllItems());
        return "todo_item";
    }

    @GetMapping("/get/{todoId}")
    public String getItem(@PathVariable long todoId, Model model) {
        model.addAttribute("todoId", todoId);
        model.addAttribute("todoItem", todoItemService.getItem(todoId));
        return "todo_item";
    }

    @PostMapping("/post/{title}/{status}")
    public String insertItemWithoutDetails(@PathVariable String title, @PathVariable String status,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        todoItemService.insertItem(new TodoItem(0, title, status, ""));
        return "redirect:/";
    }

    @PostMapping("/post/{title}/{status}/{details}")
    public String insertItemWithDetails(@PathVariable String title, @PathVariable String status,
            @PathVariable String details, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("details", details);
        todoItemService.insertItem(new TodoItem(0, title, status, details));
        return "redirect:/";
    }

    @PostMapping("/patch/{todoId}/{title}/{status}/{details}")
    public String updateItem(@PathVariable long todoId, @PathVariable String title,
            @PathVariable String status, @PathVariable String details, Model model) {
        model.addAttribute("todoId", todoId);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("details", details);
        todoItemService.updateItem(new TodoItem(todoId, title, status, details));
        return "redirect:/";
    }

    @PostMapping("/delete/{todoId}")
    public String updateItem(@PathVariable long todoId, Model model) {
        model.addAttribute("todoId", todoId);
        todoItemService.deleteItem(todoId);
        return "redirect:/";
    }
}
