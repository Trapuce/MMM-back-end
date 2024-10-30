package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.CustomerAllDto;
import matser2.istic.mmmback.DTO.CustomerPostDto;
import matser2.istic.mmmback.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring"
)
public interface CustomerMapper {
    CustomerAllDto customerToCustomerAllDto(Customer customer);

    Customer customerAllDtoToCustomer(CustomerAllDto customerAllDto);

    CustomerPostDto customerToCustomerPostDto(Customer customer);

    Customer customerPostDtoToCustomer(CustomerPostDto customerPostDto);
}
