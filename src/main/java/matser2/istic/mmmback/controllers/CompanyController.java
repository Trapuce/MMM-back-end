package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Company ")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public ResponseEntity<Company> addCompany(Company company) {
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
             companyService.CreateCompany(company);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
