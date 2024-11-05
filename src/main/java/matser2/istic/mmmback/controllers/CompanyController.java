package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.DTO.CompanyAllDto;
import matser2.istic.mmmback.DTO.CompanyGetDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
import matser2.istic.mmmback.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * Récupérer une entreprise par son ID
     *
     * @param id L'ID de l'entreprise
     * @return La réponse contenant le DTO de l'entreprise ou une réponse 404 si l'entreprise n'est pas trouvée
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyGetDto> getCompany(@PathVariable Long id) {
        CompanyGetDto companyDto = companyService.getCompany(id);

        if (companyDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyDto);
    }

    /**
     * Récupérer toutes les entreprises
     *
     * @return La liste de toutes les entreprises, ou une réponse 204 si aucune entreprise n'est trouvée
     */
    @GetMapping
    public ResponseEntity<List<CompanyGetDto>> getAllCompanies() {
        List<CompanyGetDto> companies = companyService.getAllCompanies();

        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }

    /**
     * Ajouter une nouvelle entreprise
     *
     * @param companyDto Les informations de l'entreprise à créer
     * @return La réponse contenant le DTO de l'entreprise créée ou une réponse 400/500 en cas d'erreur
     */
    @PostMapping
    public ResponseEntity<CompanyPostDto> addCompany(@RequestBody CompanyPostDto companyDto) {
        try {
            CompanyPostDto createdCompany = companyService.createCompany(companyDto);
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
}
