package com.example.projectspringtodobackend.todoList.controller;

import com.example.projectspringtodobackend.todoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class ToDoController {
    private final ToDoService toDoService;
}
