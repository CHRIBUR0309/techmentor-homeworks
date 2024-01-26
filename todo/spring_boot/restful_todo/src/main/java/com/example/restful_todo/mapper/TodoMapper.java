package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import java.util.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoMapper {
    @Select("SELECT * FROM todo")
    List<TodoItem> getTodoItems();

    @Select("SELECT * FROM todo WHERE id = #{todoId.getId()}")
    TodoItem getTodoItem(TodoId todoId);

    @Insert("INSERT INTO todo (title, status, details) VALUES (#{title.getTitle()}, #{status.getStatus()}, #{details.getDetails()})")
    TodoItem createTodoItem(Title title, Status status, Details details);

    @Update("UPDATE todo SET title = #{title.getTitle()}, status = #{status.getStatus()}, details = #{details.getDetails()} WHERE id = #{todoId.getId()}")
    TodoItem updateTodoItem(TodoId todoId, Title title, Status status, Details details);

    @Delete("DELETE FROM todo WHERE id = #{todoId.getId()}")
    void deleteTodoItem(TodoId todoId);
}
