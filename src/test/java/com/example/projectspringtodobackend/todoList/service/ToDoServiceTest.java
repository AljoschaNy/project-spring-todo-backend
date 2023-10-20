package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.model.ToDoToSave;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoServiceTest {
    private final ToDoRepo mockToDoRepo = mock(ToDoRepo.class);
    private final ToDoService toDoService = new ToDoService(mockToDoRepo);

    @Test
    public void addToDo() {
        ToDo expected = ToDo.builder()
                .id(UUID.randomUUID().toString())
                .description("test")
                .status(Status.OPEN)
                .build();
        when(mockToDoRepo.save(any(ToDo.class))).thenReturn(expected);
        ToDo actual = toDoService.addToDo(expected);
        assertEquals(expected,actual);
    }

}