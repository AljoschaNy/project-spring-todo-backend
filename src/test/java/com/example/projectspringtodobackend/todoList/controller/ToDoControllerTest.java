package com.example.projectspringtodobackend.todoList.controller;

import com.example.projectspringtodobackend.todoList.exception.NoSuchTaskException;
import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.model.ToDoUpdate;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
    @DirtiesContext
    void getAllToDos_noToDos_returnEmptyList() throws Exception {
        mockMvc.perform(get(baseUri))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
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
    @DirtiesContext
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
        String toDoListAsJSON = objectMapper.writeValueAsString(toDoList);

        mockMvc.perform(get(baseUri))
                .andExpect(status().isOk())
                .andExpect(content().json(toDoListAsJSON));
    }

    @Test
    @DirtiesContext
    void getToDoById_validId_returnToDo() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        String toDoAsJSON = objectMapper.writeValueAsString(toDo);
        toDoRepo.save(toDo);
        mockMvc.perform(get(baseUri + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toDoAsJSON));
    }

    @Test
    @DirtiesContext
    void getToDoById_invalidId_throwException() {
        assertThrows(ServletException.class, () -> mockMvc.perform(get(baseUri+ "/1")));
    }

    @Test
    @DirtiesContext
    void addToDo() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        String toDoAsJSON = objectMapper.writeValueAsString(toDo);
        mockMvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON).content(toDoAsJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(toDoAsJSON));
    }

    @Test
    @DirtiesContext
    void updateToDo() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        ToDoUpdate update = ToDoUpdate.builder()
                .description("testtest")
                .status(Status.IN_PROGRESS)
                .build();
        ToDo expected = ToDo.builder()
                .id("1")
                .description("testtest")
                .status(Status.IN_PROGRESS)
                .build();
        String updateAsJson = objectMapper.writeValueAsString(update);
        String expectedAsJSON = objectMapper.writeValueAsString(expected);
        toDoRepo.save(toDo);

        mockMvc.perform(put(baseUri + "/" + expected.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateAsJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedAsJSON));
    }

    @Test
    @DirtiesContext
    void deleteToDo() throws Exception {
        ToDo toDo = ToDo.builder()
                .id("1")
                .description("test")
                .status(Status.OPEN)
                .build();
        toDoRepo.save(toDo);
        List<ToDo> expectedList = List.of();
        String expectedListAsJson = objectMapper.writeValueAsString(expectedList);

        mockMvc.perform(delete(baseUri + "/" + toDo.id()))
                .andExpect(status().isOk());
        mockMvc.perform((get(baseUri)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedListAsJson));
    }
}
