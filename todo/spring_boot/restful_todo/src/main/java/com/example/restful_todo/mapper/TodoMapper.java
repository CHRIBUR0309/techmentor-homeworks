package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import java.util.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoMapper {
    @Select("SELECT * FROM todo")
    List<TodoItem> getTodoItems();

    @Select("SELECT * FROM todo WHERE id = #{id.getId()}")
    TodoItem getTodoItem(Id id);

    @Insert("INSERT INTO todo (title, status, details) VALUES (#{todoItem.getTitle().getTitle()}, #{todoItem.getStatus().getStatus()}, #{todoItem.getDetails().getDetails()})")
    TodoItem createTodoItem(TodoItem todoItem);

    @Update("UPDATE todo SET title = #{todoItem.getTitle().getTitle()}, status = #{todoItem.getStatus().getStatus()}, details = #{todoItem.getDetails().getDetails()} WHERE id = #{id.getId()}")
    TodoItem updateTodoItem(Id id, TodoItem requestTodoItem);

    @Delete("DELETE FROM todo WHERE id = #{id.getId()}")
    void deleteTodoItem(Id id);
}
