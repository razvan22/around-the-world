package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnp.aroundtheworld.entities.PostImage;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, String> {
}
