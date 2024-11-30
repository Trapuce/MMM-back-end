package matser2.istic.mmmback.config;

import matser2.istic.mmmback.DTO.EmployeeDto;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.*;
import matser2.istic.mmmback.repository.CustomerRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import matser2.istic.mmmback.repository.WorksiteRepository;
import matser2.istic.mmmback.service.ResourceService;
import matser2.istic.mmmback.service.WorkSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WorksiteRepository worksiteRepository;

    @Autowired
    private WorksiteMapper worksiteMapper;
    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private WorkSiteService workSiteService;
    @Override
    public void run(String... args) throws Exception {
        seedCustomers();
        seedWorksites();
        seedResources();
    }

    private void seedCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Client Alpha");
        customer1.setEmail("alpha@example.com");
        customer1.setPhoneNumber(123456789);

        Customer customer2 = new Customer();
        customer2.setName("Client Beta");
        customer2.setEmail("beta@example.com");
        customer2.setPhoneNumber(987654321);

        customerRepository.saveAll(Arrays.asList(customer1, customer2));
        System.out.println("Customers seeded: " + Arrays.asList(customer1.getName(), customer2.getName()));
    }

    private void seedWorksites() {
        Customer customerAlpha = customerRepository.findByName("Client Alpha").orElseThrow();

        Worksite worksite1 = new Worksite();
        worksite1.setTitle("Construction Immeuble Alpha");
        worksite1.setDescription("Immeuble résidentiel de 10 étages.");
        worksite1.setStartDate(new Date());
        worksite1.setStatusUpdated(new Date());
        worksite1.setDuration(24);
        worksite1.setCreatedAt(new Date());
        worksite1.setLongitude(48);
        worksite1.setLatitude(2);
        worksite1.setLocation("Paris");
        worksite1.setCustomer(customerAlpha);
        worksite1.setStatus(WorksiteStatus.IN_PROGRESS);

        Worksite worksite2 = new Worksite();
        worksite2.setTitle("Pont Beta");
        worksite2.setDescription("Construction d'un pont suspendu.");
        worksite2.setStartDate(new Date());
        worksite2.setStatusUpdated(new Date());
        worksite2.setDuration(41);
        worksite2.setCreatedAt(new Date());
        worksite2.setLongitude(50);
        worksite2.setLatitude(5);
        worksite2.setLocation("Lyon");
        worksite2.setCustomer(customerAlpha);
        worksite2.setStatus(WorksiteStatus.NOT_STARTED);

        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite1));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite2));

        System.out.println("Worksites seeded: " + Arrays.asList(worksite1.getTitle(), worksite2.getTitle()));
    }

    private void seedResources() {
        // Employees
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");
        employee1.setPassword("password123");
        employee1.setRole(Role.CHEF_DE_CHANTIER);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmail("jane.smith@example.com");
        employee2.setPassword("securePass456");
        employee2.setRole(Role.RESPONSABLE_DU_CHANTIER);

        // Vehicles
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("ABC-1234");
        vehicle1.setModel("Ford Transit");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setLicensePlate("XYZ-5678");
        vehicle2.setModel("Renault Kangoo");

        // Equipment
        Equipment equipment1 = new Equipment();
        equipment1.setType("Power Drill");
        equipment1.setSerialNumber("PD-123456");

        Equipment equipment2 = new Equipment();
        equipment2.setType("Cement Mixer");
        equipment2.setSerialNumber("CM-789012");

        resourceService.createResource(employee1);
        resourceService.createResource(employee2);
        resourceService.createResource(vehicle1);
        resourceService.createResource(vehicle2);

        System.out.println("Resources seeded: Employees, Vehicles, Equipment");
    }
}