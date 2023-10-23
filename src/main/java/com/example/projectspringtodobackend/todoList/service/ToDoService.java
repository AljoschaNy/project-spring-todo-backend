package com.example.projectspringtodobackend.todoList.service;

import com.example.projectspringtodobackend.todoList.exception.NoSuchTaskException;
import com.example.projectspringtodobackend.todoList.model.ToDo;
import com.example.projectspringtodobackend.todoList.model.ToDoUpdate;
import com.example.projectspringtodobackend.todoList.repository.ToDoRepo;
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

    public ToDo getToDoById(String id) throws NoSuchTaskException {
        Optional<ToDo> toDo = toDoRepo.findById(id);
        System.out.println(toDo);
        if(toDo.isPresent()) {
            return toDo.get();
        } else {
            throw new NoSuchTaskException();
        }
    }

    public ToDo updateToDo(String id, ToDoUpdate toDoUpdate) {
        ToDo legacy = getToDoById(id);
        ToDo newToDo = ToDo.builder()
                .id(legacy.id())
                .description(toDoUpdate.description())
                .status(toDoUpdate.status())
                .build();
        return addToDo(newToDo);
    }

    public void deleteToDo(String id) {
        toDoRepo.delete(getToDoById(id));
    }
}
