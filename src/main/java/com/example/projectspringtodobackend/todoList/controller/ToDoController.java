package com.example.projectspringtodobackend.todoList.controller;

import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.model.ToDoUpdate;
import com.example.projectspringtodobackend.todoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ToDo getToDoById(@PathVariable String id) {
        return toDoService.getToDoById(id);
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo) {
        return toDoService.addToDo(toDo);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable String id, @RequestBody ToDoUpdate toDoUpdate) {
        return toDoService.updateToDo(id, toDoUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }
}
