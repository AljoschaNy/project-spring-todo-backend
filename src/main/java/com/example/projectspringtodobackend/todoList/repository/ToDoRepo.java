package com.example.projectspringtodobackend.todoList.repository;

import com.example.projectspringtodobackend.todoList.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends MongoRepository<ToDo, String> {
}
