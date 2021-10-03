package rnp.aroundtheworld.services.Iservices;

import rnp.aroundtheworld.entities.User;

import java.util.List;

public interface IUserService {
    User findByEmail(String email);
    void saveAllUsers(List<User> users);
    void deleteAll();
}
