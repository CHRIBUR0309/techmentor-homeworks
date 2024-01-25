package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import java.util.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoMapper {
    @Select("SELECT * FROM todo")
    List<TodoItem> getTodoItems();

    @Select("SELECT * FROM todo WHERE id = #{id}")
    TodoItem getTodoItem(TodoId todoId);

    @Insert("INSERT INTO todo (title, status, details) VALUES (#{title.getTitle()}, #{status.getStatus()}, #{details.getDetails()})")
    TodoItem createTodoItem(TodoItem todoItem);

    @Update("UPDATE todo SET title = #{requestTodoItem.getTitle().getTitle()}, status = #{requestTodoItem.getStatus().getStatus()}, details = #{requestTodoItem.getDetails().getDetails()} WHERE id = #{todoId.getId()}")
    TodoItem updateTodoItem(TodoId todoId, TodoItem requestTodoItem);

    @Delete("DELETE FROM todo WHERE id = #{id}")
    void deleteTodoItem(TodoId todoId);
}
