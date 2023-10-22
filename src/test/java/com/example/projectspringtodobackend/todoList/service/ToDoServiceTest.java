package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.exception.NoSuchTaskException;
import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ToDoServiceTest {
    private final ToDoRepo mockToDoRepo = mock(ToDoRepo.class);
    private final ToDoService toDoService = new ToDoService(mockToDoRepo);

    @Test
    public void addToDo() {
        ToDo expected = ToDo.builder()
                .id(null)
                .description("test123")
                .status(Status.OPEN)
                .build();
        when(mockToDoRepo.save(any(ToDo.class))).thenReturn(expected);
        ToDo actual = toDoService.addToDo(expected);
        verify(mockToDoRepo).save(expected);
        assertEquals(expected,actual);
    }

    @Test
    public void getAllToDos() {
        List<ToDo> expected = List.of(
            ToDo.builder().id(null).description("test").status(Status.OPEN).build(),
            ToDo.builder().id(null).description("test").status(Status.OPEN).build()
        );

        when(mockToDoRepo.findAll()).thenReturn(expected);
        List<ToDo> actual = toDoService.getAllToDos();
        verify(mockToDoRepo).findAll();
        assertEquals(expected,actual);
    }

    @Test
    public void getToDoDetails_validId() throws NoSuchTaskException {
        ToDo expected = ToDo.builder()
                .id("12")
                .description("test")
                .status(Status.OPEN)
                .build();

        when(mockToDoRepo.findById(expected.id())).thenReturn(Optional.of(expected));
        Optional<ToDo> actual = toDoService.getToDoById(expected.id());
        verify(mockToDoRepo).findById(expected.id());
        assertEquals(Optional.of(expected),actual);
    }

    @Test
    public void getToDoDetails_invalidId() {
        when(mockToDoRepo.findById("12")).thenThrow(NoSuchTaskException.class);
        assertThrows(NoSuchTaskException.class, () -> toDoService.getToDoById("12"));
        verify(mockToDoRepo).findById("12");
    }
}