package com.example.projectspringtodobackend.todoList.controller;

import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepo toDoRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUri = "/api/todo";

    @Test
    void getAllToDos_noToDos_returnEmptyList() throws Exception {
        mockMvc.perform(get(baseUri))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllToDos_withOneToDo_returnToDoListOf1() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        List<ToDo> toDoList = List.of(toDo);
        toDoRepo.save(toDo);
        String toDoListAsJson = objectMapper.writeValueAsString(toDoList);

        mockMvc.perform(get(baseUri))
                .andExpect(status().isOk())
                .andExpect(content().json(toDoListAsJson));
    }

    @Test
    void getAllToDos_withTwoToDos_returnToDoListOf2() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        ToDo toDo2 = ToDo.builder()
                .id("2")
                .description("test")
                .status(Status.OPEN)
                .build();
        List<ToDo> toDoList = List.of(toDo,toDo2);
        toDoRepo.save(toDo);
        toDoRepo.save(toDo2);
        String toDoListAsJson = objectMapper.writeValueAsString(toDoList);

        mockMvc.perform(get(baseUri))
                .andExpect(status().isOk())
                .andExpect(content().json(toDoListAsJson));
    }

    @Test
    void getToDoById() {
    }

    @Test
    void addToDo() {
    }

    @Test
    void updateToDo() {
    }

    @Test
    void deleteToDo() {
    }
}