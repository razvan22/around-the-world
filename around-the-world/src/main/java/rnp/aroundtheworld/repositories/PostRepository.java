package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rnp.aroundtheworld.entities.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
