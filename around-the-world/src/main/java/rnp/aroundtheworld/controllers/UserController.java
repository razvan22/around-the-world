package rnp.aroundtheworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.services.Iservices.UserService;


@RestController()
@RequestMapping("api/v1/user/")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public void addNewUser(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/email/{email}")
    public User changeUserEmail(@PathVariable String email){
        return userService.findByUsername(email);
    }

    @GetMapping
    public ResponseEntity<String> userTest(){
        return ResponseEntity.ok().body("User Test endpoint !");
    }

}