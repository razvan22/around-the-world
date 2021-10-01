package rnp.aroundtheworld.services.Iservices;

import rnp.aroundtheworld.entities.User;

public interface IUserService {
    User findByEmail(String email);
}
