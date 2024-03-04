package com.example.restful_todo.mapper;

import java.util.*;
import org.apache.ibatis.annotations.*;
import com.example.restful_todo.model.*;

@Mapper
public interface TodoItemMapper {
    List<TodoItem> getAllItems();

    TodoItem getItem(String todoId);

    void insertItemWithoutDetails(String title, String status);

    void insertItemWithDetails(String title, String status, String details);

    void updateItem(String todoId, String title, String status, String details);

    void deleteItem(String todoId);
}
