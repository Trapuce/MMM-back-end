package matser2.istic.mmmback.controllers;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.exceptions.WorksiteNotFoundException;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.WorksiteStatus;
import matser2.istic.mmmback.service.WorkSiteService;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/worksite")
public class WorkSiteController {

    @Autowired
    private WorkSiteService workSiteService;

    @PostMapping("/create")
    public ResponseEntity<?> addWorkSite(@RequestBody @Valid WorksitePostDto worksiteDto) {


            WorksitePostDto createdWorksiteDTO = workSiteService.createWorkSite(worksiteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorksiteDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<WorksiteGetDto> getWorkSite(@PathVariable Long id)  {


            WorksiteGetDto worksiteDto = workSiteService.getWorkSite(id);

            return ResponseEntity.ok(worksiteDto);



    }

    @GetMapping
    public ResponseEntity<?> getWorkSites() {

            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorkSites();


            return ResponseEntity.ok(worksitesDTO);

    }

    @PostMapping("/{worksiteId}/resources")
    public ResponseEntity<WorksiteGetDto> addResourcesToWorksite(
            @PathVariable Long worksiteId,
            @RequestBody List<Long> resourceIds) {

            WorksiteGetDto updatedWorksiteDTO = workSiteService.addResourcesToWorksite(worksiteId, resourceIds);

            return ResponseEntity.ok(updatedWorksiteDTO);

    }
    @PutMapping("/{id}")
    public ResponseEntity<WorksiteAllDto> updateWorksite(
            @PathVariable Long id,
            @RequestBody WorksiteAllDto worksiteAllDto) {

            WorksiteAllDto updatedWorksiteDto = workSiteService.updateWorksite(id, worksiteAllDto);

            return ResponseEntity.ok(updatedWorksiteDto);

    }



    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateWorksiteStatus(
            @PathVariable Long id,
            @RequestBody String newStatus
    ) {

            newStatus = newStatus.trim().replace("\"", "");

            WorksiteStatus status = WorksiteStatus.valueOf(newStatus.toUpperCase());

            WorksiteGetDto updatedWorksite = workSiteService.updateWorksiteStatus(id, status);

            return ResponseEntity.ok(Map.of(
                    "code", "STATUS_UPDATED",
                    "message", "Statut du chantier mis à jour avec succès",
                    "worksite", updatedWorksite
            ));

    }


    @PostMapping("/{id}/anomalies")
    public ResponseEntity<AnomalyDto> addAnomaly(@PathVariable Long id, @RequestBody AnomalyDto anomalyDto) {

            AnomalyDto addedAnomaly = workSiteService.addAnomalyToWorksite(id, anomalyDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(addedAnomaly);

    }


    @GetMapping("/{id}/anomalies")
    public ResponseEntity<List<AnomalyDto>> getAnomalies(@PathVariable Long id) {

            List<AnomalyDto> anomalies = workSiteService.getWorkSite(id).getAnomalies();

            return ResponseEntity.ok(anomalies);

    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorksite(@PathVariable Long id) {

            workSiteService.deleteWorksite(id);
            return ResponseEntity.noContent().build();

    }


    @PostMapping("/{id}/photos")
    public ResponseEntity<String> addPhotosToWorksite(
            @PathVariable Long id,
            @RequestBody List<PhotoDto> photos) {

            workSiteService.addPhotosToWorksite(id, photos);
            return ResponseEntity.ok("Photos added successfully.");

    }

    @GetMapping("/{worksiteId}/resources")
    public ResponseEntity<List<ResourcesSimpleDto>> getWorksiteResources(
            @PathVariable Long worksiteId) {
        List<ResourcesSimpleDto> resources = workSiteService.getWorksiteResources(worksiteId);
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{worksiteId}/resources/{resourceId}")
    public ResponseEntity<String> removeResourceFromWorksite(
            @PathVariable Long worksiteId,
            @PathVariable Long resourceId) {

            workSiteService.removeResourceFromWorksite(worksiteId, resourceId);
            return ResponseEntity.ok("Resource removed successfully from the worksite.");

    }


    @PutMapping("/{worksiteId}/anomalies/{anomalyId}")
    public ResponseEntity<AnomalyDto> updateAnomaly(
            @PathVariable Long worksiteId,
            @PathVariable Long anomalyId,
            @RequestBody AnomalyDto anomalyDto) {

            AnomalyDto updatedAnomaly = workSiteService.updateAnomalyInWorksite(worksiteId, anomalyId, anomalyDto);
            return ResponseEntity.ok(updatedAnomaly);

    }


    @GetMapping("/resource/{resourceId}/worksites")
    public ResponseEntity<?> getWorksitesByResourceId(@PathVariable Long resourceId) {

            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorksitesByResourceId(resourceId);



            return ResponseEntity.ok(worksitesDTO);

    }

}
