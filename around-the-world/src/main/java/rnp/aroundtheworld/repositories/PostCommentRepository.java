package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rnp.aroundtheworld.entities.PostComment;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment,Integer> {
    List<PostComment> findAllByPostId(int id);
}