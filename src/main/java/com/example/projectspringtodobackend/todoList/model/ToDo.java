package com.example.projectspringtodobackend.todoList.model;

public record ToDo(
        String id,
        String description,
        Status status

) {
}
