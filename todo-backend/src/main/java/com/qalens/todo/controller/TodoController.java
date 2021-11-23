package com.qalens.todo.controller;

import com.qalens.todo.model.TodoStatus;
import com.qalens.todo.view.CreateTodoRequest;
import com.qalens.todo.view.PatchTodoRequest;
import com.qalens.todo.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    private List<Todo> myTodoList = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public TodoController(){}

    private Todo newTodoForNameAndTags(String name,List<Long> tags) {
        return new Todo(counter.incrementAndGet(), name,tags.stream().distinct().collect(Collectors.toList()));
    }

    @GetMapping(value = "/todo/")
    public ResponseEntity index() {
        return ResponseEntity.ok(myTodoList);
    }

    @GetMapping(value = "/todo/active/")
    public ResponseEntity active() {
        return ResponseEntity.ok(myTodoList.stream().filter(todo-> todo.getStatus() == TodoStatus.Active).collect(Collectors.toList()));
    }

    @GetMapping(value = "/todo/{id}/")
    public ResponseEntity getTodo(@PathVariable Long id) {
        Todo itemToReturn = null;
        for(Todo bucket : myTodoList){
            if(bucket.getId() == id)
                itemToReturn = bucket;
        }

        if(itemToReturn == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(itemToReturn);
        }

    }

    @PostMapping(value = "/todo/")
    public ResponseEntity create(@RequestBody CreateTodoRequest ctr) {
        Todo newTodo = newTodoForNameAndTags(ctr.getName(),ctr.getTags());
        myTodoList.add(newTodo);
        return ResponseEntity.ok(newTodo);
    }

    @PatchMapping(value = "/todo/{id}/")
    public ResponseEntity update(@PathVariable Long id,@RequestBody PatchTodoRequest ctr) {
        AtomicReference<Todo> foundTodo=new AtomicReference<>();
        myTodoList.forEach(todo ->  {
            if(todo.getId() == id){
                foundTodo.set(todo);
                if (ctr.getName()!=null)
                    todo.setName(ctr.getName());
                if (ctr.getTags()!=null)
                    todo.setTags(ctr.getTags());
                if (ctr.getStatus()!=null){
                    todo.setStatus(ctr.getStatus());
                }
            }
        });
        if (foundTodo.get() !=null){
            return ResponseEntity.ok(foundTodo.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/todo/{id}/")
    public ResponseEntity delete(@PathVariable Long id) {
        Todo itemToRemove = null;
        for(Todo todo : myTodoList){
            if(todo.getId() == id)
                itemToRemove = todo;
        }

        myTodoList.remove(itemToRemove);
        return ResponseEntity.ok("SUCCESS");
    }
}
