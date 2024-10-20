package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.models.Worksite;
import matser2.istic.mmmback.service.WorkSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workSite")
public class WorkSiteController {


    @Autowired
    private WorkSiteService workSiteService;


    @PostMapping
    public ResponseEntity<Worksite> addWorkSite(@RequestBody Worksite worksite) {
        if (worksite == null || worksite.getCustomer() == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Worksite createdWorksite = workSiteService.createWorkSite(worksite);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorksite);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worksite> getWorkSite(@PathVariable Long id) {
        Worksite worksite = workSiteService.getWorkSite(id);
        if (worksite == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worksite);
    }

    @GetMapping
    public ResponseEntity<List<Worksite>> getWorkSites() {
        List<Worksite> worksites = workSiteService.getWorkSites();
        if (worksites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(worksites);
    }
}
