package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import java.util.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoMapper {
    @Select("SELECT * FROM todos")
    List<TodoItem> getTodoItems();

    @Select("SELECT * FROM todos WHERE id = #{todoId.getId()}")
    TodoItem getTodoItem(TodoId todoId);

    @Insert("INSERT INTO todos (title, status, details) VALUES (#{title.getTitle()}, #{status.getStatus()}, #{details.getDetails()})")
    TodoItem createTodoItem(Title title, Status status, Details details);

    @Update("UPDATE todos SET title = #{title.getTitle()}, status = #{status.getStatus()}, details = #{details.getDetails()} WHERE id = #{todoId.getId()}")
    TodoItem updateTodoItem(TodoId todoId, Title title, Status status, Details details);

    @Delete("DELETE FROM todos WHERE id = #{todoId.getId()}")
    void deleteTodoItem(TodoId todoId);
}
