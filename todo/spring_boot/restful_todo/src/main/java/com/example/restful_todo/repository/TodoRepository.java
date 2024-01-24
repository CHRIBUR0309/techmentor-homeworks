package com.example.restful_todo.repository;

import com.example.restful_todo.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {
}
