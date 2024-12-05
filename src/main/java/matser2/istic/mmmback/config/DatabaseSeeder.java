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


        Customer customerGamma = new Customer();
        customerGamma.setName("Client Gamma");
        customerGamma.setEmail("gamma@example.com");
        customerGamma.setPhoneNumber(456123789);

        Customer customerDelta = new Customer();
        customerDelta.setName("Client Delta");
        customerDelta.setEmail("delta@example.com");
        customerDelta.setPhoneNumber(789456123);

        Customer customerEpsilon = new Customer();
        customerEpsilon.setName("Client Epsilon");
        customerEpsilon.setEmail("epsilon@example.com");
        customerEpsilon.setPhoneNumber(321654987);


        customerRepository.saveAll(Arrays.asList(customer1, customer2 , customerGamma,customerDelta ,customerEpsilon ));
        System.out.println("Customers seeded: " + Arrays.asList(customer1.getName(), customer2.getName(), customerGamma.getName(), customerDelta.getName(), customerEpsilon.getName()));
    }

    private void seedWorksites() {
        Customer customerAlpha = customerRepository.findByName("Client Alpha").orElseThrow();
        Customer customerBeta = customerRepository.findByName("Client Beta").orElseThrow();
        Customer customerGamma = customerRepository.findByName("Client Gamma").orElseThrow();
        Customer customerDelta = customerRepository.findByName("Client Delta").orElseThrow();
        Customer customerEpsilon = customerRepository.findByName("Client Epsilon").orElseThrow();
        Customer customerZeta = customerRepository.findByName("Client Beta").orElseThrow();

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

        Worksite worksite3 = new Worksite();
        worksite3.setTitle("Construction École Gamma");
        worksite3.setDescription("Construction d'une école primaire avec 15 classes.");
        worksite3.setStartDate(new Date());
        worksite3.setStatusUpdated(new Date());
        worksite3.setDuration(18);
        worksite3.setCreatedAt(new Date());
        worksite3.setLongitude((int) 44.1);
        worksite3.setLatitude((int) 3.5);
        worksite3.setLocation("Marseille");
        worksite3.setCustomer(customerGamma);
        worksite3.setStatus(WorksiteStatus.IN_PROGRESS);

        Worksite worksite4 = new Worksite();
        worksite4.setTitle("Aménagement Parc Delta");
        worksite4.setDescription("Création d'un parc public de 2 hectares.");
        worksite4.setStartDate(new Date());
        worksite4.setStatusUpdated(new Date());
        worksite4.setDuration(8);
        worksite4.setCreatedAt(new Date());
        worksite4.setLongitude((int) 50.1);
        worksite4.setLatitude((int) 1.9);
        worksite4.setLocation("Nice");
        worksite4.setCustomer(customerDelta);
        worksite4.setStatus(WorksiteStatus.COMPLETED);

// Worksite 5
        Worksite worksite5 = new Worksite();
        worksite5.setTitle("Réfection Route Epsilon");
        worksite5.setDescription("Réfection de 10 km de route départementale.");
        worksite5.setStartDate(new Date());
        worksite5.setStatusUpdated(new Date());
        worksite5.setDuration(16);
        worksite5.setCreatedAt(new Date());
        worksite5.setLongitude((int) 45.2);
        worksite5.setLatitude((int) 4.2);
        worksite5.setLocation("Toulouse");
        worksite5.setCustomer(customerEpsilon);
        worksite5.setStatus(WorksiteStatus.INTERRUPTED);


        Worksite worksite6 = new Worksite();
        worksite6.setTitle("Extension Usine Zeta");
        worksite6.setDescription("Extension de 5000m² d'une usine de production.");
        worksite6.setStartDate(new Date());
        worksite6.setStatusUpdated(new Date());
        worksite6.setDuration(20);
        worksite6.setCreatedAt(new Date());
        worksite6.setLongitude((int) 43.5);
        worksite6.setLatitude((int) 5.6);
        worksite6.setLocation("Bordeaux");
        worksite6.setCustomer(customerZeta);
        worksite6.setStatus(WorksiteStatus.NOT_STARTED);

        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite1));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite2));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite3));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite4));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite5));
        workSiteService.createWorkSite(worksiteMapper.worksiteToWorksitePostDto(worksite6));

        System.out.println("Worksites seeded: " + Arrays.asList(
                worksite1.getTitle(),
                worksite2.getTitle(),
                worksite3.getTitle(),
                worksite4.getTitle(),
                worksite5.getTitle(),
                worksite6.getTitle()
        ));
    }

    private void seedResources() {
        // Employees
        Employee employee1 = new Employee();
        employee1.setName("john doe");
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");
        employee1.setPassword("password123");
        employee1.setRole(Role.CHEF_DE_CHANTIER);

        Employee employee2 = new Employee();
        employee2.setName("jane smith");
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmail("jane.smith@example.com");
        employee2.setPassword("securePass456");
        employee2.setRole(Role.RESPONSABLE_DU_CHANTIER);



        Employee employee3 = new Employee();
        employee3.setName("michael brown");
        employee3.setFirstName("Michael");
        employee3.setLastName("Brown");
        employee3.setEmail("michael.brown@example.com");
        employee3.setPassword("michaelPass789");
        employee3.setRole(Role.CHEF_DE_CHANTIER);

        Employee employee4 = new Employee();
        employee4.setName("emily davis");
        employee4.setFirstName("Emily");
        employee4.setLastName("Davis");
        employee4.setEmail("emily.davis@example.com");
        employee4.setPassword("emilySecure456");
        employee4.setRole(Role.RESPONSABLE_DU_CHANTIER);

        Employee employee5 = new Employee();
        employee5.setName("daniel williams");
        employee5.setFirstName("Daniel");
        employee5.setLastName("Williams");
        employee5.setEmail("daniel.williams@example.com");
        employee5.setPassword("equipier123");
        employee5.setRole(Role.EQUIPIER_SIMPLE);

        Employee employee6 = new Employee();
        employee6.setName("sarah johnson");
        employee6.setFirstName("Sarah");
        employee6.setLastName("Johnson");
        employee6.setEmail("sarah.johnson@example.com");
        employee6.setPassword("simplePass001");
        employee6.setRole(Role.EQUIPIER_SIMPLE);

        Employee employee7 = new Employee();
        employee7.setName("mark taylor");
        employee7.setFirstName("Mark");
        employee7.setLastName("Taylor");
        employee7.setEmail("mark.taylor@example.com");
        employee7.setPassword("equipierPass202");
        employee7.setRole(Role.EQUIPIER_SIMPLE);

        Employee employee8 = new Employee();
        employee8.setName("laura wilson");
        employee8.setFirstName("Laura");
        employee8.setLastName("Wilson");
        employee8.setEmail("laura.wilson@example.com");
        employee8.setPassword("teamwork303");
        employee8.setRole(Role.EQUIPIER_SIMPLE);


        // Vehicles
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("BMW");
        vehicle1.setLicensePlate("ABC-1234");
        vehicle1.setModel("Ford Transit");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Mercedes");
        vehicle2.setLicensePlate("XYZ-5678");
        vehicle2.setModel("Renault Kangoo");

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setName("Toyota");
        vehicle3.setLicensePlate("LMN-9101");
        vehicle3.setModel("Toyota Hilux");

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setName("Volkswagen");
        vehicle4.setLicensePlate("OPQ-2345");
        vehicle4.setModel("Volkswagen Transporter");

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setName("Fiat");
        vehicle5.setLicensePlate("RST-6789");
        vehicle5.setModel("Fiat Ducato");


        // Equipment
        Equipment equipment1 = new Equipment();
        equipment1.setName("Marteau");
        equipment1.setType("Power Drill");
        equipment1.setSerialNumber("PD-123456");

        Equipment equipment2 = new Equipment();
        equipment2.setName("Brouette");
        equipment2.setType("Cement Mixer");
        equipment2.setSerialNumber("CM-789012");

        Equipment equipment3 = new Equipment();
        equipment3.setName("Perceuse");
        equipment3.setType("Electric Drill");
        equipment3.setSerialNumber("ED-345678");

        Equipment equipment4 = new Equipment();
        equipment4.setName("Pelle");
        equipment4.setType("Shovel");
        equipment4.setSerialNumber("SH-901234");

        Equipment equipment5 = new Equipment();
        equipment5.setName("Scie circulaire");
        equipment5.setType("Circular Saw");
        equipment5.setSerialNumber("CS-567890");


        resourceService.createResource(employee1);
        resourceService.createResource(employee2);
        resourceService.createResource(employee3);
        resourceService.createResource(employee4);
        resourceService.createResource(employee5);
        resourceService.createResource(employee6);
        resourceService.createResource(employee7);
        resourceService.createResource(employee8);


        resourceService.createResource(vehicle1);
        resourceService.createResource(vehicle2);
        resourceService.createResource(vehicle3);
        resourceService.createResource(vehicle4);
        resourceService.createResource(vehicle5);


        resourceService.createResource(equipment1);
        resourceService.createResource(equipment2);
        resourceService.createResource(equipment3);
        resourceService.createResource(equipment5);



        System.out.println("Resources seeded: Employees, Vehicles, Equipment");
    }
}