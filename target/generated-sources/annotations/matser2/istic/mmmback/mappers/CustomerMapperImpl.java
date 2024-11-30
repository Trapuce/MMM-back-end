package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.CustomerAllDto;
import matser2.istic.mmmback.DTO.CustomerGetDto;
import matser2.istic.mmmback.DTO.CustomerPostDto;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T22:57:56+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerAllDto customerToCustomerAllDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerAllDto customerAllDto = new CustomerAllDto();

        customerAllDto.setPhoneNumber( String.valueOf( customer.getPhoneNumber() ) );
        customerAllDto.setId( customer.getId() );
        customerAllDto.setName( customer.getName() );
        customerAllDto.setEmail( customer.getEmail() );
        List<Worksite> list = customer.getWorksites();
        if ( list != null ) {
            customerAllDto.setWorksites( new ArrayList<Worksite>( list ) );
        }

        return customerAllDto;
    }

    @Override
    public Customer customerAllDtoToCustomer(CustomerAllDto customerAllDto) {
        if ( customerAllDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerAllDto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( Integer.parseInt( customerAllDto.getPhoneNumber() ) );
        }
        customer.setId( customerAllDto.getId() );
        customer.setName( customerAllDto.getName() );
        customer.setEmail( customerAllDto.getEmail() );
        List<Worksite> list = customerAllDto.getWorksites();
        if ( list != null ) {
            customer.setWorksites( new ArrayList<Worksite>( list ) );
        }

        return customer;
    }

    @Override
    public CustomerPostDto customerToCustomerPostDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerPostDto customerPostDto = new CustomerPostDto();

        customerPostDto.setId( customer.getId() );
        customerPostDto.setName( customer.getName() );
        customerPostDto.setEmail( customer.getEmail() );
        customerPostDto.setPhoneNumber( String.valueOf( customer.getPhoneNumber() ) );

        return customerPostDto;
    }

    @Override
    public Customer customerPostDtoToCustomer(CustomerPostDto customerPostDto) {
        if ( customerPostDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerPostDto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( Integer.parseInt( customerPostDto.getPhoneNumber() ) );
        }
        customer.setId( customerPostDto.getId() );
        customer.setName( customerPostDto.getName() );
        customer.setEmail( customerPostDto.getEmail() );

        return customer;
    }

    @Override
    public CustomerGetDto customerToCustomerGetDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerGetDto customerGetDto = new CustomerGetDto();

        customerGetDto.setId( customer.getId() );
        customerGetDto.setName( customer.getName() );
        customerGetDto.setPhoneNumber( String.valueOf( customer.getPhoneNumber() ) );
        customerGetDto.setEmail( customer.getEmail() );

        return customerGetDto;
    }

    @Override
    public Customer customerGetDtoToCustomer(CustomerGetDto customerGetDto) {
        if ( customerGetDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerGetDto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( Integer.parseInt( customerGetDto.getPhoneNumber() ) );
        }
        customer.setId( customerGetDto.getId() );
        customer.setName( customerGetDto.getName() );
        customer.setEmail( customerGetDto.getEmail() );

        return customer;
    }
}
