package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnp.aroundtheworld.entities.PostComment;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Integer> {
    List<PostComment> findAllByPostId(int id);
}