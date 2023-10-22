package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.exception.NoSuchTaskException;
import com.example.projectspringtodobackend.todoList.model.Status;
import com.example.projectspringtodobackend.todoList.model.ToDo;import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepo toDoRepo;

    public ToDo addToDo(ToDo toDo) {
        return toDoRepo.save(toDo);
    }

    public List<ToDo> getAllToDos() {
        return toDoRepo.findAll();
    }

    public Optional<ToDo> getToDoById(String id) throws NoSuchTaskException {
        return Optional.of(toDoRepo.findById(id)).orElseThrow(NoSuchTaskException::new);
    }

    public ToDo updateToDo(String id, String description, Status status) {
        ToDo legacy = getToDoById(id).orElseThrow();
        ToDo newToDo = new ToDo(legacy.id(), description, status);
        toDoRepo.delete(legacy);
        return toDoRepo.save(newToDo);
        //return new ToDoUpdate(newToDo.description(), newToDo.status());
    }
}
