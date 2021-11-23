package com.qalens.tagged;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController("")
public class TagController {

    private List<Tag> myTagList = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public TagController(){
        myTagList.add(createNeTagWithName("Personal"));
        myTagList.add(createNeTagWithName("Office"));
        myTagList.add(createNeTagWithName("Community"));
        myTagList.add(createNeTagWithName("Family"));
    }

    private Tag createNeTagWithName(String name) {
        return new Tag(counter.incrementAndGet(), name);
    }

    @GetMapping(value = "/tag")
    public ResponseEntity index() {
        return ResponseEntity.ok(myTagList);
    }

    @GetMapping(value = "/tag/{id}")
    public ResponseEntity getTodo(@PathVariable Long id) {
        Tag itemToReturn = null;
        for(Tag bucket : myTagList){
            if(bucket.getId() == id)
                itemToReturn = bucket;
        }

        if(itemToReturn == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(itemToReturn);
        }

    }

    @PostMapping(value = "/tag")
    public ResponseEntity createTag(@RequestBody CreateUpdateTagRequest ctr) {
        Tag newTag= createNeTagWithName(ctr.getName());
        myTagList.add(newTag);
        return ResponseEntity.ok(newTag);
    }

    @PutMapping(value = "/tag/{id}")
    public ResponseEntity updateTag(@PathVariable Long id, @RequestBody CreateUpdateTagRequest utr) {
        myTagList.forEach(tagList ->  {
            if(tagList.getId() == id){
                tagList.setName(utr.getName());
            }
        });
        return ResponseEntity.ok(myTagList);
    }

    @DeleteMapping(value = "/tag/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Tag itemToRemove = null;
        for(Tag bucket : myTagList){
            if(bucket.getId() == id)
                itemToRemove = bucket;
        }

        myTagList.remove(itemToRemove);
        return ResponseEntity.ok(myTagList);
    }
}
