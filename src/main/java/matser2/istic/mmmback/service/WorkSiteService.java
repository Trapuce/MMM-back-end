package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.CompanyMapper;
import matser2.istic.mmmback.mappers.CustomerMapper;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import matser2.istic.mmmback.repository.CompanyRepository;
import matser2.istic.mmmback.repository.CustomerRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import matser2.istic.mmmback.repository.WorksiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkSiteService {

    @Autowired
    private WorksiteRepository worksiteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private WorksiteMapper worksiteMapper  ;

    @Autowired
    private CustomerMapper customerMapper   ;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMapper companyMapper;

    public WorksitePostDto createWorkSite(WorksitePostDto worksiteDto) {
        Long companyId = worksiteDto.getCompany() != null ? worksiteDto.getCompany().getId() : null;
        if (companyId == null) {
            throw new IllegalArgumentException("L'ID de la société doit être fourni.");
        }
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Société non trouvée avec l'ID fourni."));

        Worksite worksite = worksiteMapper.worksitePostDtoToWorksite(worksiteDto);

        company.addWorksite(worksite);

        if (worksiteDto.getCustomer() == null) {
            throw new IllegalArgumentException("Le client doit être fourni et ne doit pas être nul.");
        }

        Customer customer = customerMapper.customerPostDtoToCustomer(worksiteDto.getCustomer());

        customer = customerRepository.save(customer);

        customer.addWorksite(worksite);

        Worksite savedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksitePostDto(savedWorksite);
    }


    public WorksiteGetDto getWorkSite(Long id) {
        Worksite worksite = worksiteRepository.findById(id).orElse(null);
        return worksite != null ? worksiteMapper.worksiteToWorksiteGetDto(worksite) : null;
    }

    public List<WorksiteGetDto> getWorkSites() {
        List<Worksite> worksites = worksiteRepository.findAll();
        return  worksites.stream().map(worksiteMapper::worksiteToWorksiteGetDto).collect(Collectors.toList());
    }

    @Transactional
    public WorksiteGetDto addResourceToWorksite(Long worksiteId, Long resourceId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Resources resource = resourcesRepository.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource not found with id: " + resourceId));

        worksite.addResources(resource);
        Worksite updatedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite) ;
    }

    @Transactional
    public WorksiteGetDto updateWorksite(WorksitePostDto worksiteDto) {
        if (worksiteDto.getId() == null) {
            throw new IllegalArgumentException("L'ID du chantier doit être fourni pour la mise à jour.");
        }

        Worksite existingWorksite = worksiteRepository.findById(worksiteDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Aucun chantier trouvé avec l'ID fourni."));

        Long companyId = worksiteDto.getCompany() != null ? worksiteDto.getCompany().getId() : null;
        if (companyId != null && (existingWorksite.getCompany() == null || !existingWorksite.getCompany().getId().equals(companyId))) {
            Company newCompany = companyRepository.findById(companyId)
                    .orElseThrow(() -> new EntityNotFoundException("Aucune société trouvée avec l'ID fourni."));
            existingWorksite.setCompany(newCompany);
            newCompany.addWorksite(existingWorksite);
        }

        Long customerId = worksiteDto.getCustomer() != null ? worksiteDto.getCustomer().getId() : null;
        if (customerId != null && (existingWorksite.getCustomer() == null || !existingWorksite.getCustomer().getId().equals(customerId))) {
            Customer newCustomer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new EntityNotFoundException("Aucun client trouvé avec l'ID fourni."));
            existingWorksite.setCustomer(newCustomer);
            newCustomer.addWorksite(existingWorksite);
        }

        existingWorksite.setDescription(worksiteDto.getDescription());
        existingWorksite.setLocation(worksiteDto.getLocation());
        existingWorksite.setStartDate(worksiteDto.getStartDate());

        Worksite updatedWorksite = worksiteRepository.save(existingWorksite);
        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite);
    }

    public void deleteWorksite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun chantier trouvé avec l'ID fourni."));

        Company company = worksite.getCompany();
        if (company != null) {
            company.removeWorksite(worksite);
        }

        worksiteRepository.delete(worksite);
    }


}
