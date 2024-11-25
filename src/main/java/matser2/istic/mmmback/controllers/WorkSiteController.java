package matser2.istic.mmmback.controllers;


import jakarta.persistence.EntityNotFoundException;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.WorksiteAllDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.DTO.WorksitePostDto;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.WorksiteStatus;
import matser2.istic.mmmback.service.WorkSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/worksite")
public class WorkSiteController {

    @Autowired
    private WorkSiteService workSiteService;

    @PostMapping
    public ResponseEntity<WorksitePostDto> addWorkSite(@RequestBody WorksitePostDto worksiteDto) {
        if (worksiteDto == null || worksiteDto.getCustomer() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            WorksitePostDto createdWorksiteDTO = workSiteService.createWorkSite(worksiteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorksiteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorksiteGetDto> getWorkSite(@PathVariable Long id) {
        WorksiteGetDto worksiteDto = workSiteService.getWorkSite(id);
        if (worksiteDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worksiteDto);
    }

    @GetMapping
    public ResponseEntity<List<WorksiteGetDto>> getWorkSites() {
        List<WorksiteGetDto> worksitesDTO = workSiteService.getWorkSites();
        if (worksitesDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(worksitesDTO);
    }

    @PostMapping("/{worksiteId}/resources/{resourceId}")
    public ResponseEntity<WorksiteGetDto> addResourceToWorksite(
            @PathVariable Long worksiteId,
            @PathVariable Long resourceId) {
        WorksiteGetDto updatedWorksiteDTO = workSiteService.addResourceToWorksite(worksiteId, resourceId);
        return ResponseEntity.ok(updatedWorksiteDTO);
    }
    @PutMapping
    public ResponseEntity<WorksiteGetDto> updateWorksite( @RequestBody WorksitePostDto worksiteDto) {
        WorksiteGetDto updatedWorksiteDto = workSiteService.updateWorksite(worksiteDto);
        return ResponseEntity.ok(updatedWorksiteDto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateWorksiteStatus(@PathVariable Long id, @RequestBody String newStatus) {
        try {
            newStatus = newStatus.replace("\"", "");
            System.out.println(newStatus);
            WorksiteStatus status = WorksiteStatus.valueOf(newStatus.toUpperCase());
            System.out.println("status : " +status.toString());
            workSiteService.updateWorksiteStatus(id, status);

            return ResponseEntity.ok("Worksite status updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value: " + newStatus);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/anomalies")
    public AnomalyDto addAnomaly(@PathVariable Long id, @RequestBody AnomalyDto anomalyDto) {
        return workSiteService.addAnomalyToWorksite(id, anomalyDto);
    }

    @GetMapping("/{id}/anomalies")
    public List<AnomalyDto> getAnomalies(@PathVariable Long id) {
        return workSiteService.getWorkSite(id).getAnomalies();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorksite(@PathVariable Long id) {
        workSiteService.deleteWorksite(id);
        return ResponseEntity.noContent().build();
    }
}
