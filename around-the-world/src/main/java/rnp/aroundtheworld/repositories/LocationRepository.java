package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnp.aroundtheworld.entities.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
}
