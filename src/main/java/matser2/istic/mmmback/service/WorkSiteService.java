package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.CustomerMapper;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import matser2.istic.mmmback.repository.CustomerRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import matser2.istic.mmmback.repository.WorksiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkSiteService {

    @Autowired
    private WorksiteRepository worksiteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    private WorksiteMapper worksiteMapper  ;


    private CustomerMapper customerMapper   ;

    private ResourcesMapper resourceMapper ;

    public WorksitePostDto createWorkSite(WorksitePostDto worksiteDto) {
       CustomerPostDto customerDto = worksiteDto.getCustomer();
        Customer customer = customerMapper.customerPostDtoToCustomer(customerDto);

        Worksite worksite =  worksiteMapper.worksitePostDtoToWorksite(worksiteDto);

        if (customer != null) {
            customer = customerRepository.save(customer);

            customer.addWorksite(worksite);
        } else {
            throw new IllegalArgumentException("Customer must be provided and not null.");
        }

        Worksite savedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksitePostDto(savedWorksite);
    }

    public WorksiteGetDto getWorkSite(Long id) {
        Worksite worksite = worksiteRepository.findById(id).orElse(null);
        return worksite != null ? worksiteMapper.worksiteToWorksiteGetDto(worksite) : null;
    }

    public List<WorksiteAllDto> getWorkSites() {
        List<Worksite> worksites = worksiteRepository.findAll();
        return resourceMapper.worksiteToWorksiteAllDto(worksites);
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



}
