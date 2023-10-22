package com.example.projectspringtodobackend.todoList.model;

import lombok.Builder;

@Builder
public record ToDoUpdate(
        String description,
        Status status
) {
}
