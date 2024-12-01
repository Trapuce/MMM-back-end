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

//    @Query("SELECT a.resource FROM Availability a " +
//            "WHERE (a.startTime IS NULL OR a.endTime IS NULL " +
//            "      OR (a.startTime <= :endDate AND a.endTime >= :startDate) " +
//            "      OR a.startTime >= CURRENT_TIMESTAMP)")
//    List<Resources> findAvailableResources(Date startDate, Date endDate);

    @Query("SELECT a.resource FROM Availability a " +
            "WHERE ( " +
            /* Scénario 1: Ressources toujours disponibles */
            "(a.startTime IS NULL AND a.endTime IS NULL) " +
            "OR " +
            /* Scénario 2: Période définie qui ne chevauche pas la période demandée */
            "(a.startTime IS NOT NULL AND a.endTime IS NOT NULL " +
            "AND (a.endTime < :startDate OR a.startTime > :endDate)) " +
            "OR " +
            /* Scénario 3: Date de début définie mais pas de fin */
            "(a.startTime IS NOT NULL AND a.endTime IS NULL " +
            "AND a.startTime <= :endDate) " +
            ")")
    List<Resources> findAvailableResources(Date startDate, Date endDate);


    @Query("SELECT a FROM Availability a " +
            "WHERE a.resource = :resource " +
            "AND a.endTime >= :startDate " +
            "AND a.startTime <= :endDate " +
            "AND (a.startTime IS NULL OR a.endTime IS NULL OR a.startTime >= CURRENT_TIMESTAMP)")
    List<Availability> findConflictingAvailabilities(Resources resource, Date startDate, Date endDate);

    List<Availability> findByResourceId(Long id);

    List<Availability> findByResource(Resources resource);
}

