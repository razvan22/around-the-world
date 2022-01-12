package rnp.aroundtheworld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.repositories.PostRepository;
import rnp.aroundtheworld.repositories.UserRepository;

@Service
public class DatabaseCommandsRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {
//        postRepository.deleteAll();
//        userRepository.deleteAll();

//        User admin = new User(
//                "Jon",
//                "Smith",
//                "jon@gmail.com",
//                new BCryptPasswordEncoder().encode("admin"),
//                "ADMIN"
//
//        );

        User user = new User(
                "Razvan-Petru",
                "Nechifor",
                "razvan@gmail.com",
                 new BCryptPasswordEncoder().encode("password22"),
                "USER"

        );

//        userRepository.save(user);

//        List<User> defaultUsers = Arrays.asList(admin,user);
//        userRepository.saveAll(defaultUsers);
    }
}