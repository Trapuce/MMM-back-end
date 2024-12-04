package matser2.istic.mmmback.service;


import matser2.istic.mmmback.DTO.CustomerGetDto;
import matser2.istic.mmmback.DTO.CustomerPostDto;
import matser2.istic.mmmback.exceptions.CustomerNotFoundException;
import matser2.istic.mmmback.mappers.CustomerMapper;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository ;

    @Autowired
    private CustomerMapper customerMapper;


    public List<CustomerGetDto> getCustomerAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToCustomerGetDto)
                .collect(Collectors.toList());
    }


    public CustomerGetDto getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        return customerMapper.customerToCustomerGetDto(customer);
    }
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        customerRepository.delete(customer);
    }

    public CustomerGetDto updateCustomer(Long customerId, CustomerPostDto customerUpdateDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        customer.setName(customerUpdateDto.getName());
        customer.setEmail(customerUpdateDto.getEmail());

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerGetDto(updatedCustomer);
    }

}
