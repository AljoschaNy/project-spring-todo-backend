package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepo toDoRepo;


    public ToDo addToDo(String task) {
        ToDo toDo = ToDo.builder()
                .id(null)
                .description(task)
                .status(Status.OPEN)
                .build();

        return toDoRepo.save(toDo);
    }
}
