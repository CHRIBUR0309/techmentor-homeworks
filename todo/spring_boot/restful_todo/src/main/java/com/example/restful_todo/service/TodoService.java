package com.example.restful_todo.service;

import com.example.restful_todo.model.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public interface TodoService {
    List<TodoItem> getTodoItems();

    TodoItem getTodoItem(int id);

    TodoItem createTodoItem(TodoItem todoItem);

    TodoItem updateTodoItem(int id, TodoItem requestTodoItem);

    void deleteTodoItem(int id);
}
