package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.exceptions.*;
import matser2.istic.mmmback.mappers.*;
import matser2.istic.mmmback.models.*;
import matser2.istic.mmmback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AnomalyRepository anomalyRepository;
    public WorksitePostDto createWorkSite(WorksitePostDto worksiteDto) {

        Worksite worksite = worksiteMapper.worksitePostDtoToWorksite(worksiteDto);

        Customer customer = customerMapper.customerPostDtoToCustomer(worksiteDto.getCustomer());
        customer = customerRepository.save(customer);
        customer.addWorksite(worksite);

        Worksite savedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksitePostDto(savedWorksite);
    }



    public WorksiteGetDto getWorkSite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + id));
        return worksiteMapper.worksiteToWorksiteGetDto(worksite);
    }

    public List<WorksiteGetDto> getWorkSites() {

            List<Worksite> worksites = worksiteRepository.findAll();

          if (worksites.isEmpty()) {
              throw new WorksiteNotFoundException("No worksites found");
          }

            return worksites.stream()
                    .sorted((w1, w2) -> w2.getCreatedAt().compareTo(w1.getCreatedAt()))
                    .map(worksiteMapper::worksiteToWorksiteGetDto)
                    .collect(Collectors.toList());

    }

    public WorksiteGetDto addResourcesToWorksite(Long worksiteId, List<Long> resourceIds) {
        if (resourceIds == null || resourceIds.isEmpty()) {
            throw new InvalidWorksiteOperationException("Resource IDs list cannot be null or empty");
        }

        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException ("Worksite not found with id: " + worksiteId));

        List<Resources> resources = resourcesRepository.findAllById(resourceIds);
        if (resources.size() != resourceIds.size()) {
            Set<Long> foundResourceIds = resources.stream()
                    .map(Resources::getId)
                    .collect(Collectors.toSet());

            List<Long> missingResourceIds = resourceIds.stream()
                    .filter(id -> !foundResourceIds.contains(id))
                    .collect(Collectors.toList());

            throw new ResourceNotFoundException("Resources not found with ids: " + missingResourceIds);
        }

        Date startDate = worksite.getStartDate();
        int durationInHalfDays = worksite.getDuration();
        Date endDate = calculateEndDate(startDate, durationInHalfDays);

        for (Resources resource : resources) {
            List<Availability> availabilities = availabilityRepository.findByResourceId(resource.getId());

            for (Availability availability : availabilities) {
                    availability.setStartTime(startDate);
                    availability.setEndTime(endDate);
                    availabilityRepository.save(availability);

            }

            worksite.addResources(resource);
        }

        Worksite updatedWorksite = worksiteRepository.save(worksite);
        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite);
    }


    @Transactional
    public WorksiteGetDto removeResourceFromWorksite(Long worksiteId, Long resourceId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + worksiteId));

        Resources resource = resourcesRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));

        if (!worksite.getResources().contains(resource)) {
            throw new InvalidWorksiteOperationException("The resource is not associated with this worksite.");
        }

        List<Availability> availabilities = availabilityRepository.findByResourceId(resourceId);
        for (Availability availability : availabilities) {
            if (availability.getStartTime() != null && availability.getEndTime() != null) {
                availability.setStartTime(null);
                availability.setEndTime(null);
                availabilityRepository.save(availability);
            }
        }

        worksite.removeResources(resource);

        Worksite updatedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite);
    }

    public Date calculateEndDate(Date startDate, int durationInHalfDays) {
        int daysToAdd = durationInHalfDays / 2;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);

        return calendar.getTime();
    }

    public WorksiteAllDto updateWorksite(Long id, WorksiteAllDto worksiteAllDto) {
        if (id == null) {
            throw new IllegalArgumentException("The worksite ID must be provided for the update.");
        }

        Worksite existingWorksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("No workSites found with the ID supplied."));

        if (worksiteAllDto.getCustomer() != null && worksiteAllDto.getCustomer().getId() != null) {
            Long customerId = worksiteAllDto.getCustomer().getId();
            if (existingWorksite.getCustomer() == null || !existingWorksite.getCustomer().getId().equals(customerId)) {
                Customer newCustomer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new CustomerNotFoundException("No customers found with the ID supplied."));
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

        return worksiteMapper.worksiteToWorksiteAllDto(updatedWorksite);
    }

    public List<ResourcesSimpleDto> getWorksiteResources(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + worksiteId));

        return worksite.getResources().stream()
                .map(resourcesSimpleMapper::resourcesToResourcesSimpleDto)
                .collect(Collectors.toList());
    }

    public void deleteWorksite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("Aucun chantier trouvé avec l'ID fourni."));



        worksiteRepository.delete(worksite);
    }



    @Transactional
    public WorksiteGetDto updateWorksiteStatus(Long worksiteId, WorksiteStatus newStatus) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with ID: " + worksiteId));

        worksite.setStatus(newStatus);

        if (newStatus == WorksiteStatus.COMPLETED) {
            List<Resources> resources = worksite.getResources();

            for (Resources resource : resources) {
                List<Availability> availabilities = availabilityRepository.findByResource(resource);

                for (Availability availability : availabilities) {

                    if (availability.getResource().getWorksites().contains(worksite)) {
                        availability.setStartTime(null);
                        availability.setEndTime(null);
                        availabilityRepository.save(availability);
                    }
                }
            }
        }

        Worksite updatedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksiteGetDto(updatedWorksite);
    }

    public AnomalyDto addAnomalyToWorksite(Long worksiteId, AnomalyDto anomalyDto) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + worksiteId));

        Anomaly anomaly = new Anomaly();
        anomaly.setTitle(anomalyDto.getTitle());
        anomaly.setDescription(anomalyDto.getDescription());
        anomaly.setSeverityLevel(anomalyDto.getSeverityLevel());

        worksite.addAnomaly(anomaly);

        if (anomalyDto.getPhotos() != null) {
            for (PhotoDto photoDto : anomalyDto.getPhotos()) {
                if (photoDto.getFilePath() != null && !photoDto.getFilePath().isEmpty()) {
                    Photo photo = new Photo();
                    photo.setFilePath(photoDto.getFilePath());

                    anomaly.addPhoto(photo);
                    worksite.addPhoto(photo);
                }
            }
        }

        Worksite updatedWorksite = worksiteRepository.save(worksite);

        Anomaly savedAnomaly = updatedWorksite.getAnomalies().get(updatedWorksite.getAnomalies().size() - 1);

        return anomalyMapper.anomalyToAnomalyDto(savedAnomaly);
    }

    public void addPhotosToWorksite(Long worksiteId, List<PhotoDto> photos) throws EntityNotFoundException {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found"));

        for (PhotoDto photoDto : photos) {
            if (photoDto.getFilePath() != null && !photoDto.getFilePath().isEmpty()) {
                Photo photo = new Photo();
                photo.setFilePath(photoDto.getFilePath());

                worksite.addPhoto(photo);
            }
        }

        worksiteRepository.save(worksite);
    }

    public AnomalyDto updateAnomalyInWorksite(Long worksiteId, Long anomalyId, AnomalyDto anomalyDto) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + worksiteId));

        Anomaly existingAnomaly = worksite.getAnomalies().stream()
                .filter(a -> a.getId().equals(anomalyId))
                .findFirst()
                .orElseThrow(() -> new AnomalyNotFoundException("Anomaly not found with id: " + anomalyId));

        existingAnomaly.setTitle(anomalyDto.getTitle());
        existingAnomaly.setDescription(anomalyDto.getDescription());
        existingAnomaly.setSeverityLevel(anomalyDto.getSeverityLevel());


        existingAnomaly.getPhotos().clear();

        if (anomalyDto.getPhotos() != null) {
            for (PhotoDto photoDto : anomalyDto.getPhotos()) {
                if (photoDto.getFilePath() != null && !photoDto.getFilePath().isEmpty()) {
                    Photo photo = new Photo();
                    photo.setFilePath(photoDto.getFilePath());
                    existingAnomaly.addPhoto(photo);
                    worksite.addPhoto(photo);
                }
            }
        }

        Worksite updatedWorksite = worksiteRepository.save(worksite);

        Anomaly savedAnomaly = updatedWorksite.getAnomalies().stream()
                .filter(a -> a.getId().equals(anomalyId))
                .findFirst()
                .orElseThrow(() -> new AnomalyNotFoundException("Updated anomaly not found"));

        return anomalyMapper.anomalyToAnomalyDto(savedAnomaly);

    }

    public List<WorksiteGetDto> getWorksitesByResourceId(Long resourceId) {
        Resources resource = resourcesRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));

        List<Worksite> worksites = worksiteRepository.findByResources(resource);

        return worksites.stream()
                .map(worksiteMapper::worksiteToWorksiteGetDto)
                .collect(Collectors.toList());
    }
}
