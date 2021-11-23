package com.qalens.todo.view;

import com.qalens.todo.model.TodoStatus;

import java.util.List;
public class PatchTodoRequest {
    private String name;
    private List<Long> tags;
    private TodoStatus status;

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

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
