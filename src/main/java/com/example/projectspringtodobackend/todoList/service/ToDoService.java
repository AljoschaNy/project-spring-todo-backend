package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepo toDoRepo;


    public ToDo addToDo(ToDo toDo) {
        return toDoRepo.save(toDo);
    }
}
