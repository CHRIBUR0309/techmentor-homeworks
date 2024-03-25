package com.example.restful_todo.mapper;

import com.example.restful_todo.model.TodoItem;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoItemMapper {
    List<TodoItem> getAllItems();

    TodoItem getItemById(String todoId);

    List<TodoItem> getItemsByStatus(String status);

    void insertItemWithoutDetails(String todoId, String title, String status);

    void insertItemWithDetails(String todoId, String title, String status, String details);

    void updateItem(String todoId, String title, String status, String details);

    void deleteItem(String todoId);
}
