package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import java.util.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoMapper {
    @Select("SELECT * FROM todos WHERE user_id = #{userId.getUserId()}")
    List<TodoItem> getTodoItems(UserId userId);

    @Select("SELECT * FROM todos WHERE todo_id = #{todoId.getTodoId()} AND user_id = #{userId.getUserId()}")
    TodoItem getTodoItem(TodoId todoId, UserId userId);

    @Insert("INSERT INTO todos (user_id, title, status, details) VALUES (#{userId.getUserId()}, #{title.getTitle()}, #{status.getStatus()}, #{details.getDetails()})")
    TodoItem createTodoItem(UserId userId, Title title, Status status, Details details);

    @Update("UPDATE todos SET title = #{title.getTitle()}, status = #{status.getStatus()}, details = #{details.getDetails()} WHERE todo_id = #{todoId.getTodoId()} AND user_id = #{userId.getUserId()}")
    TodoItem updateTodoItem(TodoId todoId, UserId userId, Title title, Status status,
            Details details);

    @Delete("DELETE FROM todos WHERE todo_id = #{todoId.getTodoId()} AND user_id = #{userId.getUserId()}")
    void deleteTodoItem(TodoId todoId, UserId userId);
}
