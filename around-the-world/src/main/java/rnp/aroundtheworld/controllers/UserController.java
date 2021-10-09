package rnp.aroundtheworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.services.Iservices.UserService;

import javax.servlet.http.HttpServletRequest;


@RestController()
@RequestMapping("api/v1/user/")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity saveNewUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public User getUser(HttpServletRequest request){
        if (userService.isUserAuthenticated(request)){
            String userName = userService.getUserNameFromJwtToken(request);
            return userService.findByUsername(userName);
        } else return null;
    }

}