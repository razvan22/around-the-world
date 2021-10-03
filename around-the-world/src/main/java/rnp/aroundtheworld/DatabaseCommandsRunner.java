package rnp.aroundtheworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.services.UserService;

@Service
public class DatabaseCommandsRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        userService.deleteAll();
//
//        User admin = new User(
//                "Jon",
//                "Smith",
//                "jon@gamil.com",
//                "admin",
//                "ADMIN",
//                "/users/all"
//        );
//
//        User user = new User(
//                "Razvan-Petru",
//                "Nechifor",
//                "razvan@gmail.com",
//                "password",
//                "USER",
//                ""
//        );
//
//        List<User> defaultUsers = Arrays.asList(admin,user);
//        userService.saveAllUsers(defaultUsers);
    }
}
