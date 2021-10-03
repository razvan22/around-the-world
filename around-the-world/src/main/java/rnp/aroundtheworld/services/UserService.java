package rnp.aroundtheworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.repositories.UserRepository;
import rnp.aroundtheworld.services.Iservices.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return null;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public void saveAllUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
