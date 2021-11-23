package com.qalens.todo.model;

import java.util.List;

public class Todo {

    private long id;
    private String name;
    private List<Long> tags;
    private TodoStatus status;

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Todo(long id, String name, List<Long> tags){
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.status = TodoStatus.Active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
