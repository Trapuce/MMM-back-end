package matser2.istic.mmmback.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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




    /**
     * Create a new worksite.
     *
     * @param worksiteDto the details of the worksite to create.
     * @return the created worksite.
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new worksite", description = "Creates a new worksite with the provided details.")
    @ApiResponse(responseCode = "201", description = "Worksite created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<?> addWorkSite(@RequestBody @Valid WorksitePostDto worksiteDto) {
            WorksitePostDto createdWorksiteDTO = workSiteService.createWorkSite(worksiteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorksiteDTO);
    }



    /**
     * Get a worksite by its ID.
     *
     * @param id the ID of the worksite.
     * @return the details of the worksite.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a worksite by ID", description = "Fetch the details of a worksite using its ID.")
    @ApiResponse(responseCode = "200", description = "Worksite fetched successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<WorksiteGetDto> getWorkSite(@PathVariable Long id)  {
            WorksiteGetDto worksiteDto = workSiteService.getWorkSite(id);
            return ResponseEntity.ok(worksiteDto);
    }


    /**
     * Get all worksites.
     *
     * @return a list of all worksites.
     */
    @GetMapping
    @Operation(summary = "Get all worksites", description = "Fetch all the worksites in the system.")
    @ApiResponse(responseCode = "200", description = "List of worksites fetched successfully")
    public ResponseEntity<?> getWorkSites() {
            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorkSites();
            return ResponseEntity.ok(worksitesDTO);
    }



    /**
     * Add resources to a worksite.
     *
     * @param worksiteId the ID of the worksite.
     * @param resourceIds a list of resource IDs to associate with the worksite.
     * @return the updated worksite details.
     */
    @PostMapping("/{worksiteId}/resources")
    @Operation(summary = "Add resources to a worksite", description = "Associate resources with a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Resources added to worksite successfully")
    @ApiResponse(responseCode = "404", description = "Worksite or resource(s) not found")
    public ResponseEntity<WorksiteGetDto> addResourcesToWorksite(
            @PathVariable Long worksiteId,
            @RequestBody List<Long> resourceIds) {
            WorksiteGetDto updatedWorksiteDTO = workSiteService.addResourcesToWorksite(worksiteId, resourceIds);
            return ResponseEntity.ok(updatedWorksiteDTO);
    }


    /**
     * Update a worksite's details.
     *
     * @param id the ID of the worksite to update.
     * @param worksiteAllDto the updated details of the worksite.
     * @return the updated worksite.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a worksite", description = "Update the details of a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Worksite updated successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<WorksiteAllDto> updateWorksite(
            @PathVariable Long id,
            @RequestBody WorksiteAllDto worksiteAllDto) {
            WorksiteAllDto updatedWorksiteDto = workSiteService.updateWorksite(id, worksiteAllDto);
            return ResponseEntity.ok(updatedWorksiteDto);
    }


    /**
     * Update the status of a worksite.
     *
     * @param id the ID of the worksite.
     * @param newStatus the new status of the worksite.
     * @return a response indicating the status update result.
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "Update a worksite's status", description = "Change the status of a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Worksite status updated successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    @ApiResponse(responseCode = "400", description = "Invalid status provided")
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



    /**
     * Add anomalies to a worksite.
     *
     * @param id the ID of the worksite.
     * @param anomalyDto the anomaly to add.
     * @return the added anomaly.
     */
    @PostMapping("/{id}/anomalies")
    @Operation(summary = "Add an anomaly to a worksite", description = "Register an anomaly for a specific worksite.")
    @ApiResponse(responseCode = "201", description = "Anomaly added successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<AnomalyDto> addAnomaly(@PathVariable Long id, @RequestBody AnomalyDto anomalyDto) {
            AnomalyDto addedAnomaly = workSiteService.addAnomalyToWorksite(id, anomalyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedAnomaly);
    }


    /**
     * Get all anomalies of a worksite.
     *
     * @param id the ID of the worksite.
     * @return a list of anomalies for the worksite.
     */
    @GetMapping("/{id}/anomalies")
    @Operation(summary = "Get anomalies of a worksite", description = "Fetch all anomalies associated with a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Anomalies fetched successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<List<AnomalyDto>> getAnomalies(@PathVariable Long id) {
            List<AnomalyDto> anomalies = workSiteService.getWorkSite(id).getAnomalies();
            return ResponseEntity.ok(anomalies);
    }



    /**
     * Delete a worksite by its ID.
     *
     * @param id the ID of the worksite to delete.
     * @return a no-content response.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a worksite", description = "Remove a specific worksite by its ID.")
    @ApiResponse(responseCode = "204", description = "Worksite deleted successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<Void> deleteWorksite(@PathVariable Long id) {
            workSiteService.deleteWorksite(id);
            return ResponseEntity.noContent().build();
    }



    /**
     * Add photos to a worksite.
     *
     * @param id the ID of the worksite.
     * @param photos the photos to add.
     * @return a message indicating the result.
     */
    @PostMapping("/{id}/photos")
    @Operation(summary = "Add photos to a worksite", description = "Attach one or more photos to a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Photos added successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<String> addPhotosToWorksite(
            @PathVariable Long id,
            @RequestBody List<PhotoDto> photos) {
            workSiteService.addPhotosToWorksite(id, photos);
            return ResponseEntity.ok("Photos added successfully.");
    }


    /**
     * Get all resources assigned to a worksite.
     *
     * @param worksiteId the ID of the worksite.
     * @return a list of resources assigned to the worksite.
     */
    @GetMapping("/{worksiteId}/resources")
    @Operation(summary = "Get worksite resources", description = "Fetch all resources assigned to a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Resources fetched successfully")
    @ApiResponse(responseCode = "404", description = "Worksite not found")
    public ResponseEntity<List<ResourcesSimpleDto>> getWorksiteResources(
            @PathVariable Long worksiteId) {
        List<ResourcesSimpleDto> resources = workSiteService.getWorksiteResources(worksiteId);
        return ResponseEntity.ok(resources);
    }


    /**
     * Remove a resource from a worksite.
     *
     * @param worksiteId the ID of the worksite.
     * @param resourceId the ID of the resource to remove.
     * @return a message indicating the result.
     */
    @DeleteMapping("/{worksiteId}/resources/{resourceId}")
    @Operation(summary = "Remove a resource from a worksite", description = "Detach a specific resource from a worksite.")
    @ApiResponse(responseCode = "200", description = "Resource removed successfully")
    @ApiResponse(responseCode = "404", description = "Worksite or resource not found")
    public ResponseEntity<String> removeResourceFromWorksite(
            @PathVariable Long worksiteId,
            @PathVariable Long resourceId) {
            workSiteService.removeResourceFromWorksite(worksiteId, resourceId);
            return ResponseEntity.ok("Resource removed successfully from the worksite.");
    }



    /**
     * Update an anomaly in a worksite.
     *
     * @param worksiteId the ID of the worksite.
     * @param anomalyId the ID of the anomaly to update.
     * @param anomalyDto the updated anomaly details.
     * @return the updated anomaly.
     */
    @PutMapping("/{worksiteId}/anomalies/{anomalyId}")
    @Operation(summary = "Update an anomaly in a worksite", description = "Update details of a specific anomaly in a worksite.")
    @ApiResponse(responseCode = "200", description = "Anomaly updated successfully")
    @ApiResponse(responseCode = "404", description = "Worksite or anomaly not found")
    public ResponseEntity<AnomalyDto> updateAnomaly(
            @PathVariable Long worksiteId,
            @PathVariable Long anomalyId,
            @RequestBody AnomalyDto anomalyDto) {
            AnomalyDto updatedAnomaly = workSiteService.updateAnomalyInWorksite(worksiteId, anomalyId, anomalyDto);
            return ResponseEntity.ok(updatedAnomaly);
    }



    /**
     * Get all worksites associated with a specific resource.
     *
     * @param resourceId the ID of the resource.
     * @return a list of worksites the resource is assigned to.
     */
    @GetMapping("/resource/{resourceId}/worksites")
    @Operation(summary = "Get worksites by resource", description = "Fetch all worksites associated with a specific resource.")
    @ApiResponse(responseCode = "200", description = "Worksites fetched successfully")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    public ResponseEntity<?> getWorksitesByResourceId(@PathVariable Long resourceId) {
            List<WorksiteGetDto> worksitesDTO = workSiteService.getWorksitesByResourceId(resourceId);
            return ResponseEntity.ok(worksitesDTO);
    }

}
