package com.example.projectspringtodobackend.todoList.model;

import lombok.Builder;
import lombok.With;

@Builder
public record ToDo(
        String id,
        String description,
        @With
        Status status

) {
}
