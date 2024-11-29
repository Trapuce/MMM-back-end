package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
