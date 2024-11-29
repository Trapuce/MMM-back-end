package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.*;
import matser2.istic.mmmback.models.*;
import matser2.istic.mmmback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private ResourcesSimpleMapper resourcesSimpleMapper;

    @Autowired
    private PhotoRepository    photoRepository;

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
    public WorksiteGetDto addResourcesToWorksite(Long worksiteId, List<Long> resourceIds) {
        // Validate input
        if (resourceIds == null || resourceIds.isEmpty()) {
            throw new IllegalArgumentException("Resource IDs list cannot be null or empty");
        }

        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        List<Resources> resources = resourcesRepository.findAllById(resourceIds);
        if (resources.size() != resourceIds.size()) {
            Set<Long> foundResourceIds = resources.stream()
                    .map(Resources::getId)
                    .collect(Collectors.toSet());

            List<Long> missingResourceIds = resourceIds.stream()
                    .filter(id -> !foundResourceIds.contains(id))
                    .collect(Collectors.toList());

            throw new EntityNotFoundException("Resources not found with ids: " + missingResourceIds);
        }

        resources.forEach(worksite::addResources);

        Worksite updatedWorksite = worksiteRepository.save(worksite);
        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite);
    }

    public WorksiteAllDto updateWorksite(Long id, WorksiteAllDto worksiteAllDto) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du chantier doit être fourni pour la mise à jour.");
        }

        Worksite existingWorksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun chantier trouvé avec l'ID fourni."));

        // Mise à jour du client associé
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
        existingWorksite.setStatusUpdated(new Date());
        existingWorksite.setStatus(worksiteAllDto.getStatus());
        existingWorksite.setLongitude(worksiteAllDto.getLongitude());
        existingWorksite.setLatitude(worksiteAllDto.getLatitude());
        existingWorksite.setDuration(worksiteAllDto.getDuration());

        if (worksiteAllDto.getAnomalies() != null) {
            existingWorksite.setAnomalies(anomalyMapper.anomalyDtosToAnomalies(worksiteAllDto.getAnomalies()));
        }

        if (worksiteAllDto.getPhotos() != null) {
            existingWorksite.setPhotos(photoMapper.photoDtosToPhotos(worksiteAllDto.getPhotos()));
        }

        if (worksiteAllDto.getResources() != null) {
            List<Resources> resources = worksiteAllDto.getResources().stream()
                    .map(resourcesMapper::resourcesDtoToResources)
                    .collect(Collectors.toList());
            existingWorksite.setResources(resources);
        }

        Worksite updatedWorksite = worksiteRepository.save(existingWorksite);

        // Retourner le DTO mis à jour
        return worksiteMapper.worksiteToWorksiteAllDto(updatedWorksite);
    }

    public List<ResourcesSimpleDto> getWorksiteResources(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        return worksite.getResources().stream()
                .map(resourcesSimpleMapper::resourcesToResourcesSimpleDto)
                .collect(Collectors.toList());
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

        if (anomalyDto.getPhotos() != null) {
            for (PhotoDto photoDto : anomalyDto.getPhotos()) {
                Photo photo = new Photo();
                photo.setFilePath(photoDto.getFilePath());
                anomaly.addPhoto(photo);

                worksite.addPhoto(photo);
            }
        }

        worksite.addAnomaly(anomaly);

        Worksite updatedWorksite = worksiteRepository.save(worksite);

        Anomaly savedAnomaly = updatedWorksite.getAnomalies().get(updatedWorksite.getAnomalies().size() - 1);

        return anomalyMapper.anomalyToAnomalyDto(savedAnomaly);
    }
    public void addPhotosToWorksite(Long worksiteId, List<PhotoDto> photos) throws EntityNotFoundException {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found"));

        for (PhotoDto photoDto : photos) {
            Photo photo = new Photo();
            photo.setFilePath(photoDto.getFilePath());
            worksite.addPhoto(photo);
            photoRepository.save(photo);
        }

        worksiteRepository.save(worksite);
    }
}
