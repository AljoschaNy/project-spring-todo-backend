package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.exception.NoSuchTaskException;
import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.model.ToDoUpdate;
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
    public void getToDoById_validId() throws NoSuchTaskException {
        ToDo expected = ToDo.builder()
                .id("12")
                .description("test")
                .status(Status.OPEN)
                .build();

        when(mockToDoRepo.findById(expected.id())).thenReturn(Optional.of(expected));
        ToDo actual = toDoService.getToDoById(expected.id());
        verify(mockToDoRepo).findById(expected.id());
        assertEquals(Optional.of(expected).get(),actual);
    }

    @Test
    public void getToDoById_invalidId() {
        when(mockToDoRepo.findById("12")).thenThrow(NoSuchTaskException.class);
        assertThrows(NoSuchTaskException.class, () -> toDoService.getToDoById("12"));
        verify(mockToDoRepo).findById("12");
    }

    @Test
    public void updateToDo() {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        ToDoUpdate toDoUpdate = ToDoUpdate.builder()
                .description("TestTest")
                .status(Status.IN_PROGRESS)
                .build();
        ToDo expect = ToDo.builder()
                .id(toDo.id())
                .description(toDoUpdate.description())
                .status(toDoUpdate.status())
                .build();

        when(mockToDoRepo.findById(toDo.id())).thenReturn(Optional.of(toDo));
        when(mockToDoRepo.save(any(ToDo.class))).thenReturn(expect);

        ToDo actual = toDoService.updateToDo(toDo.id(),toDoUpdate);
        verify(mockToDoRepo).findById(toDo.id());
        verify(mockToDoRepo).save(expect);

        assertEquals(expect,actual);
    }

    @Test
    public void deleteToDo() {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        when(mockToDoRepo.findById("1")).thenReturn(Optional.of(toDo));

        toDoService.deleteToDo("1");
        verify(mockToDoRepo).findById("1");
        verify(mockToDoRepo).delete(toDo);
    }
}
