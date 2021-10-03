package rnp.aroundtheworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.services.UserService;

import java.util.List;


@RestController()
@RequestMapping("api/v1/user/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
       return userService.findAll();
    }

    @PostMapping("/new")
    public User addNewUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/user/email/{email}")
    public User changeUserEmail(@PathVariable String email){
        return userService.findByEmail(email);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id ){
        userService.deleteUser(id);
    }

}