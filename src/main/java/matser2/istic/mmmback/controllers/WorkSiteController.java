package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.DTO.WorksiteAllDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.DTO.WorksitePostDto;
import matser2.istic.mmmback.service.WorkSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<WorksiteAllDto>> getWorkSites() {
        List<WorksiteAllDto> worksitesDTO = workSiteService.getWorkSites();
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
}
