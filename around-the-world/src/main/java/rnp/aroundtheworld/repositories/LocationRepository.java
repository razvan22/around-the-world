package rnp.aroundtheworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rnp.aroundtheworld.entities.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
