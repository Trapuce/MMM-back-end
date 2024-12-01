package matser2.istic.mmmback.controllers;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import matser2.istic.mmmback.DTO.*;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/worksite")
public class WorkSiteController {

    @Autowired
    private WorkSiteService workSiteService;

    @PostMapping("/create")
    public ResponseEntity<?> addWorkSite(@RequestBody @Valid WorksitePostDto worksiteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            if (worksiteDto == null || worksiteDto.getCustomer() == null) {
                return ResponseEntity.badRequest().body("Worksite or customer information is missing.");
            }

            WorksitePostDto createdWorksiteDTO = workSiteService.createWorkSite(worksiteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorksiteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorksiteGetDto> getWorkSite(@PathVariable Long id) {
        try {
            WorksiteGetDto worksiteDto = workSiteService.getWorkSite(id);
            if (worksiteDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(worksiteDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getWorkSites() {
        try {
            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorkSites();

            if (worksitesDTO == null || worksitesDTO.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("code", "NO_CONTENT");
                errorResponse.put("message", "Aucun site de travail trouvé");
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(errorResponse);
            }

            return ResponseEntity.ok(worksitesDTO);
        } catch (DataAccessException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("code", "DATABASE_ERROR");
            errorResponse.put("message", "Erreur d'accès à la base de données");
            errorResponse.put("details", e.getMessage());


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("code", "UNEXPECTED_ERROR");
            errorResponse.put("message", "Erreur lors de la récupération des sites");
            errorResponse.put("details", e.getMessage());


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PostMapping("/{worksiteId}/resources")
    public ResponseEntity<WorksiteGetDto> addResourcesToWorksite(
            @PathVariable Long worksiteId,
            @RequestBody List<Long> resourceIds) {
        try {
            WorksiteGetDto updatedWorksiteDTO = workSiteService.addResourcesToWorksite(worksiteId, resourceIds);
            if (updatedWorksiteDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(updatedWorksiteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<WorksiteAllDto> updateWorksite(
            @PathVariable Long id,
            @RequestBody WorksiteAllDto worksiteAllDto) {
        try {
            WorksiteAllDto updatedWorksiteDto = workSiteService.updateWorksite(id, worksiteAllDto);
            if (updatedWorksiteDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(updatedWorksiteDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateWorksiteStatus(@PathVariable Long id, @RequestBody String newStatus) {
        try {
            newStatus = newStatus.replace("\"", "");
            WorksiteStatus status = WorksiteStatus.valueOf(newStatus.toUpperCase());
            workSiteService.updateWorksiteStatus(id, status);

            return ResponseEntity.ok("Worksite status updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid status value: " + newStatus);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Worksite not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/{id}/anomalies")
    public ResponseEntity<AnomalyDto> addAnomaly(@PathVariable Long id, @RequestBody AnomalyDto anomalyDto) {
        try {
            AnomalyDto addedAnomaly = workSiteService.addAnomalyToWorksite(id, anomalyDto);
            if (addedAnomaly == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(addedAnomaly);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/{id}/anomalies")
    public ResponseEntity<List<AnomalyDto>> getAnomalies(@PathVariable Long id) {
        try {
            List<AnomalyDto> anomalies = workSiteService.getWorkSite(id).getAnomalies();
            if (anomalies == null || anomalies.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(anomalies);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorksite(@PathVariable Long id) {
        try {
            workSiteService.deleteWorksite(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @PostMapping("/{id}/photos")
    public ResponseEntity<String> addPhotosToWorksite(
            @PathVariable Long id,
            @RequestBody List<PhotoDto> photos) {
        try {
            workSiteService.addPhotosToWorksite(id, photos);
            return ResponseEntity.ok("Photos added successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worksite not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
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
        try {
            workSiteService.removeResourceFromWorksite(worksiteId, resourceId);
            return ResponseEntity.ok("Resource removed successfully from the worksite.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while removing the resource: " + e.getMessage());
        }
    }


    @PutMapping("/{worksiteId}/anomalies/{anomalyId}")
    public ResponseEntity<AnomalyDto> updateAnomaly(
            @PathVariable Long worksiteId,
            @PathVariable Long anomalyId,
            @RequestBody AnomalyDto anomalyDto) {
        try {
            AnomalyDto updatedAnomaly = workSiteService.updateAnomalyInWorksite(worksiteId, anomalyId, anomalyDto);
            return ResponseEntity.ok(updatedAnomaly);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/resource/{resourceId}/worksites")
    public ResponseEntity<?> getWorksitesByResourceId(@PathVariable Long resourceId) {
        try {
            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorksitesByResourceId(resourceId);

            if (worksitesDTO.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(Map.of(
                                "code", "NO_WORKSITES",
                                "message", "Aucun site de travail trouvé pour cette ressource"
                        ));
            }

            return ResponseEntity.ok(worksitesDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "code", "RESOURCE_NOT_FOUND",
                            "message", "Ressource non trouvée",
                            "details", e.getMessage()
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "code", "UNEXPECTED_ERROR",
                            "message", "Erreur lors de la récupération des sites de travail",
                            "details", e.getMessage()
                    ));
        }
    }

}
