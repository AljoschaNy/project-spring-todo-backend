package com.example.projectspringtodobackend.todoList.controller;

import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class ToDoController {
    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getToDoDetails(@PathVariable String id) {
        return toDoService.getToDoDetails(id);
    }

    @PostMapping
    public ToDo addToDo(@RequestBody String toDo) {
        return toDoService.addToDo(toDo);
    }
}
