package rnp.aroundtheworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rnp.aroundtheworld.entities.Post;
import rnp.aroundtheworld.entities.PostComment;
import rnp.aroundtheworld.entities.PostRating;
import rnp.aroundtheworld.repositories.PostRepository;
import rnp.aroundtheworld.services.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/post")
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;


    @GetMapping
    private List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("/id={id}")
    public Optional<Post> getById(@PathVariable String id) {
        return postRepository.findById(id);
    }

    @GetMapping("/continent/name={name}")
    public List<Post> getPostsByContinent(@PathVariable("name") String continent) {
        return postService.findAllByContinentName(continent);
    }

    @GetMapping("/title={name}")
    public List<Post> findAllWereTitleStartsWith(@PathVariable("name") String title) {
        return postService.findAllWereTitleStartsWith(title);
    }

    @PostMapping("/new")
    private ResponseEntity savePost(@RequestParam("imageFile")  MultipartFile[] imageFile, @RequestParam("post") String post , HttpServletRequest request){
        return  postService.savePost(imageFile, post, request);
    }

    @PostMapping("/comment")
    private ResponseEntity postComment(@RequestBody PostComment comment,  HttpServletRequest request){
        return postService.saveComment(comment, request);
    }

    @PostMapping("/rating")
    private ResponseEntity postRating(@RequestBody PostRating rating, HttpServletRequest request){
        return postService.addNewRating(rating, request);
    }
}