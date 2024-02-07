package com.example.restful_todo.service;

import java.util.List;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import com.example.restful_todo.mapper.*;
import com.example.restful_todo.model.*;
import lombok.*;

@RequiredArgsConstructor
@Service
@Transactional
public class TodoItemService {
    private final TodoItemMapper todoItemMapper;

    public List<TodoItem> getAllItems() {
        return todoItemMapper.getAllItems();
    }

    public TodoItem getItem(long todoId) {
        return todoItemMapper.getItem(todoId);
    }

    public void insertItem(TodoItem todoItem) {
        if (todoItem.getDetails().isBlank()) {
            todoItemMapper.insertItemWithoutDetails(todoItem.getTitle(), todoItem.getStatus());
        } else {
            todoItemMapper.insertItemWithDetails(todoItem.getTitle(), todoItem.getStatus(),
                    todoItem.getDetails());
        }
    }

    public void updateItem(TodoItem todoItem) {
        todoItemMapper.updateItem(todoItem.getTodoId(), todoItem.getTitle(), todoItem.getStatus(),
                todoItem.getDetails());
    }

    public void deleteItem(long todoId) {
        todoItemMapper.deleteItem(todoId);
    }
}
