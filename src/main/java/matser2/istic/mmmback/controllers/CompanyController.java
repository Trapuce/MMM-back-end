package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.DTO.CompanyDto;
import matser2.istic.mmmback.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) {
        CompanyDto companyDto = companyService.getCompany(id);

        if (companyDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyDto);
    }


    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();

        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }


    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
        try {
            CompanyDto createdCompany = companyService.createCompany(companyDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdCompany);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyPostDto) {
        try {
            CompanyDto updatedCompany = companyService.updateCompany(companyPostDto);
            return ResponseEntity.ok(updatedCompany);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
