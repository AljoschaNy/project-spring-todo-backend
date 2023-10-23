package com.example.projectspringtodobackend.todoList.model;

import lombok.Builder;

@Builder
public record ToDo(
        String id,
        String description,
        Status status
) {
}
