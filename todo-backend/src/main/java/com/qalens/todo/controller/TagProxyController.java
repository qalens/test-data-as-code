package com.qalens.todo.controller;

import com.qalens.todo.view.PatchTodoRequest;
import com.qalens.todo.model.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TagProxyController {
    public TagProxyController(){}

    @Value("${tagger.api.url}")
    private String baseURL;

    @Value("${tagger.api.token}")
    private String TOKEN;

    @GetMapping(value = "/tag/")
    public ResponseEntity index() {
        return new RestTemplate().exchange(
                RequestEntity.get(uriFrom(baseURL)).header("TOKEN",TOKEN).build(),
                new ParameterizedTypeReference<List<Tag>>(){});
    }

    @GetMapping(value = "/tag/{id}/")
    public ResponseEntity getTodo(@PathVariable Long id) {
        return new RestTemplate().exchange(
                RequestEntity.get(uriFrom(baseURL+"/"+id)).header("TOKEN",TOKEN).build(),
                Tag.class);
    }
    @PostMapping(value = "/tag/")
    public ResponseEntity create(@RequestBody PatchTodoRequest ctr) {
        return new RestTemplate().exchange(RequestEntity.post(uriFrom(baseURL)).header("TOKEN",TOKEN).body(ctr),Tag.class);
    }

    @PutMapping(value = "/tag/{id}/")
    public ResponseEntity update(@PathVariable Long id,@RequestBody PatchTodoRequest ctr) {

        return new RestTemplate().exchange(RequestEntity.put(uriFrom(baseURL+"/"+id)).header("TOKEN",TOKEN).body(ctr),Tag.class);
    }

    @DeleteMapping(value = "/tag/{id}/")
    public ResponseEntity delete(@PathVariable Long id) {
        return new RestTemplate().exchange(RequestEntity.delete(uriFrom(baseURL+"/"+id)).header("TOKEN",TOKEN).build(),Tag.class);
    }
    public URI uriFrom(String url){
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Incorrect URL "+url);
        }
    }
}
