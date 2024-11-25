package matser2.istic.mmmback.repository;

import matser2.istic.mmmback.models.Availability;
import matser2.istic.mmmback.models.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    @Query("SELECT a.resource FROM Availability a WHERE a.isAvailable = true AND a.startTime <= :startDate AND a.endTime >= :endDate")
    List<Resources> findAvailableResources(Date startDate, Date endDate);



    @Query("SELECT a FROM Availability a WHERE a.resource = :resource AND a.endTime >= :startDate AND a.startTime <= :endDate")
    List<Availability> findConflictingAvailabilities(Resources resource, Date startDate, Date endDate);

}
