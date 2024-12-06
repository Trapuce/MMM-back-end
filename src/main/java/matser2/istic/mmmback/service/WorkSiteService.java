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
    private AnomalyMapper anomalyMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private ResourcesSimpleMapper resourcesSimpleMapper;



    @Autowired
    private AvailabilityRepository availabilityRepository;





    /**
     * Creates a new worksite based on the provided DTO.
     * Saves the associated customer and links it to the worksite.
     *
     * @param worksiteDto Data Transfer Object containing worksite details.
     * @return WorksitePostDto representing the created worksite.
     */
    public WorksitePostDto createWorkSite(WorksitePostDto worksiteDto) {
        Worksite worksite = worksiteMapper.worksitePostDtoToWorksite(worksiteDto);
        if (worksiteDto == null) {
            throw new WorksiteException("The worksite data transfer object (DTO) must not be null.");
        }

        if (worksiteDto.getCustomer() == null ) {
            throw new CustomerNotFoundException("The customer ID must be provided in the DTO.");
        }
        Customer customer = customerMapper.customerPostDtoToCustomer(worksiteDto.getCustomer());


        customer = customerRepository.save(customer);
        customer.addWorksite(worksite);

        Worksite savedWorksite = worksiteRepository.save(worksite);

        return worksiteMapper.worksiteToWorksitePostDto(savedWorksite);
    }




    /**
     * Retrieves a specific worksite by its ID.
     *
     * @param id The ID of the worksite.
     * @return WorksiteGetDto containing details of the requested worksite.
     * @throws WorksiteNotFoundException if no worksite with the given ID exists.
     */
    public WorksiteGetDto getWorkSite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + id));
        return worksiteMapper.worksiteToWorksiteGetDto(worksite);
    }



    /**
     * Retrieves all worksites and sorts them by creation date in descending order.
     *
     * @return List of WorksiteGetDto objects.
     * @throws WorksiteNotFoundException if no worksites are found.
     */
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

    /**
     * Adds a list of resources to a specific worksite.
     * Updates resource availability during the worksite duration.
     *
     * @param worksiteId The ID of the worksite.
     * @param resourceIds List of resource IDs to add.
     * @return Updated WorksiteGetDto.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     * @throws ResourceNotFoundException if any resource is not found.
     * @throws InvalidWorksiteOperationException if the resource list is null or empty.
     */
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


    /**
     * Removes a resource from a worksite and resets the resource's availability.
     *
     * @param worksiteId The ID of the worksite.
     * @param resourceId The ID of the resource to remove.
     * @return Updated WorksiteGetDto.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     * @throws ResourceNotFoundException if the resource does not exist.
     * @throws InvalidWorksiteOperationException if the resource is not associated with the worksite.
     */
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


    /**
     * Calculates the end date of a worksite based on its start date and duration.
     *
     * @param startDate The start date of the worksite.
     * @param durationInHalfDays The duration in half days.
     * @return The calculated end date.
     */
    public Date calculateEndDate(Date startDate, int durationInHalfDays) {
        int daysToAdd = durationInHalfDays / 2;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);

        return calendar.getTime();
    }


    /**
     * Updates an existing worksite based on its ID and the provided details.
     * Links or updates the customer, anomalies, photos, and resources.
     *
     * @param id The ID of the worksite to update.
     * @param worksiteAllDto Data Transfer Object containing the new details of the worksite.
     * @return Updated WorksiteAllDto representing the updated worksite.
     * @throws IllegalArgumentException if the ID is null.
     * @throws WorksiteNotFoundException if no worksite is found with the provided ID.
     * @throws CustomerNotFoundException if the customer ID in the DTO does not exist.
     */
    public WorksiteAllDto updateWorksite(Long id, WorksiteAllDto worksiteAllDto) {
        if (id == null) {
            throw new IllegalArgumentException("The worksite ID must be provided for the update.");
        }

        Worksite existingWorksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("No workSites found with the ID supplied."));

        // Update customer information if provided
        if (worksiteAllDto.getCustomer() != null) {
            Customer customerToUpdate = null;

            // Find the customer to update
            if (worksiteAllDto.getCustomer().getId() != null) {
                customerToUpdate = customerRepository.findById(worksiteAllDto.getCustomer().getId())
                        .orElseThrow(() -> new CustomerNotFoundException("No customers found with the ID supplied."));
            } else if (existingWorksite.getCustomer() != null) {
                customerToUpdate = existingWorksite.getCustomer();
            }

            // Update customer details if customer exists
            if (customerToUpdate != null) {
                CustomerGetDto customerDto = worksiteAllDto.getCustomer();

                // Update customer fields as needed
                if (customerDto.getName() != null) {
                    customerToUpdate.setName(customerDto.getName());
                }
                if (customerDto.getEmail() != null) {
                    customerToUpdate.setEmail(customerDto.getEmail());
                }
                if (customerDto.getPhoneNumber() != null) {
                    customerToUpdate.setPhoneNumber(Integer.parseInt(customerDto.getPhoneNumber()));
                }
                // Add more customer fields as necessary

                // Save updated customer
                customerRepository.save(customerToUpdate);

                // Link customer to worksite if not already linked
                if (existingWorksite.getCustomer() == null || !existingWorksite.getCustomer().getId().equals(customerToUpdate.getId())) {
                    existingWorksite.setCustomer(customerToUpdate);
                    customerToUpdate.addWorksite(existingWorksite);
                }
            }
        }

        // Rest of the existing update logic remains the same
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


    /**
     * Retrieves all resources associated with a specific worksite.
     *
     * @param worksiteId The ID of the worksite.
     * @return List of ResourcesSimpleDto representing the resources of the worksite.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     */
    public List<ResourcesSimpleDto> getWorksiteResources(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new WorksiteNotFoundException("Worksite not found with id: " + worksiteId));

        return worksite.getResources().stream()
                .map(resourcesSimpleMapper::resourcesToResourcesSimpleDto)
                .collect(Collectors.toList());
    }


    /**
     * Deletes a specific worksite by its ID.
     *
     * @param id The ID of the worksite to delete.
     * @throws WorksiteNotFoundException if no worksite is found with the provided ID.
     */
    public void deleteWorksite(Long id) {
        Worksite worksite = worksiteRepository.findById(id)
                .orElseThrow(() -> new WorksiteNotFoundException("Aucun chantier trouvé avec l'ID fourni."));
        worksiteRepository.delete(worksite);
    }



    /**
     * Updates the status of a specific worksite.
     * If the status is set to COMPLETED, clears the availability of associated resources.
     *
     * @param worksiteId The ID of the worksite to update.
     * @param newStatus The new status to assign to the worksite.
     * @return Updated WorksiteGetDto with the new status.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     */
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


    /**
     * Adds an anomaly to a specific worksite and optionally attaches photos to it.
     *
     * @param worksiteId The ID of the worksite.
     * @param anomalyDto Data Transfer Object containing the anomaly details.
     * @return AnomalyDto representing the created anomaly.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     */
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


    /**
     * Adds photos to a specific worksite.
     *
     * @param worksiteId The ID of the worksite.
     * @param photos List of PhotoDto objects representing the photos to add.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     */
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


    /**
     * Updates an anomaly in a specific worksite.
     *
     * @param worksiteId The ID of the worksite.
     * @param anomalyId The ID of the anomaly to update.
     * @param anomalyDto Data Transfer Object containing the updated anomaly details.
     * @return Updated AnomalyDto.
     * @throws WorksiteNotFoundException if the worksite does not exist.
     * @throws AnomalyNotFoundException if the anomaly does not exist in the worksite.
     */
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
