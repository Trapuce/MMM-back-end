package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComapnyRepository extends JpaRepository<Company , Long> {
}
