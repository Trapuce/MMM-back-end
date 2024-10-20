package matser2.istic.mmmback.service;


import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Worksite;
import matser2.istic.mmmback.repository.CustomerRepository;
import matser2.istic.mmmback.repository.WorksiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSiteService {

    @Autowired
    private WorksiteRepository worksiteRepository;
    @Autowired
    private CustomerRepository customerRepository;


    public Worksite createWorkSite(Worksite worksite) {
        Customer customer = this.customerRepository.findById(worksite.getCustomer().getId()).orElse(null);
        if (customer != null) {
            customer.addWorksite(worksite);
            //this.customerRepository.save(customer);
        }
        return worksiteRepository.save(worksite);
    }

    public Worksite getWorkSite(Long id) {
        return worksiteRepository.findById(id).orElse(null);
    }

    public List<Worksite> getWorkSites() {
        return worksiteRepository.findAll();
    }

}
