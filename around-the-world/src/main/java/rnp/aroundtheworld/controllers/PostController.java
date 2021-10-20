package rnp.aroundtheworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnp.aroundtheworld.entities.Post;
import rnp.aroundtheworld.services.PostService;

import java.util.List;

@RestController()
@RequestMapping("api/v1/post")
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/new")
    private ResponseEntity savePost(@RequestBody Post post){
        postService.savePost(post);
        return null;
    }

    @GetMapping
    private List<Post> getAll(){
        return postService.getAll();
    }

}
