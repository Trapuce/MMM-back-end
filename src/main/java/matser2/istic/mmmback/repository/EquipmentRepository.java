package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment , Long> {
}
