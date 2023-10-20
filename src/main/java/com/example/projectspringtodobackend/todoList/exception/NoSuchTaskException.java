package com.example.projectspringtodobackend.todoList.exception;

public class NoSuchTaskException extends RuntimeException{
    public NoSuchTaskException(String errorMessage) {
        super(errorMessage);
    }

    public NoSuchTaskException() {
        super("The task is unknown");
    }
}
