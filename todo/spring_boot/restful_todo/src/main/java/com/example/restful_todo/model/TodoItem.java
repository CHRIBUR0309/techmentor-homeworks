package com.example.restful_todo.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class TodoItem {
    @NotBlank
    private final String todoId;
    @NotBlank
    private final String title;
    @Pattern(regexp = "(Unprocessed|Proceeding|Finished)")
    private final String status;
    @NotNull
    private final String details;
}
