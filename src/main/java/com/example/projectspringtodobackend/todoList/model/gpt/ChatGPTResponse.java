package com.example.projectspringtodobackend.todoList.model.gpt;

import java.util.List;

public record ChatGPTResponse(
        List<ChatGPTChoice> choices
) {
    public String text() {
        return choices.get(0).message().content();
    }
}
