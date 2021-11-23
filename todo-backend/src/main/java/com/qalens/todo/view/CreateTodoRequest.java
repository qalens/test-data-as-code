package com.qalens.todo.view;

import java.util.List;
import java.util.Optional;

public class CreateTodoRequest {
    private String name;
    private List<Long> tags;
    private Optional<Boolean> isDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }
}
