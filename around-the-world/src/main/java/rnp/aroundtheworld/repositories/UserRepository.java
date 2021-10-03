package rnp.aroundtheworld.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnp.aroundtheworld.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
