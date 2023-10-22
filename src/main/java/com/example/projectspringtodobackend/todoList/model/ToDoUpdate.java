package com.example.projectspringtodobackend.todoList.model;

public record ToDoUpdate(
        String description,
        Status status
) {
}
