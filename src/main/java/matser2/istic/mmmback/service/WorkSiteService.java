package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.*;
import matser2.istic.mmmback.models.*;
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

    @Autowired
    private AnomalyMapper anomalyMapper;

    public WorksitePostDto createWorkSite(WorksitePostDto worksiteDto) {
        if (worksiteDto.getCustomer() == null) {
            throw new IllegalArgumentException("Le client doit être fourni et ne doit pas être nul.");
        }

        Worksite worksite = worksiteMapper.worksitePostDtoToWorksite(worksiteDto);

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
    public WorksiteAllDto updateWorksite(Long id, WorksiteAllDto worksiteAllDto) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du chantier doit être fourni pour la mise à jour.");
        }

        Worksite existingWorksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun chantier trouvé avec l'ID fourni."));

        if (worksiteAllDto.getCustomer() != null && worksiteAllDto.getCustomer().getId() != null) {
            Long customerId = worksiteAllDto.getCustomer().getId();
            if (existingWorksite.getCustomer() == null || !existingWorksite.getCustomer().getId().equals(customerId)) {
                Customer newCustomer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new EntityNotFoundException("Aucun client trouvé avec l'ID fourni."));
                existingWorksite.setCustomer(newCustomer);
                newCustomer.addWorksite(existingWorksite);
            }
        }

        existingWorksite.setTitle(worksiteAllDto.getTitle());
        existingWorksite.setDescription(worksiteAllDto.getDescription());
        existingWorksite.setLocation(worksiteAllDto.getLocation());
        existingWorksite.setStartDate(worksiteAllDto.getStartDate());
        existingWorksite.setStatus(worksiteAllDto.getStatus());
        existingWorksite.setLongitude(worksiteAllDto.getLongitude());
        existingWorksite.setLatitude(worksiteAllDto.getLatitude());
        existingWorksite.setDuration(worksiteAllDto.getDuration());

        Worksite updatedWorksite = worksiteRepository.save(existingWorksite);

        return worksiteMapper.worksiteToWorksiteAllDto(updatedWorksite);
    }



    public void deleteWorksite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun chantier trouvé avec l'ID fourni."));



        worksiteRepository.delete(worksite);
    }

    public void updateWorksiteStatus(Long worksiteId, WorksiteStatus newStatus) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with ID: " + worksiteId));

        worksite.setStatus(newStatus);

        worksiteRepository.save(worksite);
    }

    public AnomalyDto addAnomalyToWorksite(Long worksiteId, AnomalyDto anomalyDto) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Anomaly anomaly = new Anomaly();
        anomaly.setDescription(anomalyDto.getDescription());
        worksite.addAnomaly(anomaly);

        for (PhotoDto photo : anomalyDto.getPhotos()) {

            Photo photonew = new Photo();
            photonew.setId(photo.getId());
            photonew.setFilePath(photo.getFilePath());
            anomaly.addPhoto(photonew);
        }

        worksite.addAnomaly(anomaly);
        worksiteRepository.save(worksite);

        return anomalyMapper.anomalyToAnomalyDto(anomaly);
    }

}
