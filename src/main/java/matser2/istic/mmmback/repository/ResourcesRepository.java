package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    @Query(value = "SELECT * FROM resources WHERE \"type-resources\" = 'EMPLOYEE' AND email = :email", nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(@Param("email") String email);



    @Query(value = "SELECT * FROM resources WHERE \"type-resources\" = 'VEHICLE'", nativeQuery = true)
    List<Vehicle> findAllVehicles();

    @Query(value = "SELECT * FROM resources WHERE \"type-resources\" = 'EQUIPMENT'", nativeQuery = true)
    List<Equipment> findAllEquipments();

    @Query(value = "SELECT * FROM resources WHERE \"type-resources\" = 'EMPLOYEE'", nativeQuery = true)
    List<Employee> findAllEmployees();
}
