package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorksiteRepository extends JpaRepository<Worksite, Long> {
    List<Worksite> findByResources(Resources resource);
}
