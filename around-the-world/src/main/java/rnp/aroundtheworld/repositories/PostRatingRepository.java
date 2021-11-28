package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnp.aroundtheworld.entities.PostRating;

@Repository
public interface PostRatingRepository extends JpaRepository<PostRating, Integer> {
}
